package com.example.hospitalmanagement.config;
import com.example.hospitalmanagement.sevice.UserInforDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private UserInforDetailService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();  // Không mã hóa mật khẩu
    }
    /**
     * Cấu hình AuthenticationProvider sử dụng DaoAuthenticationProvider và BCryptPasswordEncoder.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService); // No need to cast
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * Cấu hình SecurityFilterChain.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable()) // Disable CSRF (if needed)
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers("/login", "/logoutSuccessful", "/403").permitAll() // Allow these URLs
                        .requestMatchers("/logout", "/doctor/**").authenticated() // Secure URLs
                )
                .formLogin((formLogin) -> formLogin
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .loginPage("/login")
                        .failureUrl("/login?error=true")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/doctor", true)
                        .permitAll()
                )
                .logout((logout) -> logout
                        .deleteCookies("remove")
                        .invalidateHttpSession(true)
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/logoutSuccessful")
                )
                .rememberMe((remember) -> remember
                        .rememberMeParameter("remember-me")
                        .tokenValiditySeconds(60 * 60 * 24 * 365)
                )
                .exceptionHandling((exceptionHandling) -> exceptionHandling
                        .accessDeniedPage("/403")
                );

        return http.build();
    }
}
