package br.com.zupacademy.anaminadakis.mercadolivre.novacompra.request;

import br.com.zupacademy.anaminadakis.mercadolivre.novacompra.gateway.GatewayPagamento;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NovaCompraRequest {

    @Positive
    @NotNull
    private int quantidade;

    @NotNull
    private Long produto;

    @NotNull
    private GatewayPagamento meioDePagamento;

    public NovaCompraRequest(int quantidade, Long produto, GatewayPagamento gateway) {
        super();
        this.quantidade = quantidade;
        this.produto = produto;
        this.meioDePagamento = gateway;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Long getProduto() {
        return produto;
    }

    public GatewayPagamento getMeioDePagamento() {
        return meioDePagamento;
    }


}
