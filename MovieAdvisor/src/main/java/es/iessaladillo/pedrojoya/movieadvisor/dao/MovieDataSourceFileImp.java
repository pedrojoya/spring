package es.iessaladillo.pedrojoya.movieadvisor.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import es.iessaladillo.pedrojoya.movieadvisor.config.AppConfig;
import es.iessaladillo.pedrojoya.movieadvisor.model.Movie;

@Component
public class MovieDataSourceFileImp implements MovieDataSource {
	
	private List<Movie> movies;
	
	@Autowired
	public MovieDataSourceFileImp(AppConfig appConfig) throws FileNotFoundException, IOException {
		movies = Files.lines(ResourceUtils.getFile(appConfig.getFilePath()).toPath())
			// Skip header line
			.skip(1)
			.map(line -> parseMovie(line, appConfig.getFieldSeparator(), appConfig.getGenreSeparator()))
			.collect(Collectors.toList());
	}
	
	public Movie parseMovie(String line, String fieldSeparator, String genreSeparator) {
		String[] values = line.split(fieldSeparator);
		return new Movie(Long.parseLong(values[0]), values[1], Integer.parseInt(values[2]), Arrays.asList(values[3].split(genreSeparator)));
	}

	@Override
	public List<Movie> provideAllMovies() {
		return movies;
	}

}
