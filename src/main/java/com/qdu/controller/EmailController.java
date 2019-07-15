package com.qdu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qdu.utils.FmtMail;

@Controller
@RequestMapping("/email")
public class EmailController {

	@RequestMapping("/send")
	@ResponseBody
	public String sendEail(String to,String theme,String content ) throws Exception {
		
		System.out.println(theme);
		System.out.println(content);
		String[] aa = to.split(",");
		FmtMail email = new FmtMail(aa,"624717080@qq.com","tzwmawhvzedibege","smtp.qq.com","587");
		email.send(theme, content);
		return null;
	}
}
