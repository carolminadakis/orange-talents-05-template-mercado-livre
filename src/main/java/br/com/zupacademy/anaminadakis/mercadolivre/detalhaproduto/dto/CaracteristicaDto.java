package br.com.zupacademy.anaminadakis.mercadolivre.detalhaproduto.dto;

import br.com.zupacademy.anaminadakis.mercadolivre.produto.model.Caracteristica;

public class CaracteristicaDto {

    private String nome;

    private String descricao;

    public CaracteristicaDto(Caracteristica caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
