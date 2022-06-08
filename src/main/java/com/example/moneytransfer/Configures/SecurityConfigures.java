package com.example.moneytransfer.Configures;

import com.example.moneytransfer.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfigures extends WebSecurityConfigurerAdapter {
    @Autowired
    CardService cardService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/card/register").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/card/update/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/card/delete/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/user/register/").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/transfer").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
                .and()
                .withUser("user").password(passwordEncoder().encode("user")).roles("USER");
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}
