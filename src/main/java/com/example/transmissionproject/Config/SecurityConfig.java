package com.example.transmissionproject.Config;

import com.example.transmissionproject.PassEncoder;
import com.example.transmissionproject.Services.AgentService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private final PassEncoder passEncode;
    private final AgentService agentService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()

                .authorizeHttpRequests()
                .requestMatchers("/user","/record/**")
                .hasAuthority("USER")
                .requestMatchers("/")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .logout();



        return http.build();
    }


    public AuthenticationManagerBuilder authenticationManagerBuilder(AuthenticationManagerBuilder auth){
        return auth.authenticationProvider(authenticationProvider());
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passEncode.encoder());
        provider.setUserDetailsService(agentService);
        return provider;
    }

}
