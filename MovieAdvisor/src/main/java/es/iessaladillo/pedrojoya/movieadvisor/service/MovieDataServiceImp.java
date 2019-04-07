package es.iessaladillo.pedrojoya.movieadvisor.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iessaladillo.pedrojoya.movieadvisor.dao.MovieDao;
import es.iessaladillo.pedrojoya.movieadvisor.model.Movie;

@Service
public class MovieDataServiceImp implements MovieDataService {

	private final MovieDao movieDao;
	
	@Autowired	
	public MovieDataServiceImp(MovieDao movieDao) {
		this.movieDao = movieDao;
	}
	
	@Override
	public Collection<String> findAllGenres() {
		return movieDao.queryAll().stream()
				.flatMap(movie -> movie.getGenres().stream())
				.distinct()
				.sorted()
				.collect(Collectors.toList());			
	}

	@Override
	public Collection<Movie> findByAnyGenre(String... genres) {
		return execute(new MovieQuery.Builder()
				.ofAnyGenre(genres).build());
	}

	@Override
	public Collection<Movie> findByAllGenres(String... genres) {
		return execute(new MovieQuery.Builder()
				.ofAllGenres(genres).build());
	}

	@Override
	public Collection<Movie> findByYear(int year) {
		return execute(new MovieQuery.Builder()
				.withYear(year).build());
	}

	@Override
	public Collection<Movie> findBetweenYears(int from, int to) {
		return execute(new MovieQuery.Builder()
				.betweenYears(from, to).build());
	}

	@Override
	public Collection<Movie> findByTitleContains(String title) {
		return execute(new MovieQuery.Builder()
				.withTitle(title).build());
	}

	@Override
	public Collection<Movie> findAll() {
		return execute(new MovieQuery.Builder().build());
	}
	
	public Collection<Movie> execute(MovieQuery movieQuery) {
		return movieDao.queryAll().stream()
			.filter(movieQuery.getQuery())
			.collect(Collectors.toList());
		
	}

}
