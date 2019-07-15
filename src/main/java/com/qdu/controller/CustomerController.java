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

import com.qdu.model.CustomerModel;
import com.qdu.service.CustomerService;
import com.qdu.utils.FormatPOI;
import com.qdu.utils.Layui;

@Controller
@RequestMapping("/cus")
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	@RequestMapping("/insert")
	@ResponseBody
	public int insert(CustomerModel customer) {
		int a = customerService.insert(customer);
		System.out.println("a");
		return a;
	}
	
	@RequestMapping("/deleteModel")
	@ResponseBody
	public int deleteModel(CustomerModel customer) {
		int a = customerService.deleteModel(customer);
		return a;
	}
	
	@RequestMapping("/updateActive")
	@ResponseBody
	public int updateActive(CustomerModel customer) {
		int a = customerService.updateActive(customer);
		System.out.println("a");
		return a;
	}
	
	@RequestMapping("/selectCount")
	@ResponseBody
	public int selectCount() {
		int a = customerService.selectCount();
		System.out.println("a");
		return a;
	}
	
	@RequestMapping("/selectModelPage")
	@ResponseBody
	public Layui selectModelPage(@RequestParam("page")int page,@RequestParam("limit")int limit,String custCode,String custName) {
		CustomerModel customer = new CustomerModel();
		customer.setCustCode(custCode);
		customer.setCustName(custName);
		
		int before = limit * (page-1);
		int after = page * limit;
		
		List<CustomerModel> list = customerService.selectModelPage(customer, before, after);
		int count = customerService.selectCount();
		
		return Layui.data(count, list);
	}
	
	
	@RequestMapping("/selectModel")
	@ResponseBody
    public CustomerModel selectModel(String custCode) {
    	
    	System.out.println("cus"+custCode);
    	CustomerModel cust = new CustomerModel();
    	cust.setCustCode(custCode);
    	
    	List<CustomerModel> list = customerService.selectModel(cust);
    	CustomerModel customer = new CustomerModel();
    	for(CustomerModel aa:list) {
    		
    		customer.setCustCode(aa.getCustCode());
    		customer.setCustName(aa.getCustName());
    		customer.setStatus(aa.getStatus());
    		customer.setEmail(aa.getEmail());
    	}
    	return customer;
    }
	
	//导出导入excle表格

		@RequestMapping("/excle")
		public void exportExcel(HttpServletResponse response) throws Exception {

			List<CustomerModel> list = customerService.selectAll(new CustomerModel());
			List<String> propList = Arrays.asList("custCode", "custName", "status", "email");
			List<String> fieldName = Arrays.asList("用户编号", "用户名", "状态", "邮件");
			Workbook wb = FormatPOI.exportExcel(list, propList, fieldName);

			// 1)、设置响应的头文件，会自动识别文件内容
			response.setContentType("multipart/form-data");
			// 2)、设置Content-Disposition
			response.setHeader("Content-Disposition", "attachment;filename=customer.xlsx");
			// 3)、输出流
			OutputStream out = response.getOutputStream();
			wb.write(out);
			wb.close();
			out.close();
		}
		
		@RequestMapping("/model")
		public void model(HttpServletResponse response) throws Exception {
			List<String> fieldName = Arrays.asList("用户编号", "用户名", "状态", "邮件");
			Workbook wb = FormatPOI.moban(fieldName);
			// 1)、设置响应的头文件，会自动识别文件内容
			response.setContentType("multipart/form-data");
			// 2)、设置Content-Disposition
			response.setHeader("Content-Disposition", "attachment;filename=customerModel.xlsx");
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
		private List<CustomerModel> parse(InputStream fis) throws IOException {
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("user");
			List<CustomerModel> list = new ArrayList<>();
			for (Row row : sheet) {
				if (0 == row.getRowNum()) {
					continue;
				}
				CustomerModel product = new CustomerModel();
				product.setCustCode(getValue(row.getCell(0)));
				product.setCustName(getValue(row.getCell(1)));
				product.setStatus(getValue(row.getCell(2)));
				product.setEmail(getValue(row.getCell(3)));
				list.add(product);
				customerService.insert(product);
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
