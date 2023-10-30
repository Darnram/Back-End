package com.danram.server.config;

import com.danram.server.util.JwtCustomFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//@PreAuthorize 어노테이션을 메소드 단위로 추가하기 위해
public class SecurityConfig {
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return new WebSecurityCustomizer() {
            @Override
            public void customize(WebSecurity web) {
                web.ignoring().antMatchers(
                        "/",
                        "/auth/**",
                        "/user/signup",
                        "/login/**",
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/swagger-resources/**",
                        "/token/**",
                        "/chat/**",
                        "/chatting/**",
                        "/health/**",
                        "/free/**"
                );
            }
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .cors()
                .configurationSource(corsConfigurationSource())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilterAfter(new JwtCustomFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/party/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                .antMatchers("/alarm/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/token/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                .antMatchers("/user/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                .antMatchers("/auth/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                .antMatchers("/inquiry/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                .antMatchers("/chatting/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                .antMatchers("/comment/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                .antMatchers("/feed/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                .antMatchers("/friend/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                .antMatchers("/goal/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                .antMatchers("/notification/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                .antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
                .anyRequest().denyAll()
                .and().build();
    }
}