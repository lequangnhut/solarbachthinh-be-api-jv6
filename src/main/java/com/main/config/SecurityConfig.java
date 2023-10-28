package com.main.config;

import com.main.security.CustomSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize
                                .requestMatchers("/user/**").permitAll()
                                .requestMatchers("/quan-tri/**").hasAuthority("ADMIN")
                                .requestMatchers("/gio-hang/**", "/thong-tin/**").hasAuthority("USER")
                                .requestMatchers("/**").permitAll()
                ).formLogin(
                        form -> form
                                .loginPage("/dang-nhap")
                                .loginProcessingUrl("/dang-nhap")
                                .successHandler(handler)
                                .permitAll()
                ).logout(
                        logout -> logout
                                .invalidateHttpSession(true)
                                .clearAuthentication(true)
                                .logoutRequestMatcher(new AntPathRequestMatcher("/dang-xuat"))
                                .deleteCookies("JSESSIONID")
                                .logoutSuccessUrl("/trang-chu")
                                .permitAll()
                );
        return http.build();
    }
}
