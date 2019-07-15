package com.qdu.model;

public class ProductModel {

	private Integer id;
	private String code;
	private String name;
	private int    sum;
	private int    cost;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	@Override
	public String toString() {
		return "ProductModel [id=" + id + ", code=" + code + ", name=" + name + ", sum=" + sum + ", cost=" + cost + "]";
	}
	
	
}
