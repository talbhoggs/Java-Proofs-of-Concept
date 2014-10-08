package com.daniel;

import javax.ejb.Remote;

import java.util.*;

@Remote
public interface WineSearchRemote {

	List wineSearch(String wineType);
	
}
