package com.daniel.models;

import java.util.List;

public class AppleStore {
	
	private int id = 0;
	private String manager = null;
	private List<Good> goods = null;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public List<Good> getGoods() {
		return goods;
	}
	public void setGoods(List<Good> goods) {
		this.goods = goods;
	}
	
	

}
