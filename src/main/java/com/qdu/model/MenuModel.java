package com.qdu.model;

import java.util.ArrayList;
import java.util.List;

public class MenuModel {

	private Integer id;
	private String menuCode;
	private String menuName;
	private String menuUrl;
	private String parentCode;
	
	private List<MenuModel> child = new ArrayList<>();
	
	
	
	public List<MenuModel> getChild() {
		return child;
	}
	public void setChild(List<MenuModel> child) {
		this.child = child;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	@Override
	public String toString() {
		return "MenuModel [id=" + id + ", menuCode=" + menuCode + ", menuName=" + menuName + ", menuUrl=" + menuUrl
				+ ", parentCode=" + parentCode + ", child=" + child + "]";
	}
	
	
	
}
