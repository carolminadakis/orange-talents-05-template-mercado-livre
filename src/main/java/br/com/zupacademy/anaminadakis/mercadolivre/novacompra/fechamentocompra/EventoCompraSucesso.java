package br.com.zupacademy.anaminadakis.mercadolivre.novacompra.fechamentocompra;

import br.com.zupacademy.anaminadakis.mercadolivre.novacompra.model.Compra;

public interface EventoCompraSucesso {

    void processa(Compra compra);
}