package daniel.models;

import java.util.ArrayList;

public class Customer {
	
	public String name;
	public int age;
	public boolean flag;
	public String address;
	public Purchases purchases;
	public String zoom;
	
	public String getZoom() {
		return zoom;
	}
	public void setZoom(String zoom) {
		this.zoom = zoom;
	}
	public Purchases getPurchases() {
		return purchases;
	}
	public void setPurchases(Purchases purchases) {
		this.purchases = purchases;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	

}
