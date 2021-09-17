package com.luxoft.library.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/", "/home", "/books", "/books/{bookId:\\d+}", "/authors", "/authors/{authorId:\\d+}", "/genres",
                        "/genres/{genresId:\\d+}")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/books/{bookId:\\d+}/delete",
                        "/authors/{authorId:\\d+}/delete",
                        "/comments/{commenId:\\d+}/delete")
                .hasRole("ADMIN")
                .anyRequest().authenticated().and().formLogin()
                .loginPage("/login").permitAll().and().logout().permitAll().logoutSuccessUrl("/home");
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserDetails user = User.withUsername(
                "user").password(encoder.encode(
                "password")).roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin").password(encoder.encode(
                "password")).roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

}
