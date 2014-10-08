package com.daniel;

import javax.ejb.Local;
import java.util.*;

@Local
public interface WineSearchLocal {

	List wineSearch(String wineType);
	
}
