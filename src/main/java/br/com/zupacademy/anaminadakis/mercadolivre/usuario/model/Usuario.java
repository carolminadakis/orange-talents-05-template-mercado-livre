package br.com.zupacademy.anaminadakis.mercadolivre.usuario.model;

import org.hibernate.validator.constraints.Length;

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
    private String senha;

    @NotNull
    private LocalDateTime horarioCriacao = LocalDateTime.now();

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", horarioCriacao=" + horarioCriacao +
                '}';
    }
}
