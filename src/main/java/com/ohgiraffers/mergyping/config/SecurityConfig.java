package com.ohgiraffers.mergyping.config;

import com.ohgiraffers.mergyping.common.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Autowired
    private AuthFailHandler authFailHandler;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests( auth -> {
            auth.requestMatchers( "/auth/**",
                    "/", "/main", "/css/**", "/img/**",
                    "/userinfo/**", "/useractive/**", "/intro", "/notice", "/selectnotice", "/selectpost","/writepost", "/mbti/**",
                    "/checknickname/**", "/admin/**", "/toggleFavorite", "/post/**","selectpost/**","/toggleScary/**","/toggleNotScary/**").permitAll();
            auth.requestMatchers("/admin/*").hasAnyAuthority(UserRole.ADMIN.getRole());
            auth.requestMatchers("/user/*").hasAnyAuthority(UserRole.USER.getRole());
            auth.anyRequest().authenticated();

        }).formLogin( login -> {
            login.loginPage("/auth/login");
            login.usernameParameter("userId");
            login.passwordParameter("userPass");
            login.defaultSuccessUrl("/", true);
            login.failureHandler(authFailHandler);

        }).logout( logout -> {
            logout.logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"));
            logout.deleteCookies("JSESSIONID");
            logout.invalidateHttpSession(true);
            logout.logoutSuccessUrl("/");

        }).sessionManagement( session -> {
            session.maximumSessions(1);
            session.invalidSessionUrl("/");

        }).csrf( csrf -> csrf.disable());

        return http.build();
    }
}
