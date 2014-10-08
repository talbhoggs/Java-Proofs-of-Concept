package com.daniel.service;

import javax.annotation.*;
import javax.ejb.*;
import java.util.*;

@Stateful(name = "ShoppingCart")
public class ShoppingCart implements ShoppingCartLocal {

	private int num = 0;
	private String shopperId = "Shopping cart not setup yet...";

	public ShoppingCart() {
	}

	public void setShopperId() {
		this.shopperId = (new Random()).nextInt(10001) + "";
	}

	public String getShopperId() {
		return this.shopperId;
	}

	public void incrementNum() {
		this.num++;
	}
	
	public Integer getNum() {
		return this.num;
	}

	@PostConstruct
	public void createdBean() {
		System.out.println("Constructed stateful bean");
	}

	@PreDestroy
	public void destroyBean() {
		System.out.println("stateful bean about to be destroyed");
	}
}
