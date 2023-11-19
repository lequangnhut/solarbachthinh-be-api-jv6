package com.main.config;

import com.main.security.CustomSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    CustomSuccessHandler handler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthorizationRequestRepository<OAuth2AuthorizationRequest> getRepository() {
        return new HttpSessionOAuth2AuthorizationRequestRepository();
    }

    @Bean
    public OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> getToken() {
        return new DefaultAuthorizationCodeTokenResponseClient();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize
                                .requestMatchers("/user/**", "/admin/**").permitAll()
                                .requestMatchers("/quan-tri/**").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(
                                        "/api/carts/**",
                                        "/api/discount/**",
                                        "/api/order/**",
                                        "/api/order-item/**"
                                ).authenticated()
                                .requestMatchers("/**").permitAll()
                ).formLogin(
                        form -> form
                                .loginProcessingUrl("/dang-nhap")
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .successHandler(handler)
                                .permitAll()
                ).logout(
                        logout -> logout
                                .invalidateHttpSession(true)
                                .clearAuthentication(true)
                                .logoutRequestMatcher(new AntPathRequestMatcher("/dang-xuat"))
                                .deleteCookies("JSESSIONID")
                                .logoutSuccessUrl("/redirect-logout")
                                .permitAll()
                ).oauth2Login(
                        oauth2 -> oauth2
                                .loginPage("/dang-nhap")
                                .defaultSuccessUrl("/login-google-success")
                                .authorizationEndpoint()
                                .baseUri("/oauth2/authorization")
                                .authorizationRequestRepository(getRepository())
                                .and()
                                .tokenEndpoint()
                                .accessTokenResponseClient(getToken())
                );
        return http.build();
    }
}
