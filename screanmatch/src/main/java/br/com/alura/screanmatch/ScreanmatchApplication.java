package br.com.alura.screanmatch;


import br.com.alura.screanmatch.model.DadosSerie;
import br.com.alura.screanmatch.model.DadosTemporada;
import br.com.alura.screanmatch.principal.Principal;
import br.com.alura.screanmatch.service.ConsumoApi;
import br.com.alura.screanmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreanmatchApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(ScreanmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();
	}

}
