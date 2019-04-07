package es.iessaladillo.pedrojoya.movieadvisor.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.iessaladillo.pedrojoya.movieadvisor.model.Movie;

// Clase con el estereotipo Repository (por tanto es un bean) que representa
// el servicio de ataque a la fuente de datos de películas.

@Repository
public class MovieDaoImp implements MovieDao {
	
	private final List<Movie> movies;	
	
	@Autowired	
	public MovieDaoImp(MovieDataSource movieDataSouce) {
		this.movies = movieDataSouce.provideAllMovies();
	}
	
//	@PostConstruct
//	public void init() {
//	}

	@Override
	public Optional<Movie> queryById(long id) {
		return movies.stream()
				.filter(movie -> movie.getId() == id)
				.findFirst();
	}

	@Override
	public Collection<Movie> queryAll() {
		return new ArrayList<>(movies);
	}

	@Override
	public void insert(Movie movie) {
		movies.add(movie);
		
	}

	@Override
	public void update(Movie movie) {
		searchMovieById(movie.getId())
		.ifPresent(index -> movies.set(index, movie));
	}

	@Override
	public void delete(long id) {
		searchMovieById(id)
		.ifPresent(index -> movies.remove(index.intValue()));
	}
	
	private Optional<Integer> searchMovieById(long id) {
		int listSize = movies.size();
		for (int i = 0; i < listSize; i++) {
			if (movies.get(i).getId() == id) {
				return Optional.of(i);
			}
		}
		return Optional.empty();
	}
	
}
