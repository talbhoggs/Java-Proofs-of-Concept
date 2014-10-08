package com.daniel;

import javax.ejb.*;
import java.util.*;

@Stateless(name = "WineSearch")
public class WineSearchEJB implements WineSearchRemote, WineSearchLocal {

	public WineSearchEJB() {

	}

	public List wineSearch(String wineType) {

		List wineList = new ArrayList<String>();

		if (wineType.equals("Red")) {
			wineList.add("Red wine 1");
			wineList.add("Red wine 2");
			wineList.add("Red wine 3");
			wineList.add("Red wine 4");
			return wineList;
		} else if (wineType.equals("White")) {
			wineList.add("White wine 1");
			wineList.add("White wine 2");
			wineList.add("White wine 3");
			wineList.add("White wine 4");
			return wineList;
		} else {;
			return null;
		}
	}

}
