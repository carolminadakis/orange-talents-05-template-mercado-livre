package br.com.zupacademy.anaminadakis.mercadolivre.imagens.controller;

import br.com.zupacademy.anaminadakis.mercadolivre.imagens.dto.UploaderFake;
import br.com.zupacademy.anaminadakis.mercadolivre.imagens.request.ImagemRequest;
import br.com.zupacademy.anaminadakis.mercadolivre.produto.model.Produto;
import br.com.zupacademy.anaminadakis.mercadolivre.produto.repository.ProdutoRepository;
import br.com.zupacademy.anaminadakis.mercadolivre.usuario.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

@RestController
public class AdicionaImagemController {

    @Autowired
    private UploaderFake uploaderFake;
    @Autowired
    private ProdutoRepository produtoRepository;


    @PostMapping("/produtos/{id}/imagens")
    @Transactional
    public ResponseEntity<?> adicionaImagens(@PathVariable Long id, @Valid ImagemRequest imagemRequest, @AuthenticationPrincipal Usuario usuarioLogado) {
        /*
        1- Enviar imagens para local onde ficarão;
        2- Pegar os links de todas as imagens;
        3- Associar os links com os produtos em questão;
        4- Carregar o produto;
        5- Atualizar a nova versão do produto, após a associação da imagem
         */
        Set<String> links = uploaderFake.envia(imagemRequest.getImagens());
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Produto inexistente"));

        produto.associaImagens(links);
        produtoRepository.save(produto);

        return ResponseEntity.ok().body(produto.toString());
    }

}
