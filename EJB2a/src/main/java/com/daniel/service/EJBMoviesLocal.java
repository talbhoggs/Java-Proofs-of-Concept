package com.daniel.service;

import java.util.List;

import javax.ejb.*;

import com.daniel.models.Movie;

@Local
public interface EJBMoviesLocal {

	public void addMovie(Movie movie);

	public void deleteMovie(Movie movie);

	public List<Movie> getMovies();
}
