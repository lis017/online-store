package com.lis.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {   //spring security의 설정클래스
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //CSRF토큰
    @Bean
    public CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //CSRF토큰
        http.csrf(csrf -> csrf.csrfTokenRepository(csrfTokenRepository())
                .ignoringRequestMatchers("/login")                      //여기가면 CSRF꺼짐.
        );

        http.authorizeHttpRequests((authorize) ->                       //내 서버 모든 링크에 접속해도, 기본 spring security로그인 안해도되게.(로그인기능 직접 만들기위해 잠시 꺼둠)
                authorize.requestMatchers("/**").permitAll()
        );

        http.formLogin((formLogin) -> formLogin.loginPage("/login")     //form으로 로그인할것이다.
                .defaultSuccessUrl("/list")
                .failureUrl("/fail")
        );

        //로그아웃
        http.logout( logout -> logout.logoutUrl("/logout") );        //logout으로 get요청 날리면.
                                                                    //로그아웃되면 login창으로 이동

        return http.build();
    }
}