package com.daniel;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.List;
import com.daniel.models.*;

@Stateful
public class EJBMovies {

    @PersistenceContext(unitName = "movieBook", type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    public void addMovie(Movie movie) {
        entityManager.persist(movie);
    }

    public void deleteMovie(Movie movie) {
        entityManager.remove(movie);
    }

    public List<Movie> getMovies() {
        Query query = entityManager.createQuery("SELECT m from Movie as m");
        return query.getResultList();
    }

}