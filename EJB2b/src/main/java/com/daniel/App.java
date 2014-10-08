package com.daniel;

import com.daniel.service.*;
import com.daniel.models.*;

import java.util.*;

import com.daniel.models.*;
import javax.ejb.*;
import javax.naming.*;
import javax.ejb.embeddable.*;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");


		final Context context = EJBContainer.createEJBContainer().getContext();
		EJBMovies movies = null;
		try {
			movies = (EJBMovies) context
					.lookup("java:global/jpa-hibernate/Movies");
		} catch (NamingException e) {
			System.out.println("A naming exception has occured...");
		}

		movies.addMovie(new Movie("Quentin Tarantino", "Reservoir Dogs", "1992"));
		movies.addMovie(new Movie("Joel Coen", "Fargo", "1996"));
		movies.addMovie(new Movie("Joel Coen", "The Big Lebowski", "1998"));

		List<Movie> list = movies.getMovies();

	}

}
