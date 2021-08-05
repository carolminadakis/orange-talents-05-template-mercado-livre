package br.com.zupacademy.anaminadakis.mercadolivre.pergunta.controller;

import br.com.zupacademy.anaminadakis.mercadolivre.pergunta.model.Pergunta;
import br.com.zupacademy.anaminadakis.mercadolivre.pergunta.repository.PerguntaRepository;
import br.com.zupacademy.anaminadakis.mercadolivre.pergunta.request.NovaPerguntaRequest;
import br.com.zupacademy.anaminadakis.mercadolivre.produto.model.Produto;
import br.com.zupacademy.anaminadakis.mercadolivre.produto.repository.ProdutoRepository;
import br.com.zupacademy.anaminadakis.mercadolivre.usuario.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class PerguntaController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PerguntaRepository perguntaRepository;


    @PostMapping("/produtos/{id}/perguntas")
    @Transactional
    public String cadastrar(@RequestBody @Valid NovaPerguntaRequest request,
                            @PathVariable Long id,
                            @AuthenticationPrincipal Usuario usuario) {

        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Produto inexistente"));

        Pergunta novaPergunta = request.converte(produto, usuario);
        perguntaRepository.save(novaPergunta);

        return novaPergunta.toString();
    }

}
