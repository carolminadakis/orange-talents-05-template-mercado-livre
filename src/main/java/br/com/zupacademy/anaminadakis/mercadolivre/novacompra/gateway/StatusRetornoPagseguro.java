package br.com.zupacademy.anaminadakis.mercadolivre.novacompra.gateway;

import br.com.zupacademy.anaminadakis.mercadolivre.novacompra.transacao.StatusTransacao;

public enum StatusRetornoPagseguro {

    SUCESSO,ERRO;

    public StatusTransacao normaliza() {
        if(this.equals(SUCESSO)) {
            return StatusTransacao.SUCESSO;
        }

        return StatusTransacao.ERRO;
    }
}
