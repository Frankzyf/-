package com.qdu.model;

public class OrderInfoModel {
	
	private Integer id;
	private String  userCode;
	private String custCode;
	private String prodCode;
	private int  count;
	private String time;
	private String status;
	
	
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
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProdCode() {
		return prodCode;
	}
	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "OrderInfoModel [id=" + id + ", UserCode=" + userCode + ", CustCode=" + custCode + ", prodCode="
				+ prodCode + ", count=" + count + ", time=" + time + ", status=" + status + "]";
	}
	
	

}
