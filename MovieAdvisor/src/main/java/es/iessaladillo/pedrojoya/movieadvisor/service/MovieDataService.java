package es.iessaladillo.pedrojoya.movieadvisor.service;

import java.util.Collection;

import es.iessaladillo.pedrojoya.movieadvisor.model.Movie;

// Interface de alto nivel para trabajo con datos de películas.

public interface MovieDataService {

	public Collection<String> findAllGenres();
	public Collection<Movie> findByAnyGenre(String... genres);
	public Collection<Movie> findByAllGenres(String... genres);
	public Collection<Movie> findByYear(int year);
	public Collection<Movie> findBetweenYears(int from, int to);
	public Collection<Movie> findByTitleContains(String title);
	public Collection<Movie> findAll();
	public Collection<Movie> execute(MovieQuery movieQuery);
	
}
