package br.com.zupacademy.anaminadakis.mercadolivre.produto.request;

import br.com.zupacademy.anaminadakis.mercadolivre.produto.model.Caracteristica;
import br.com.zupacademy.anaminadakis.mercadolivre.produto.model.Produto;

import javax.validation.constraints.NotBlank;

public class CaracteristicaRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    public CaracteristicaRequest(String nome,
                                 String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }


    public String getNome() { return nome; }

    public String getDescricao() { return descricao; }


    public Caracteristica converte(Produto produto) {
        return new Caracteristica(this.nome, this.descricao, produto);
    }
}
