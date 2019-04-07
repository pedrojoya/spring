package es.iessaladillo.pedrojoya.movieadvisor.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

// Clase de configuración JavaConfig para String (@Configuration)
// Indicaremos el paquete que debe escanear en busca de arquetipos (@ComponentScan)
// Indicamos mediante @PropertySource que como fuente de propiedades tenemos el fichero
// el fichero de propiedades que está en el classpath.

@Configuration
@ComponentScan(basePackages = "es.iessaladillo.pedrojoya.movieadvisor")
@PropertySource("classpath:/movieadvisor.properties")
public class AppConfig {

	// En vez de inyectar la propiedades donde se necesiten, las inyectamos
	// aquí y donde hagan falta se inyecta un objeto AppConfig.
	
	// Para inyectar una propiedad se usa @Value("${nombrePropiedad}")
	
	@Value("${file.path}")
	private String filePath;
	
	@Value("${file.csv.field_separator}")
	private String fieldSeparator;
	
	@Value("${file.csv.genre_separator}")
	private String genreSeparator;

	public String getFilePath() {
		return filePath;
	}

	public String getFieldSeparator() {
		return fieldSeparator;
	}

	public String getGenreSeparator() {
		return genreSeparator;
	}

}
