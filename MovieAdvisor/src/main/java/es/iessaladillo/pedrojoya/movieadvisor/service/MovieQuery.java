package es.iessaladillo.pedrojoya.movieadvisor.service;

import java.util.Arrays;
import java.util.function.Predicate;

import es.iessaladillo.pedrojoya.movieadvisor.model.Movie;

// Le añadimos el estereotipo Service para que sea un bean.

public class MovieQuery {

	private Predicate<Movie> query;

	private MovieQuery(Predicate<Movie> query) {
		this.query = query;
	}
	
	public Predicate<Movie> getQuery() {
		return query;
	}

	public static class Builder {
		
		private Predicate<Movie> query = movie -> true; 
		
		public Builder withTitle(String title) {
			query = query.and(movie -> movie.getTitle().contains(title));
			return this;
		}
		
		public Builder withYear(int year) {
			query = query.and(movie -> movie.getYear() == year);
			return this;
		}
		
		public Builder betweenYears(int from, int to) {
			query = query.and(movie -> movie.getYear() >= from && movie.getYear() <= to);
			return this;
		}
		
		public Builder ofAnyGenre(String... genres) {
			query = query.and(movie -> Arrays.asList(genres).stream().anyMatch(genre -> movie.getGenres().contains(genre)));
			return this;
		}
		
		public Builder ofAllGenres(String... genres) {
			query = query.and(movie -> Arrays.asList(genres).stream().allMatch(genre -> movie.getGenres().contains(genre)));
			return this;
		}
		
		public MovieQuery build() {
			return new MovieQuery(query);
		}
		
	}
	
}
