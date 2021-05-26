package br.com.zupacademy.anaminadakis.mercadolivre.usuario.controller.request;

import br.com.zupacademy.anaminadakis.mercadolivre.usuario.model.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NovoUsuarioRequest {


    @NotBlank
    @Email
    private String email;

    @NotBlank @Length(min = 6)
    private String senha;


    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario converter() {
        return new Usuario(this.email, this.senha);
    }
}
