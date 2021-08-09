package br.com.zupacademy.anaminadakis.mercadolivre.novacompra.model;

import br.com.zupacademy.anaminadakis.mercadolivre.novacompra.gateway.GatewayPagamento;
import br.com.zupacademy.anaminadakis.mercadolivre.novacompra.gateway.RetornoGatewayPagamento;
import br.com.zupacademy.anaminadakis.mercadolivre.novacompra.transacao.model.Transacao;
import br.com.zupacademy.anaminadakis.mercadolivre.produto.model.Produto;
import br.com.zupacademy.anaminadakis.mercadolivre.usuario.model.Usuario;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

    public Compra(@NotNull Produto produtoSelecionado,
                  @Positive int quantidade,
                  @NotNull Usuario comprador,
                  GatewayPagamento gatewayPagamento) {
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

    public Usuario getDonoProduto() {
        return produtoSelecionado.getVendedor();
    }


    public void adicionaTransacao(@Valid RetornoGatewayPagamento request) {
        Transacao novaTransacao = request.toTransacao(this);

        Assert.state(!this.transacoes.contains(novaTransacao),
                "Já existe uma transacao igual a essa processada "
                        + novaTransacao);

        Assert.state(transacoesConcluidasComSucesso().isEmpty(), "Compra concluída com sucesso");
        this.transacoes.add(novaTransacao);
    }


    private Set<Transacao> transacoesConcluidasComSucesso() {
        Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream()
                .filter(Transacao::concluidaComSucesso)
                .collect(Collectors.toSet());

        Assert.isTrue(transacoesConcluidasComSucesso.size() <= 1, "Tem mais de uma transacao concluida com sucesso aqui na compra " + this.id);
        return transacoesConcluidasComSucesso;
    }

    public boolean processadaComSucesso() {
        return !transacoesConcluidasComSucesso().isEmpty();
    }
}
