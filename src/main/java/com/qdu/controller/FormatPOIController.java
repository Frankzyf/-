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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.qdu.model.UserModel;
import com.qdu.service.UserService;
import com.qdu.utils.FormatPOI;



@Controller
@RequestMapping("/poi")
public class FormatPOIController {
	@Autowired
	UserService userService;

	@RequestMapping("/excleUser")
	public void exportExcel(HttpServletResponse response) throws Exception {

		List<UserModel> list = userService.selectAllUser();
		List<String> propList = Arrays.asList("userCode", "userPass", "userName", "roleCode", "parentCode");
		List<String> fieldName = Arrays.asList("账号", "密码", "用户名", "角色类别", "上级编号");
		Workbook wb = FormatPOI.exportExcel(list, propList, fieldName);

		// 1)、设置响应的头文件，会自动识别文件内容
		response.setContentType("multipart/form-data");
		// 2)、设置Content-Disposition
		response.setHeader("Content-Disposition", "attachment;filename=user.xlsx");
		// 3)、输出流
		OutputStream out = response.getOutputStream();
		wb.write(out);
		wb.close();
		out.close();
	}
	
	@RequestMapping("/modelUser")
	public void model(HttpServletResponse response) throws Exception {
		List<String> fieldName = Arrays.asList("账号", "密码", "用户名", "角色类别", "上级编号");
		Workbook wb = FormatPOI.moban(fieldName);
		// 1)、设置响应的头文件，会自动识别文件内容
		response.setContentType("multipart/form-data");
		// 2)、设置Content-Disposition
		response.setHeader("Content-Disposition", "attachment;filename=user.xlsx");
		// 3)、输出流
		OutputStream out = response.getOutputStream();
		wb.write(out);
		wb.close();
		out.close();
	}
	
	@ResponseBody
	@RequestMapping(value = "/uploadUser", produces = "application/json;charset=UTF-8")
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
	private List<UserModel> parse(InputStream fis) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("user");
		List<UserModel> list = new ArrayList<>();
		for (Row row : sheet) {
			if (0 == row.getRowNum()) {
				continue;
			}
			UserModel userModel = new UserModel();
			userModel.setUserCode(getValue(row.getCell(0)));
			userModel.setUserPass(getValue(row.getCell(1)));
			userModel.setUserName(getValue(row.getCell(2)));
			userModel.setRoleCode(getValue(row.getCell(3)));
			userModel.setParentCode(getValue(row.getCell(4)));
			list.add(userModel);
		    userService.addUser(userModel);
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
			return String.valueOf(cell.getNumericCellValue());
		}
		return null;
	}

}
