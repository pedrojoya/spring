package es.iessaladillo.pedrojoya.movieadvisor.dao;

import java.util.Collection;
import java.util.Optional;

import es.iessaladillo.pedrojoya.movieadvisor.model.Movie;

// Interfaz de ataque contra la base de datos de películas.

public interface MovieDao {

	public Optional<Movie> queryById(long id);
	
	public Collection<Movie> queryAll();
	
	public void insert(Movie movie);

	public void update(Movie movie);
	
	public void delete(long id);
	
}
