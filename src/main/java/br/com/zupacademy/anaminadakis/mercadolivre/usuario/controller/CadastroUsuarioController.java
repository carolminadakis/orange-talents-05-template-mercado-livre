package br.com.zupacademy.anaminadakis.mercadolivre.usuario.controller;

import br.com.zupacademy.anaminadakis.mercadolivre.usuario.controller.request.NovoUsuarioRequest;
import br.com.zupacademy.anaminadakis.mercadolivre.usuario.model.Usuario;
import br.com.zupacademy.anaminadakis.mercadolivre.usuario.repository.UsuarioRepository;
import br.com.zupacademy.anaminadakis.mercadolivre.validaemail.ImpedeEmailDuplicado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class CadastroUsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ImpedeEmailDuplicado impedeEmailDuplicado;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(impedeEmailDuplicado);
    }

    @PostMapping
    @Transactional
    public String cadastra(@RequestBody @Valid NovoUsuarioRequest novoUsuarioRequest) {
        Usuario usuario = novoUsuarioRequest.converter();
        usuarioRepository.save(usuario);
        return usuario.toString();
    }
}
