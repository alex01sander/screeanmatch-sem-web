package br.com.alura.screanmatch.dto;

import br.com.alura.screanmatch.model.Categoria;


public record SerieDTO(Long id,
                        String titulo,
                        Integer totalTemporadas,
                        Double avaliacao,
                        Categoria genero,
                        String atores,
                        String poster,
                        String sinopse

) {

}
