package br.com.zupacademy.anaminadakis.mercadolivre.email;

import br.com.zupacademy.anaminadakis.mercadolivre.pergunta.model.Pergunta;

public interface EnviadorDeEmail {

    String envia(Pergunta pergunta);
}
