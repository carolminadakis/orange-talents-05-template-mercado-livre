package br.com.zupacademy.anaminadakis.mercadolivre.novacompra.gateway;

import br.com.zupacademy.anaminadakis.mercadolivre.novacompra.model.Compra;
import br.com.zupacademy.anaminadakis.mercadolivre.novacompra.transacao.model.Transacao;

public interface RetornoGatewayPagamento {

    /**
     *
     * @param compra
     * @return uma nova transacao em função do gateway específico
     */
    Transacao toTransacao(Compra compra);

}
