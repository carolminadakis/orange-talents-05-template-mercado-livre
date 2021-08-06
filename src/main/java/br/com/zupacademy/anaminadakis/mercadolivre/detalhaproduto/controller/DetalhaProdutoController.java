package br.com.zupacademy.anaminadakis.mercadolivre.detalhaproduto.controller;

import br.com.zupacademy.anaminadakis.mercadolivre.detalhaproduto.dto.DetalhaProdutoDto;
import br.com.zupacademy.anaminadakis.mercadolivre.produto.model.Produto;
import br.com.zupacademy.anaminadakis.mercadolivre.produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class DetalhaProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/produtos/{id}")
    public ResponseEntity<?> buscar(@PathVariable("id") Long id) {

        Optional<Produto> produtoOptional = produtoRepository.findById(id);

        if (produtoOptional.isEmpty()) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new DetalhaProdutoDto(produtoOptional.get()));
    }
}
