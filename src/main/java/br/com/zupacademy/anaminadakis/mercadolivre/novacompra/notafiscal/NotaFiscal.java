package br.com.zupacademy.anaminadakis.mercadolivre.novacompra.notafiscal;

import br.com.zupacademy.anaminadakis.mercadolivre.novacompra.fechamentocompra.EventoCompraSucesso;
import br.com.zupacademy.anaminadakis.mercadolivre.novacompra.model.Compra;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class NotaFiscal implements EventoCompraSucesso {

    @Override
    public void processa(Compra compra) {
        Assert.isTrue(compra.processadaComSucesso(),"Processamento não concluido com sucesso "+compra);

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra",
                compra.getId(),
                "idComprador",
                compra.getComprador().getId());

        restTemplate.postForEntity("http://localhost:8080/notas-fiscais", request, String.class);
    }
}
