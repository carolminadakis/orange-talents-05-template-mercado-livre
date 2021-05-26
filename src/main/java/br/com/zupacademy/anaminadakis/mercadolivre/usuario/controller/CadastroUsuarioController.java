package br.com.zupacademy.anaminadakis.mercadolivre.usuario.controller;

import br.com.zupacademy.anaminadakis.mercadolivre.usuario.controller.request.NovoUsuarioRequest;
import br.com.zupacademy.anaminadakis.mercadolivre.usuario.model.Usuario;
import br.com.zupacademy.anaminadakis.mercadolivre.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class CadastroUsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;


    @PostMapping
    @Transactional
    public String cadastra(@RequestBody @Valid NovoUsuarioRequest novoUsuarioRequest) {
        Usuario usuario = novoUsuarioRequest.converter();
        usuarioRepository.save(usuario);
        return usuario.toString();
    }
}
