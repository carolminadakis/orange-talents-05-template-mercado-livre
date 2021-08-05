package br.com.zupacademy.anaminadakis.mercadolivre.email;

import br.com.zupacademy.anaminadakis.mercadolivre.pergunta.model.Pergunta;
import org.springframework.stereotype.Component;

@Component
public class EnvioDeEmailFake implements EnviadorDeEmail{

    @Override
    public String envia(Pergunta pergunta) {
        return "Enviando e-mail para '" + pergunta.getEmailDoVendedorDoProduto() +
                "' com a pergunta '" + pergunta.getTitulo() +
                "' do usuário com e-mail '" + pergunta.getEmailDoDonoDaPergunta() + "'";
    }

}
