package br.com.zupacademy.anaminadakis.mercadolivre.opiniao.request;

import br.com.zupacademy.anaminadakis.mercadolivre.opiniao.model.Opiniao;
import br.com.zupacademy.anaminadakis.mercadolivre.produto.model.Produto;
import br.com.zupacademy.anaminadakis.mercadolivre.usuario.model.Usuario;

import javax.validation.constraints.*;

public class OpiniaoRequest {

    @NotNull
    @Min(1)
    @Max(5)
    private int nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Size(max = 500)
    private String descricao;

    public OpiniaoRequest(int nota, String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }


    public Opiniao toModel(Produto produto, Usuario consumidor) {
        return new Opiniao(this.nota, this.titulo, this.descricao, produto, consumidor);
    }
}
