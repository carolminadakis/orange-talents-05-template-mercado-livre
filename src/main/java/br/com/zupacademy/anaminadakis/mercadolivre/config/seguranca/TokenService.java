package br.com.zupacademy.anaminadakis.mercadolivre.config.seguranca;

import br.com.zupacademy.anaminadakis.mercadolivre.usuario.model.Usuario;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
@Service
public class TokenService {

        @Value("${mercadolivre.jwt.expiration}")
        private String expiration;

        @Value("${mercadolivre.jwt.secret}")
        private String secret;

        public String gerarToken(Authentication authentication)  {
            Usuario logado = (Usuario) authentication.getPrincipal();
            Date hoje = new Date();
            Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

            return Jwts.builder()
                    .setIssuer("API do Mercado Livre")
                    .setSubject(logado.getId().toString())  //identifica o usuário
                    .setIssuedAt(hoje)
                    .setExpiration(dataExpiracao)
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .compact();
        }

        public boolean isTokenValid(String token) {
            try {
                Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        public Long getIdUsuario(String token) {
            Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
            return Long.parseLong(claims.getSubject());
        }

}
