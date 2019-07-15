package com.qdu.model;

public class UserModel {
	private Integer id;
	private String userCode;
	private String userPass;
	private String userName;
	private String roleCode;
	private String parentCode;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	
	@Override
	public String toString() {
		return "UserModel [id=" + id + ", userCode=" + userCode + ", userPass=" + userPass + ", userName=" + userName
				+ ", roleCode=" + roleCode + ", parentCode=" + parentCode + "]";
	}
	
	

}
