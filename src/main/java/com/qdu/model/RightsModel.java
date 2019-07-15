package com.qdu.model;

public class RightsModel {

	private Integer id;
	private String roleCode;
	private String menuCode;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	
	@Override
	public String toString() {
		return "RightsModel [id=" + id + ", roleCode=" + roleCode + ", menuCode=" + menuCode + "]";
	}
	
	
	
}
