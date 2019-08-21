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

import com.qdu.model.OrderInfoModel;
import com.qdu.service.OrderInfoService;
import com.qdu.utils.FormatPOI;
import com.qdu.utils.Layui;

@Controller("order")
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderInfoService orderInfoService;
	
	@RequestMapping("/insert")
	@ResponseBody
	public int insert(OrderInfoModel order) {
		int a = orderInfoService.insert(order);
		return a;
	}
	
	@RequestMapping("/deleteModel")
	@ResponseBody
	public int deleteModel(OrderInfoModel order) {
		int a = orderInfoService.deleteModel(order);
		return a;
	}
	
	@RequestMapping("/updateActive")
	@ResponseBody
	public int updateActive(OrderInfoModel order) {
		int a = orderInfoService.updateActive(order);
		return a;
	}
	
	@RequestMapping("/selectCount")
	@ResponseBody
	public int selectCount() {
		int a = orderInfoService.selectCount();
		return a;
	}
	
	@RequestMapping("/selectModelPage")
	@ResponseBody
	public Layui selectModelPage(@RequestParam("page")int page,@RequestParam("limit")int limit,String userCode,String custCode) {
		OrderInfoModel com = new OrderInfoModel();
		com.setUserCode(userCode);
		com.setCustCode(custCode);
		
		int before = limit * (page-1);
		int after = page * limit;
		
		List<OrderInfoModel> list = orderInfoService.selectModelPage(com, before, after);
		int count = orderInfoService.selectCount();
		
		return Layui.data(count, list);
	}
	
	
	@RequestMapping("/selectModel")
	@ResponseBody
    public OrderInfoModel selectModel(String userCode) {
    	
    	System.out.println("cus"+userCode);
    	OrderInfoModel order = new OrderInfoModel();
    	order.setUserCode(userCode);
    	
    	List<OrderInfoModel> list = orderInfoService.selectModel(order);
    	OrderInfoModel com = new OrderInfoModel();
    	for(OrderInfoModel aa:list) {
    		com.setUserCode(aa.getUserCode());
    		com.setCustCode(aa.getCustCode());
    		com.setTime(aa.getTime());
    		com.setProdCode(aa.getProdCode());
    		com.setCount(aa.getCount());
    		com.setStatus(aa.getStatus());
    		
    	}
    	return com;
    }
	
	//导出导入excle表格

	@RequestMapping("/excle")
	public void exportExcel(HttpServletResponse response) throws Exception {

		List<OrderInfoModel> list = orderInfoService.selectAll(new OrderInfoModel());
		List<String> propList = Arrays.asList("userCode","custCode", "prodCode", "count", "time","status");
		List<String> fieldName = Arrays.asList("用户编号", "客户编号","商品编号","数量", "时间","状态");
		Workbook wb = FormatPOI.exportExcel(list, propList, fieldName);

		// 1)、设置响应的头文件，会自动识别文件内容
		response.setContentType("multipart/form-data");
		// 2)、设置Content-Disposition
		response.setHeader("Content-Disposition", "attachment;filename=order.xlsx");
		// 3)、输出流
		OutputStream out = response.getOutputStream();
		wb.write(out);
		wb.close();
		out.close();
	}
	
	@RequestMapping("/model")
	public void model(HttpServletResponse response) throws Exception {
		List<String> fieldName = Arrays.asList("用户编号", "客户编号","商品编号","数量", "时间","状态");
		Workbook wb = FormatPOI.moban(fieldName);
		// 1)、设置响应的头文件，会自动识别文件内容
		response.setContentType("multipart/form-data");
		// 2)、设置Content-Disposition
		response.setHeader("Content-Disposition", "attachment;filename=orderModel.xlsx");
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
	private List<OrderInfoModel> parse(InputStream fis) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("user");
		List<OrderInfoModel> list = new ArrayList<>();
		for (Row row : sheet) {
			if (0 == row.getRowNum()) {
				continue;
			}
			OrderInfoModel product = new OrderInfoModel();
			product.setUserCode(getValue(row.getCell(0)));
			product.setCustCode(getValue(row.getCell(1)));
			product.setProdCode(getValue(row.getCell(2)));
			product.setCount(Integer.parseInt(getValue(row.getCell(3))));
			product.setTime(getValue(row.getCell(4)));
			product.setStatus(getValue(row.getCell(5)));
			list.add(product);
			orderInfoService.insert(product);
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

	private String usssss(){
		System.out.println("12121212");
	}
}
