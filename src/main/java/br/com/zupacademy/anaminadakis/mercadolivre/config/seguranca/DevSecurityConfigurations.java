package br.com.zupacademy.anaminadakis.mercadolivre.config.seguranca;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity  //abilita o modo de segurança na aplicação
@Configuration      //por ser uma classe de configurações, precisamos anotá-la com Configuration
@Profile("dev") //essa anotação assegura que a camada de segurança seja desativada para o ambiente de desenvolvimento
public class DevSecurityConfigurations extends WebSecurityConfigurerAdapter {


    //Configuração de autenticação
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").permitAll()    //permite todas as requisições
                .and().csrf().disable();
    }

}