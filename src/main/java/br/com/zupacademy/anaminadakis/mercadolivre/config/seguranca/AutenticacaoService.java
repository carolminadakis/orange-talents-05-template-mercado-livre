package br.com.zupacademy.anaminadakis.mercadolivre.config.seguranca;

import br.com.zupacademy.anaminadakis.mercadolivre.usuario.model.Usuario;
import br.com.zupacademy.anaminadakis.mercadolivre.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {        // a implementação avisa ao Spring que essa é a classe de Autenticação
//quando a pessoa clicar para logar, o Spring chama essa service AutenticacaoService

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(username);
        if (usuario.isPresent()) {
            return usuario.get();
            //caso encontrado usuário no BD, o método retorna a busca
        }

        throw new UsernameNotFoundException("Dados inválidos!"); //caso não encontrado, lançamos uma excessão
    }

}
