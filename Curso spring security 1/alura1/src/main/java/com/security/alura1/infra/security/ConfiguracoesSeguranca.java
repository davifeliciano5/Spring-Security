package com.security.alura1.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //Diz ao spring que é uma classe de configuração
@EnableWebSecurity//Ativa as configurações de seguranca
public class ConfiguracoesSeguranca {

    @Bean
    public UserDetailsService dadosUsuariosCadastrados(){ //função que cria um usuário atravez do userdetailservice
        UserDetails usuario1 = User.builder() // UserDetails é o tipo utilizado para se lidar com usuários no spring
                .username("jao@gmail.com")
                .password("{noop}123")
                .build();

        UserDetails usuario2 = User.builder()
                .username("maria@gmail.com")
                .password("{noop}123")
                .build();

        return new InMemoryUserDetailsManager(usuario2,usuario1);//metodo que salva os usuários em memória
    }

    @Bean // bean para configuração o filtro do spring para que a página de login personalizada seja carregada corretamente
    public SecurityFilterChain filtroDeSeguranca(HttpSecurity httpSecurity) throws Exception {
       return httpSecurity.authorizeHttpRequests(req -> {
                    req.requestMatchers("/css/**", "/js/**", "/assets/**", "/login").permitAll();
                    req.anyRequest().authenticated();
                })
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll())
                .build();


    }
}
