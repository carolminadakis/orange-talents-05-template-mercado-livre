package br.com.zupacademy.anaminadakis.mercadolivre.opiniao.controller;

import br.com.zupacademy.anaminadakis.mercadolivre.opiniao.model.Opiniao;
import br.com.zupacademy.anaminadakis.mercadolivre.opiniao.repository.OpiniaoRepository;
import br.com.zupacademy.anaminadakis.mercadolivre.opiniao.request.OpiniaoRequest;
import br.com.zupacademy.anaminadakis.mercadolivre.produto.model.Produto;
import br.com.zupacademy.anaminadakis.mercadolivre.produto.repository.ProdutoRepository;
import br.com.zupacademy.anaminadakis.mercadolivre.usuario.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/opinioes")
public class OpiniaoController {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private OpiniaoRepository opiniaoRepository;

    @PostMapping("/{id}")
    @Transactional
    public String cadastra(@PathVariable Long id,
                           @RequestBody @Valid OpiniaoRequest request,
                           @AuthenticationPrincipal Usuario usuarioLogado) {

        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Produto inexistente"));
        Opiniao opiniao = request.toModel(produto, usuarioLogado);

        opiniaoRepository.save(opiniao);
        return opiniao.toString();

    }
}
