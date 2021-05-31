/*
package br.com.zupacademy.anaminadakis.mercadolivre.senha;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;

public class Senha {

    @NotBlank @Length(min = 6)
    private String senha;

    public Senha(String senha) {
        Assert.hasLength(senha, "campo obrigatório");
        Assert.isTrue(senha.length()>=6,"A senha precisa ter no mínimo 6 caracteres");

        this.senha = senha;
    }

    public String hash() {
        return new BCryptPasswordEncoder().encode(senha);
    }
}
*/
