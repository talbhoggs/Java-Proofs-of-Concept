package com.daniel.service;

import java.util.List;

import javax.ejb.Local;

import com.daniel.models.SimpleUser;

@Local
public interface SimpleUsersEJBLocal {
	
	public List getUsers();
    
    public String getName(int id);
    
    public Integer getAge(int id);
	
}
