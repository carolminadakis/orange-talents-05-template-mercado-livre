package br.com.zupacademy.anaminadakis.mercadolivre.pergunta.dto;

import br.com.zupacademy.anaminadakis.mercadolivre.pergunta.model.Pergunta;

public class PerguntaDto {


    private String titulo;

    public PerguntaDto(Pergunta pergunta) {

        this.titulo = pergunta.getTitulo();

    }

    public String getTitulo() {
        return titulo;
    }
}
