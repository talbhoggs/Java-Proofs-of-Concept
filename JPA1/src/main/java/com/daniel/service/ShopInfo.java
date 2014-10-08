package com.daniel.service;

import javax.annotation.*;
import javax.ejb.*;

@Singleton(name = "ShopInfo")
public class ShopInfo {
	
	//@Resource
	//private TimerService timer;

	private int numVisitors = 0;
	private String shopStatus = "Vacancy";
	
	//@Timeout //this will only happen once 30 seconds after bean is loaded
	//public void announceComercial(Timer tO0bject) {
	//	System.out.println("Ladies and gentlemen, this time only shoes are now 50% off in price!");
	//}
	
	//@Schedule(minute="*", hour="*") // repeat once per minute every hour of the day
	public void announcePeriodicCommercial() {
		System.out.println("Our prices are cheap as always!");
	}

	public ShopInfo() {
		//Timer t = timer.createTimer(30000, "This is the commerical timer");
	}

	@Lock(LockType.READ)
	public int getNumVisitors() {
		return numVisitors;
	}

	@Lock(LockType.WRITE)
	public void setNumVisitors(int numVisitors) {
		this.numVisitors = numVisitors;
	}
	
	@Lock(LockType.WRITE)
	public void addVisitors(Integer num) {
		num = this.getNumVisitors() + num;
		if(num > 25) {
			this.setShopStatus("No vacancy");
		}
		else {
			this.setShopStatus("Vacancy");
		}
		this.setNumVisitors(num);
	}
	
	//If another client is using this method, wait up to 2 minutes until it is released, 
	//and if it's still in use and now available after the 2 minute wait, then throw a ConcurentAccessTimeoutException
	@Lock(LockType.WRITE)
	@AccessTimeout(value = 120000)
	public void removeVisitors(Integer num) {
		num = this.getNumVisitors() - num;
		this.setNumVisitors(num);
	}

	@Lock(LockType.READ)
	public String getShopStatus() {
		return shopStatus;
	}

	@Lock(LockType.WRITE)
	public void setShopStatus(String shopStatus) {
		this.shopStatus = shopStatus;
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
