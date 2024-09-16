package com.example.demo.config;


import com.example.demo.jwt.JwtFilter;
import com.example.demo.jwt.JwtUtil;
import com.example.demo.jwt.LoginFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtUtil jwtUtil;

    //비밀번호는 항상 단방향 해시로 인증을 한다.
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
            return authenticationConfiguration.getAuthenticationManager();
    }



    @Bean // 인가 처리 하는 시큐리티 필터 체인
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {



        http
                .cors((cors) -> cors
                        .configurationSource(new CorsConfigurationSource() {
                            @Override
                            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                                CorsConfiguration config = new CorsConfiguration();

                                config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                                config.setAllowedMethods(Collections.singletonList("*"));
                                config.setAllowCredentials(true);
                                config.setAllowedHeaders(Collections.singletonList("*"));
                                config.setMaxAge(3600L);
                                config.setExposedHeaders(Collections.singletonList("Authorization"));

                                return config;
                            }
                        }));

        //csrf 사용 안함 이거 사용하면 csrf 토큰이 필요함
        http
                .csrf((csrf) -> csrf.disable());


        // form 로그인 사용 안함
        http
                .formLogin((form) -> form.disable());

        //httpBasic 사용 안함
        http
                .httpBasic((httpBasic) -> httpBasic.disable());


        // 권한에 맞는 인가 작업
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/login", "/", "/join").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                );


        http
                .addFilterBefore(new JwtFilter(jwtUtil), LogoutFilter.class);

        // UsernamePasswordAuthenticationFilter의 자리에 LoginFilter로 대체
        http
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration),jwtUtil), UsernamePasswordAuthenticationFilter.class);

        //jwt를 사용을 하기 때문에 세션을 stateless 상태로 만듦
        http
                .sessionManagement((sessoin) -> sessoin.sessionCreationPolicy(SessionCreationPolicy.STATELESS));




        return http.build();
    }

}
