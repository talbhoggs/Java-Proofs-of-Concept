package com.daniel;

import org.springframework.context.ApplicationContext; 
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
	
		
		 ApplicationContext context = new ClassPathXmlApplicationContext("com/daniel/beans/beans.xml");
	        
	     Aquarium aquarium = (Aquarium)context.getBean("aquarium");
		 
	     aquarium.tunaSwim("I'm a tuna swimming");
	     aquarium.catfishSwim("I'm a catfish swimming");
	     
		 
		 ( (ClassPathXmlApplicationContext) context ).close();
	         
		
	}

}
;