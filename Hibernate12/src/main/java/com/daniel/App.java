package com.daniel;

import com.daniel.service.*;


import java.util.*;

import com.daniel.models.*;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");

		MovieService ms = new MovieService();

		/*
		 * // ms.addMovie(1, "a director", "X Men Days of Future Past", //
		 * "Super great movie I'll watch after I'll come back from Europe");
		 * ms.addMovieTx(11, "a director 2", "How to Train your Dragon",
		 * "Good movie I'll watch after I'll come back from Europe");
		 * 
		 * ms.addEmployee("Daniel");
		 * 
		 * Map<String, Good> goodsMap = new HashMap<String, Good>();
		 * 
		 * Good g1 = new Good(); g1.setName("iPhone 5c"); g1.setColor("blue");
		 * 
		 * Good g2 = new Good(); g2.setName("Macbook Air");
		 * g2.setColor("silver");
		 * 
		 * Good g3 = new Good(); g3.setName("Mac Mini"); g3.setColor("silver");
		 * 
		 * goodsMap.put("iPhone 5c 1234", g1); goodsMap.put("Macbook Air 5678",
		 * g2); goodsMap.put("Mac Mini 9012", g3);
		 * 
		 * AppleStore as = new AppleStore(); as.setManager("Manager");
		 * as.setGoods(goodsMap);
		 * 
		 * ms.addAppleStore(as);
		 * 
		 * 
		 * House h = new House();
		 * 
		 * h.setAddress("Mountain View, CA");
		 * 
		 * List<com.daniel.models.Resident> residentList = new
		 * ArrayList<com.daniel.models.Resident>();
		 * 
		 * Resident r1 = new Resident(); r1.setName("Resident 1");
		 * 
		 * Resident r2 = new Resident(); r2.setName("Resident 2");
		 * 
		 * Resident r3 = new Resident(); r3.setName("Resident 3");
		 * 
		 * Resident r4 = new Resident(); r4.setName("Resident 4");
		 * 
		 * residentList.add(r1); residentList.add(r2); residentList.add(r3);
		 * residentList.add(r4);
		 * 
		 * h.setLr(residentList);
		 * 
		 * ms.addHouse(h);
		 

		Mansion m = new Mansion();

		m.setAddress("Beverley Hills, CA");
		
		Aristocrat a = new Aristocrat();
		
		a.setName("A 1");
		
		Aristocrat b = new Aristocrat();
		
		b.setName("A 2");
		
		Aristocrat c = new Aristocrat();
		
		c.setName("A 3");
		
		Aristocrat d = new Aristocrat();
		
		d.setName("A 4");
		
		Set<Aristocrat> aset = new HashSet<Aristocrat>();
		
		aset.add(a);
		aset.add(b);
		aset.add(c);
		aset.add(d);
		
		m.setA(aset);
		
		ms.addMansion(m);
	*/
		/*
		Girl g = new Girl();
		
		g.setName("Erica");
		
		Girl g2 = new Girl();
		
		g2.setName("Esther");
		
		
		Toy t1 = new Toy();
		t1.setName("Coloring Book");

		Toy t2 = new Toy();
		t2.setName("Makeup Kit");
		
		Set<Toy> ts = new HashSet<Toy>();

		ts.add(t1);
		ts.add(t2);
		
		g.setToys(ts);
		
		//ms.addGirl(g);
		
		Toy t3 = new Toy();
		t3.setName("Science book");
		
		Set<Girl> gs = new HashSet<Girl>();
		
		gs.add(g);
		gs.add(g2);
		
		t3.setGirls(gs);
		
		ms.addToy(t3);
	*/
		
		//ms.getToys(3);
		//ms.getParametrizedToys("3");
		//ms.getSeveralToysWithINoption();
		//ms.getToysIterator();
		ms.updateToy(1, "new Science Book");
		
	}
	
}
