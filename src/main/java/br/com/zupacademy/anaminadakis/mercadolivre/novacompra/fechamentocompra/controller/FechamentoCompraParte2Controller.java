package br.com.zupacademy.anaminadakis.mercadolivre.novacompra.fechamentocompra.controller;

import br.com.zupacademy.anaminadakis.mercadolivre.novacompra.fechamentocompra.EventosNovaCompra;
import br.com.zupacademy.anaminadakis.mercadolivre.novacompra.gateway.RetornoGatewayPagamento;
import br.com.zupacademy.anaminadakis.mercadolivre.novacompra.model.Compra;
import br.com.zupacademy.anaminadakis.mercadolivre.novacompra.repository.CompraRepository;
import br.com.zupacademy.anaminadakis.mercadolivre.novacompra.request.RetornoPagseguroRequest;
import br.com.zupacademy.anaminadakis.mercadolivre.novacompra.request.RetornoPaypalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class FechamentoCompraParte2Controller {

    @Autowired
    private EventosNovaCompra eventosNovaCompra;
    @Autowired
    CompraRepository compraRepository;

    @PostMapping(value = "/retorno-pagseguro/{id}")
    @Transactional
    public String processamentoPagSeguro(@PathVariable("id") Long idCompra, @Valid RetornoPagseguroRequest request) {
        return processa(idCompra, request);
    }


    @PostMapping(value = "/retorno-paypal/{id}")
    @Transactional
    public String processamentoPaypal(@PathVariable("id") Long idCompra, @Valid RetornoPaypalRequest request) {
        return processa(idCompra, request);
    }


    private String processa(Long idCompra, RetornoGatewayPagamento retornoGatewayPagamento) {
        Compra compra = compraRepository.getById(idCompra);
        compra.adicionaTransacao(retornoGatewayPagamento);
        compraRepository.save(compra);
        eventosNovaCompra.processa(compra);

        return compra.toString();
    }
}
