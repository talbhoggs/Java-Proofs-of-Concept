package com.daniel.service;

import javax.transaction.Transaction;

import org.hibernate.HibernateException;
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

		Configuration config = new Configuration().configure()
				.addAnnotatedClass(com.daniel.models.Employee.class)
				.addAnnotatedClass(com.daniel.models.House.class)
				.addAnnotatedClass(com.daniel.models.Resident.class);

		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings(config.getProperties()).buildServiceRegistry();

		sf = config.buildSessionFactory(serviceRegistry);

	}

	public void addEmployee(String name) {
		Employee e = new Employee();

		e.setName(name);

		Session s = sf.getCurrentSession();

		s.beginTransaction();

		s.save(e);

		s.getTransaction().commit();

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
	
	public void addAppleStore(AppleStore as) {
		
		Session session = sf.getCurrentSession();
		
		session.beginTransaction();
		
		session.save(as);
		
		session.getTransaction().commit();
		
	}
	
	public void addHouse(House h) {
	
		Session session = sf.getCurrentSession();
		
		session.beginTransaction();
		
		session.save(h);
		
		session.getTransaction().commit();
		
	}
	
	public void addMansion(Mansion m) {
		
		Session session = sf.getCurrentSession();
		
		session.beginTransaction();
		
		session.save(m);
		
		session.getTransaction().commit();
		
	}

	public void addMovieTx(Integer id, String director, String title,
			String synopsis) {

		Movie movie = new Movie();

		movie.setId(id);
		movie.setDirector(director);
		movie.setTitle(title);
		movie.setSynopsis(synopsis);

		Movie movie0 = new Movie();

		movie0.setId(101);
		movie0.setDirector("d");
		movie0.setTitle("t");
		movie0.setSynopsis("s");

		Session session = sf.getCurrentSession();

		org.hibernate.Transaction tx = session.beginTransaction();

		try {

			session.save(movie0);
			session.save(movie);

			tx.commit();
		} catch (HibernateException e) {
			System.out.println("Caught an hibernate exception.");
			if (tx != null) {
				System.out.println("Rolling back the transaction.");
				tx.rollback();
			}
		}

	}

}
