package br.com.zupacademy.anaminadakis.mercadolivre.pergunta.request;

import br.com.zupacademy.anaminadakis.mercadolivre.pergunta.model.Pergunta;
import br.com.zupacademy.anaminadakis.mercadolivre.produto.model.Produto;
import br.com.zupacademy.anaminadakis.mercadolivre.usuario.model.Usuario;

import javax.validation.constraints.NotBlank;

public class NovaPerguntaRequest {

    @NotBlank
    private String titulo;

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Pergunta converte(Produto produto, Usuario donoDaPergunta) {
        return new Pergunta(this.titulo, produto, donoDaPergunta);
    }
}
