package br.com.zupacademy.anaminadakis.mercadolivre.novacompra.model;

import br.com.zupacademy.anaminadakis.mercadolivre.novacompra.gateway.GatewayPagamento;
import br.com.zupacademy.anaminadakis.mercadolivre.produto.model.Produto;
import br.com.zupacademy.anaminadakis.mercadolivre.usuario.model.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive
    @NotNull
    private int quantidade;

    @ManyToOne
    @NotNull
    private Produto produtoSelecionado;

    @ManyToOne
    @NotNull
    private Usuario comprador;

    @Enumerated
    @NotNull
    private GatewayPagamento gatewayPagamento;

    public Compra(@NotNull Produto produtoSelecionado, @Positive int quantidade, @NotNull Usuario comprador, GatewayPagamento gatewayPagamento) {
        this.quantidade = quantidade;
        this.produtoSelecionado = produtoSelecionado;
        this.comprador = comprador;
        this.gatewayPagamento = gatewayPagamento;
    }

    public Long getId() {
        return id;
    }

    public Usuario getComprador() {
        return comprador;
    }
}
