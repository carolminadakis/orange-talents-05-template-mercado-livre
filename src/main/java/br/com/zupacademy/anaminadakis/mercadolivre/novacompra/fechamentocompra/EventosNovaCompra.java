package br.com.zupacademy.anaminadakis.mercadolivre.novacompra.fechamentocompra;

import br.com.zupacademy.anaminadakis.mercadolivre.novacompra.model.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class EventosNovaCompra {

    @Autowired
    private Set<EventoCompraSucesso> eventosCompraSucesso;

    public void processa(Compra compra) {

        if(compra.processadaComSucesso()) {
            eventosCompraSucesso.forEach(evento -> evento.processa(compra));
        } else {
            //eventosFalha
        }
    }
}
