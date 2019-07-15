package com.qdu.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.qdu.model.ProductModel;
import com.qdu.service.ProductService;
import com.qdu.utils.FormatPOI;
import com.qdu.utils.Layui;

@Controller
@RequestMapping("/pro")
public class ProductController {
	@Autowired
	ProductService productService;
	
	@RequestMapping("/insert")
	@ResponseBody
	public int insert(ProductModel product) {
		int a = productService.insert(product);
		System.out.println("a");
		return a;
	}
	
	@RequestMapping("/deleteModel")
	@ResponseBody
	public int deleteModel(ProductModel product) {
		int a = productService.deleteModel(product);
		return a;
	}
	
	@RequestMapping("/updateActive")
	@ResponseBody
	public int updateActive(ProductModel product) {
		int a = productService.updateActive(product);
		System.out.println("a");
		return a;
	}
	
	@RequestMapping("/selectCount")
	@ResponseBody
	public int selectCount() {
		int a = productService.selectCount();
		System.out.println("a");
		return a;
	}
	
	@RequestMapping("/selectModelPage")
	@ResponseBody
	public Layui selectModelPage(@RequestParam("page")int page,@RequestParam("limit")int limit,String code,String name) {
		ProductModel product = new ProductModel();
		product.setCode(code);
		product.setName(name);
		
		int before = limit * (page-1);
		int after = page * limit;
		
		List<ProductModel> list = productService.selectModelPage(product, before, after);
		int count = productService.selectCount();
		
		return Layui.data(count, list);
	}
	
	
	@RequestMapping("/selectModel")
	@ResponseBody
    public ProductModel selectModel(String code) {
    	
    	ProductModel product = new ProductModel();
    	product.setCode(code);
    	
    	List<ProductModel> list = productService.selectModel(product);
    	//System.out.println("product:"+list);
    	ProductModel product2 = new ProductModel();
    	for(ProductModel aa:list) {
    		
    		product2.setCode(aa.getCode());
    		product2.setName(aa.getName());
    		product2.setSum(aa.getSum());
    		product2.setCost(aa.getCost());
    	}
    	return product2;
    }
	
	//导出导入excle表格

	@RequestMapping("/excle")
	public void exportExcel(HttpServletResponse response) throws Exception {

		List<ProductModel> list = productService.selectAll(new ProductModel());
		List<String> propList = Arrays.asList("code", "name", "sum", "cost");
		List<String> fieldName = Arrays.asList("商品编号", "商品名", "数量", "费用");
		Workbook wb = FormatPOI.exportExcel(list, propList, fieldName);

		// 1)、设置响应的头文件，会自动识别文件内容
		response.setContentType("multipart/form-data");
		// 2)、设置Content-Disposition
		response.setHeader("Content-Disposition", "attachment;filename=product.xlsx");
		// 3)、输出流
		OutputStream out = response.getOutputStream();
		wb.write(out);
		wb.close();
		out.close();
	}
	
	@RequestMapping("/model")
	public void model(HttpServletResponse response) throws Exception {
		List<String> fieldName = Arrays.asList("商品编号", "商品名", "数量", "费用");
		Workbook wb = FormatPOI.moban(fieldName);
		// 1)、设置响应的头文件，会自动识别文件内容
		response.setContentType("multipart/form-data");
		// 2)、设置Content-Disposition
		response.setHeader("Content-Disposition", "attachment;filename=productModel.xlsx");
		// 3)、输出流
		OutputStream out = response.getOutputStream();
		wb.write(out);
		wb.close();
		out.close();
	}
	
	@ResponseBody
	@RequestMapping(value = "/upload", produces = "application/json;charset=UTF-8")
	public String uploadExcel(CommonsMultipartResolver multipartResolver, HttpServletRequest request) throws Exception {
		Map<String, Object> result = new HashMap<>();
		result.put("code", "0");
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				MultipartFile file = multiRequest.getFile(iter.next().toString());
				result.put("data", parse(file.getInputStream()));
			}
		}
		return new JSONObject(result).toString();
	}

	/**
	 * 将文档转换为数据库中的记录
	 */
	private List<ProductModel> parse(InputStream fis) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("user");
		List<ProductModel> list = new ArrayList<>();
		for (Row row : sheet) {
			if (0 == row.getRowNum()) {
				continue;
			}
			ProductModel product = new ProductModel();
			product.setCode(getValue(row.getCell(0)));
			product.setName(getValue(row.getCell(1)));
			product.setSum(Integer.parseInt(getValue(row.getCell(2))));
			product.setCost(Integer.parseInt(getValue(row.getCell(3))));
			list.add(product);
			productService.insert(product);
		}
		workbook.close();
		fis.close();
		return list;
	}

	private String getValue(Cell cell) {
		CellType type = cell.getCellTypeEnum();
		if (CellType.STRING.equals(type)) {
			return cell.getStringCellValue();
		} else if (CellType.NUMERIC.equals(type)) {
			return String.valueOf((int)cell.getNumericCellValue());
		}
		return null;
	}
	
}
