package br.com.zupacademy.anaminadakis.mercadolivre.usuario.model;

import br.com.zupacademy.anaminadakis.mercadolivre.senha.Senha;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    private String email;

    @NotBlank @Length(min = 6)
    private String senhaUsuario;

    @NotNull
    private LocalDateTime horarioCriacao = LocalDateTime.now();


    public Usuario(String email, Senha senha) {
        Assert.isTrue(StringUtils.hasLength(email),"email não pode ser em branco");
        Assert.notNull(senha,"senha não pode ser nula");

        this.email = email;
        this.senhaUsuario = senha.hash();
        this.horarioCriacao = LocalDateTime.now();
    }

    @Deprecated
    public Usuario() {
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", senha='" + senhaUsuario + '\'' +
                ", horarioCriacao=" + horarioCriacao +
                '}';
    }
}
