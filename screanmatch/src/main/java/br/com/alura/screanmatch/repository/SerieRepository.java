package br.com.alura.screanmatch.repository;

import br.com.alura.screanmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<Serie, Long> {

}
