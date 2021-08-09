package br.com.zupacademy.anaminadakis.mercadolivre.novacompra.request;

import br.com.zupacademy.anaminadakis.mercadolivre.novacompra.gateway.RetornoGatewayPagamento;
import br.com.zupacademy.anaminadakis.mercadolivre.novacompra.gateway.StatusRetornoPagseguro;
import br.com.zupacademy.anaminadakis.mercadolivre.novacompra.model.Compra;
import br.com.zupacademy.anaminadakis.mercadolivre.novacompra.transacao.model.Transacao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoPagseguroRequest implements RetornoGatewayPagamento {

    @NotBlank
    private String idTransacao;
    @NotNull
    private StatusRetornoPagseguro status;

    public RetornoPagseguroRequest(@NotBlank String idTransacao,
                                   StatusRetornoPagseguro status) {
        super();
        this.idTransacao = idTransacao;
        this.status = status;
    }

    @Override
    public String toString() {
        return "RetornoPagseguroRequest [idTransacao=" + idTransacao
                + ", status=" + status + "]";
    }

    public Transacao toTransacao(Compra compra) {
        return new Transacao(status.normaliza(),idTransacao,compra);
    }

}
