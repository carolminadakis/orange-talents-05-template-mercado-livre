package br.com.zupacademy.anaminadakis.mercadolivre.produto.controller;

import br.com.zupacademy.anaminadakis.mercadolivre.categorias.model.Categoria;
import br.com.zupacademy.anaminadakis.mercadolivre.categorias.repository.CategoriaRepository;
import br.com.zupacademy.anaminadakis.mercadolivre.produto.model.Produto;
import br.com.zupacademy.anaminadakis.mercadolivre.produto.repository.ProdutoRepository;
import br.com.zupacademy.anaminadakis.mercadolivre.produto.request.ProdutoRequest;
import br.com.zupacademy.anaminadakis.mercadolivre.usuario.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class NovoProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;


    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastra(@RequestBody @Valid ProdutoRequest produtoRequest, Usuario usuario) {
        Categoria categoria = categoriaRepository.findById(produtoRequest.getCategoriaId())
                                                .orElseThrow(() -> new IllegalArgumentException("Categoria inexistente"));

        Produto produto = produtoRequest.converte(categoria, usuario);
        produtoRepository.save(produto);

        return ResponseEntity.ok().build();
    }
}
