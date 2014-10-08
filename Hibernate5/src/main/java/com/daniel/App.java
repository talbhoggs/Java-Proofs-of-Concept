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

		// ms.addMovie(1, "a director", "X Men Days of Future Past",
		// "Super great movie I'll watch after I'll come back from Europe");
		ms.addMovieTx(11, "a director 2", "How to Train your Dragon",
				"Good movie I'll watch after I'll come back from Europe");

		ms.addEmployee("Daniel");

		Map<String, Good> goodsMap = new HashMap<String, Good>();

		Good g1 = new Good();
		g1.setName("iPhone 5c");
		g1.setColor("blue");

		Good g2 = new Good();
		g2.setName("Macbook Air");
		g2.setColor("silver");

		Good g3 = new Good();
		g3.setName("Mac Mini");
		g3.setColor("silver");

		goodsMap.put("iPhone 5c 1234", g1);
		goodsMap.put("Macbook Air 5678", g2);
		goodsMap.put("Mac Mini 9012", g3);

		AppleStore as = new AppleStore();
		as.setManager("Manager");
		as.setGoods(goodsMap);
		
		ms.addAppleStore(as);

	}
}
