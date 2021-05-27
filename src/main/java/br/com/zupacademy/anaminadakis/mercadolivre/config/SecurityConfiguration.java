package br.com.zupacademy.anaminadakis.mercadolivre.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

         // poderia ter colocado na classe de application:@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})

        @Override
        protected void configure(HttpSecurity http) throws Exception{
            http.authorizeRequests()
                    .antMatchers("/usuarios").permitAll()
                    .and().csrf().disable();
        }

}
