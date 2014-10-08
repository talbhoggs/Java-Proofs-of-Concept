package com.daniel.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.daniel.models.*;

public class MovieService {

	private SessionFactory sf = null;

	public MovieService() {
		initHib4();
	}

	private void initHib4() {


		Configuration config = new Configuration().configure();		

        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                config.getProperties()).buildServiceRegistry();

        sf = config.buildSessionFactory(serviceRegistry);

	}

	public void addMovie(Integer id, String director, String title,
			String synopsis) {

		Movie movie = new Movie();

		movie.setId(id);
		movie.setDirector(director);
		movie.setTitle(title);
		movie.setSynopsis(synopsis);

		Session session = sf.getCurrentSession();

		session.beginTransaction();

		session.save(movie);

		session.getTransaction().commit();

	}

}
