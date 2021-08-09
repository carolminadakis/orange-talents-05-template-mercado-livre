package br.com.zupacademy.anaminadakis.mercadolivre.email;

import br.com.zupacademy.anaminadakis.mercadolivre.pergunta.model.Pergunta;
import org.springframework.stereotype.Component;

@Component
public class Email {


    private final EnviadorDeEmail enviadorDeEmailFake;

    public Email(EnviadorDeEmail enviadorDeEmailFake) {
        this.enviadorDeEmailFake = enviadorDeEmailFake;
    }

    public String enviaPergunta(Pergunta pergunta) {
        return enviadorDeEmailFake.envia(pergunta);
    }
}
