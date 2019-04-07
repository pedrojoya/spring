package es.iessaladillo.pedrojoya.movieadvisor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.iessaladillo.pedrojoya.movieadvisor.model.Movie;
import es.iessaladillo.pedrojoya.movieadvisor.service.MovieDataService;
import es.iessaladillo.pedrojoya.movieadvisor.service.MovieQuery;

@Component
public class CliApp {
	
	private final MovieDataService movieDataService;
	private final HelpAssistant helpAssistant;
	
	@Autowired
	public CliApp(MovieDataService movieDataService, HelpAssistant helpAssistant) {
		this.movieDataService = movieDataService;
		this.helpAssistant = helpAssistant;
	}

	public void run(String[] args) {
		if (incorrectNumberOfArguments(args.length)) {
			showSyntaxError();
		} else if (args.length == 1) {
			switch (args[0].toLowerCase()) {
			case "-lg":
				showAllGenres();
				break;
			case "-h":
				showHelp();
				break;
			default:
				showSyntaxError();
			}
		} else {
			// De esta forma hemos asegurado que el número de argumentos
			// es par (opción valoropción) y que no hay más de cuatro
			// parejas (ver fichero de ayuda).
			List<String[]> argumentPairs = createArgValuePairList(args);
			MovieQuery.Builder builder = new MovieQuery.Builder();
			for (String[] pair : argumentPairs) {
				String option = pair[0];
				String value = pair[1];
				switch (option) {
				case "-ag":
					builder.ofAnyGenre(value.split(","));
					break;
				case "-tg":
					builder.ofAllGenres(value.split(","));
					break;
				case "-y":
					builder.withYear(Integer.parseInt(value));
					break;
				case "-b":
					String[] years = value.split(",");
					builder.betweenYears(Integer.parseInt(years[0]), Integer.parseInt(years[1]));
					break;
				case "-t":
					builder.withTitle(value);
					break;
				default: 
					showSyntaxError();
					return;
				}
			}
			showMovies(movieDataService.execute(builder.build()));	
		}
	}
	
	private void showMovies(Collection<Movie> movies) {
		System.out.printf("%s\t%-50s\t%s\t%s\n","ID","Título", "Año", "Géneros");
		if (!movies.isEmpty()) {
			movies.forEach(f -> System.out.printf("%s\t%-50s\t%s\t%s\n", 
					f.getId(), f.getTitle(), f.getYear(), 
					f.getGenres().stream().collect(Collectors.joining(", "))));
		} else {
			System.out.println("No hay películas que cumplan esos criterios. Lo sentimos");
		}
	}

	private List<String[]> createArgValuePairList(String[] args) {
		List<String[]> arguments = new ArrayList<>();
		for (int i = 0; i < args.length; i += 2) {
			arguments.add(new String[] { args[i], args[i + 1] });
		}
		return arguments;
	}

	private void showHelp() {
		System.out.println(helpAssistant.getHelp());
	}

	private void showAllGenres() {
		movieDataService.findAllGenres().forEach(System.out::println);
	}
	
	private boolean incorrectNumberOfArguments(int length) {
		return 
				length < 1 || 
				length > 8 ||
				(length % 2 != 0 && length != 1);
	}

	private void showSyntaxError() {
		System.out.println("Error de sintaxis");
		showHelp();
	}
	
	
	
}
