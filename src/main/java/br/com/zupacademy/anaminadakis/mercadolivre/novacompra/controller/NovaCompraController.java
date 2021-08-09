package br.com.zupacademy.anaminadakis.mercadolivre.novacompra.controller;

import br.com.zupacademy.anaminadakis.mercadolivre.novacompra.fechamentocompra.EventosNovaCompra;
import br.com.zupacademy.anaminadakis.mercadolivre.novacompra.gateway.GatewayPagamento;
import br.com.zupacademy.anaminadakis.mercadolivre.novacompra.model.Compra;
import br.com.zupacademy.anaminadakis.mercadolivre.novacompra.repository.CompraRepository;
import br.com.zupacademy.anaminadakis.mercadolivre.novacompra.request.NovaCompraRequest;
import br.com.zupacademy.anaminadakis.mercadolivre.produto.model.Produto;
import br.com.zupacademy.anaminadakis.mercadolivre.produto.repository.ProdutoRepository;
import br.com.zupacademy.anaminadakis.mercadolivre.usuario.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;


@RestController
public class NovaCompraController {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    CompraRepository compraRepository;
    @Autowired
    EventosNovaCompra eventosNovaCompra;


    @PostMapping
    @Transactional
    public ResponseEntity<?> criarCompra(@AuthenticationPrincipal Usuario usuario,
                                         @RequestBody @Valid NovaCompraRequest request,
                                         UriComponentsBuilder builder) throws BindException {

        Long produtoSelecionado = request.getProduto();
        Produto produtoAserComprado = produtoRepository.getById(produtoSelecionado);
        int quatidade = request.getQuantidade();
        boolean abatido = produtoAserComprado.abateEstoque(quatidade);


        if (abatido) {
            GatewayPagamento gateway = request.getMeioDePagamento();
            Compra novaCompra = new Compra(produtoAserComprado, quatidade, usuario, gateway);
            compraRepository.save(novaCompra);
            eventosNovaCompra.processa(novaCompra);

            if (gateway.equals(GatewayPagamento.PAGSEGURO)) {
                UriComponents urlRetornoPagseguro = builder.path("/retorno-pagseguro/{id}").buildAndExpand(novaCompra.getId().toString());
                return ResponseEntity.status(HttpStatus.FOUND).body("pagseguro.com/" + novaCompra.getId() + "?redictUrl=" + urlRetornoPagseguro);
            } else {
                UriComponents urlRetornoPaypal = builder.path("/retorno-paypal/{id}").buildAndExpand(novaCompra.getId().toString());
                return ResponseEntity.status(HttpStatus.FOUND).body("paypal.com/" + novaCompra.getId() + "?redictUrl=" + urlRetornoPaypal);
            }
        }

        BindException problemaEstoque = new BindException(request, "novaCompraRequest");
        problemaEstoque.reject(null, "problema com estoque");
        throw problemaEstoque;
    }
}

