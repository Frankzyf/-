package com.qdu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qdu.model.MenuModel;
import com.qdu.model.UserModel;
import com.qdu.service.MenuService;
import com.qdu.service.UserService;
import com.qdu.utils.BaiKe;
import com.qdu.utils.FmtEmpty;
import com.qdu.utils.Layui;


@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	MenuService menuService;

	
	/**
	 * 添加用户
	 * @param username
	 */
	@RequestMapping("/add")
	@ResponseBody
	public int addUser(UserModel userModel) {

		int a = 0;
		if(!StringUtils.isEmpty(userModel)) {
			//1、先从数据库查询用户是否存在
		     boolean mm = userService.selectUser(userModel);
		     if(mm==true) {
		    	 a=1;
		     }else {
		    	//2、没有的话插入
		 		userService.addUser(userModel);
		 		a=0;
		     }
		}
		return a;
	}
	
	
	/**
	 * 删除用户
	 * @param userCode
	 */
	@RequestMapping("/deleteUser")
	@ResponseBody
	public String deletMenu(String userCode) {
		userService.delecteUser(userCode);
		
		return "ww";
	}
	
	/**
	 * 更新
	 * @param menuModel
	 */
	@RequestMapping("/update")
	@ResponseBody
	public void update(UserModel userModel) {
		//System.out.println("userModel"+userModel);
		userService.update(userModel);
	}
	
	/**
	 * 按照code查找用户
	 * @param userCode
	 * @return
	 */
	@RequestMapping("/selectByCode")
	@ResponseBody
	public UserModel selectByCode(String userCode) {
		
		UserModel mu = userService.selectByCode(userCode);
		return mu;
	}
	
	
	/**
	 * 查找所有用户
	 * @return
	 */
	@RequestMapping("/selectAll")
	@ResponseBody
	public Layui selectAll(@RequestParam("page")int page,@RequestParam("limit")int limit,UserModel user) {
		
		//System.out.println(user);
		
		int before = limit * (page-1);
		int after = page * limit;
		List<UserModel>list =  userService.selectAll(before,after,user);
		int count = userService.count();
		
		return Layui.data(count, list) ;
	}
	
    /**
     * 用户登录
     * @param userCode
     * @param userPass
     * @return
     */
	@RequestMapping("/login")
	public String Login(String userCode,String userPass,HttpSession session,Model model) {
		System.out.println(userCode+userPass);
		//设置一个标记
		String sign = "";
		UserModel user = new UserModel();
		if(!StringUtils.isEmpty(userCode) && !StringUtils.isEmpty(userPass)) {
			user.setUserCode(userCode);
			user.setUserPass(userPass);
		    boolean AA = userService.selectUser(user);
		   // System.out.println("AA:"+AA);
		    if(AA==true) {
		    	//向session中插入用户登录信息
				session.setAttribute("user",user);
		    	System.out.println("登录成功");
			    //通过userCode获得用户权限
			    List<MenuModel> list = selectMenu(userCode);
			    model.addAttribute("MenuModel",list);
			    sign="manage";
		    }else {
		    	System.out.println("用户不存在！");
		    	sign="Login";
		    }	    
		}else {
			System.out.println("用户名和密码不能为空");
			sign="Login";
		}
		return sign;	
	}
	
	//调用此方法返回该用户可操作菜单List
	public List<MenuModel> selectMenu(String userCode){
		List<MenuModel> menu = menuService.getMenu(userCode);
		
		if(StringUtils.isEmpty(menu)) {
			return null;
		}
		List<MenuModel> result = new ArrayList<>();
		for(MenuModel rel:menu) {
			MenuModel menuModel = rel;
			String parentCode = menuModel.getParentCode();
			if(StringUtils.isEmpty(parentCode)||"00".equals(parentCode)) {
				result.add(menuModel);
				
				continue;
			}
			for(MenuModel m:result) {
				if(m.getMenuCode().equals(parentCode)) {
					m.getChild().add(menuModel);
				}
			}
		}
		
		//System.out.println("result:"+result);
		return result;
	}
	
	//爬虫
	@RequestMapping("/toBaiKe")
	public String toBaiKe() {
		return "BaiKe";
	}
	@ResponseBody
	@RequestMapping(value="/item",produces="application/json;charset=UTF-8")
	public String item(String item) {
		if(FmtEmpty.isEmpty(item)) {
			return "";
		}try {
			return new BaiKe().getContent2(BaiKe.baseUrl+item);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "";	
	} 

}
