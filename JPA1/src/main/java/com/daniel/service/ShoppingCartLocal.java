package com.daniel.service;

import javax.ejb.*;

@Local
public interface ShoppingCartLocal {
	
	public Integer getNum();
	public void incrementNum();
	public void setShopperId();
	public String getShopperId();
	
}
