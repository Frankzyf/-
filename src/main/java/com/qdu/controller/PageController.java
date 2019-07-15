package com.qdu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {
	@RequestMapping("/manage")
	public String Hello111() {
		return "manage";
	}
	

	@RequestMapping("/menu")
	public String Hello() {
		return "menu";
	}
	@RequestMapping("/login")
	public String Hello2() {
		return "Login";
	}
	
	@RequestMapping("/role")
	public String Hello3() {
		return "role";
	}
	
	@RequestMapping("/rights")
	public String Hello4() {
		return "rights";
	}
	
	@RequestMapping("/user")
	public String Hello5() {
		return "user";
	}
	
	@RequestMapping("/cus")
	public String Hello6() {
		return "customer";
	}
	
	@RequestMapping("/com")
	public String Hello7() {
		return "communication";
	}
	
	@RequestMapping("/pro")
	public String Hello8() {
		return "product";
	}
	
	@RequestMapping("/order")
	public String Hello9() {
		return "order";
	}
	
	@RequestMapping("/chart")
	public String Hello11() {
		return "chart";
	}
	
}
