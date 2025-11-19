package com.lis.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {   //spring security의 설정클래스
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable());                            //CSRF보안 꺼둠 //추후 JWT로 보완할 예정

        http.authorizeHttpRequests((authorize) ->                       //내 서버 모든 링크에 접속해도, 기본 spring security로그인 안해도되게.(로그인기능 직접 만들기위해 잠시 꺼둠)
                authorize.requestMatchers("/**").permitAll()
        );

        http.formLogin((formLogin) -> formLogin.loginPage("/login")     //form으로 로그인
                .defaultSuccessUrl("/")
                .failureUrl("/fail")
        );

        return http.build();
    }
}