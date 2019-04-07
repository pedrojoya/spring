package es.iessaladillo.pedrojoya.movieadvisor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

@Component
public class HelpAssistant {

	private String help;
	
	@Autowired
	public HelpAssistant() throws FileNotFoundException, IOException {
			help = Files
					.lines(Paths.get(ResourceUtils.getFile("classpath:ayuda.txt").toURI()))
					.collect(Collectors.joining("\n")); 
	}

	public String getHelp() {
		return help;
	}
	
}
