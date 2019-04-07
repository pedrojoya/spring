package es.iessaladillo.pedrojoya.movieadvisor.dao;

import java.util.List;

import es.iessaladillo.pedrojoya.movieadvisor.model.Movie;

public interface MovieDataSource {

	List<Movie> provideAllMovies();
	
}
