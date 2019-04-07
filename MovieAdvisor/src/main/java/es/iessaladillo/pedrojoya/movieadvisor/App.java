package es.iessaladillo.pedrojoya.movieadvisor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import es.iessaladillo.pedrojoya.movieadvisor.config.AppConfig;

public class App {

	public static void main(String[] args) {

		// Se obtiene el contexto de la aplicación Spring.
		// Como hemos usado configuración mediante Java usamos AnnotationConfigApplicationContext,
		// pasándole la clase que contiene la configuración.
		ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);

		// Se obtiene el Cli y se ejecuta con los argumentos recibidos.
		CliApp cliApp = appContext.getBean(CliApp.class);
		cliApp.run(args);

		// Cerramos el contexto de aplicación Spring.
		((AnnotationConfigApplicationContext) appContext).close();

	}
}
