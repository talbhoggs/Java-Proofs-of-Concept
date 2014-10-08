package com.daniel.service;

import javax.annotation.*;
import javax.ejb.*;
import javax.persistence.*;

import java.util.*;

import com.daniel.models.*;

import javax.interceptor.*;

/**
 * Session Bean implementation class SimpleUsersEJB
 */
@Stateless(name="SimpleUsers")
public class SimpleUsersEJB implements SimpleUsersEJBLocal {

	@PersistenceContext
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public SimpleUsersEJB() {
        // TODO Auto-generated constructor stub
    }
    
    @PreDestroy
    private void preDestroyMsg() {
    	System.out.println("Oh no I've reached the end of my time in the computer's memory...");
    }

    @PostConstruct
    private void postConstructMsg() {
    	System.out.println("I'm a stateless EJB and I'm all setup in memory...");
    }
    
    @AroundInvoke
    public Object whoIsCalled(InvocationContext ctx) throws Exception {
    	System.out.println("I just invoked: " + ctx.getMethod().getName());
    	return ctx.proceed();
    }
    
    public List getUsers() {
    	List users = em.createQuery("select u from SimpleUser u").getResultList();
    	return users;
    }
    
    public String getName(int id) {
    	SimpleUser sm = (SimpleUser)getUsers().get(id); //simple get from a list
    	return sm.getName();
    }
    
    public Integer getAge(int id) {
    	SimpleUser sm = (SimpleUser)getUsers().get(id); //simple get from a list
    	return sm.getAge();
    }

}