package br.com.zupacademy.anaminadakis.mercadolivre.usuario.model;


import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Usuario implements UserDetails {
//A classe UserDetalis comunica ao Spring Security, que essa é a nossa classe que tem os detalhes do usuário no banco de dados

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

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfis = new ArrayList<>();
    //a relação ManyToMany é Lazy, isso significa que a lista não é carregada automaticamente,
    //como vamos precisar dessa lista, iremos configurar como EAGER, dessa forma a lista será gerada sempre


    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = new BCryptPasswordEncoder().encode(senha);
    }

    @Deprecated
    public Usuario() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;

        Usuario usuario = (Usuario) o;

        if (!getId().equals(usuario.getId())) return false;
        if (!email.equals(usuario.email)) return false;
        return senha.equals(usuario.senha);
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + senha.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", horarioCriacao=" + horarioCriacao +
                ", perfis=" + perfis +
                '}';
    }

    public Long getId() { return id; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }   //devolve a senha

    @Override
    public String getUsername() {
        return this.email;
    }   //devolve o email

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
