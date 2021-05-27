package br.com.zupacademy.anaminadakis.mercadolivre.validaemail;

import br.com.zupacademy.anaminadakis.mercadolivre.usuario.controller.request.NovoUsuarioRequest;
import br.com.zupacademy.anaminadakis.mercadolivre.usuario.model.Usuario;
import br.com.zupacademy.anaminadakis.mercadolivre.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ImpedeEmailDuplicado implements Validator {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovoUsuarioRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        NovoUsuarioRequest request = (NovoUsuarioRequest) target;
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(request.getEmail());

        if (usuarioExistente.isPresent()) {
            errors.rejectValue("email", null, "Email já cadastrado " + request.getEmail());
        }

    }
}
