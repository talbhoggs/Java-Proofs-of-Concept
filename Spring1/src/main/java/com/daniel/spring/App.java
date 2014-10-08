package com.daniel.spring;

import org.springframework.context.ApplicationContext; 
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
        
        ApplicationContext context = new ClassPathXmlApplicationContext("com/daniel/spring/beans/beans.xml");
        
        Person p = (Person) context.getBean("person");
        
        System.out.println(p);
        
        CarDealership cd = (CarDealership) context.getBean("cardealership");
        System.out.print(cd);
        
        System.out.println("The best car is: ");
        System.out.println(cd.getBestCar());
        
        System.out.println("The person2 map of car dealership workers:");
        System.out.println(cd.people2);
        
        ( (ClassPathXmlApplicationContext) context ).close();
         
        
    }
}
