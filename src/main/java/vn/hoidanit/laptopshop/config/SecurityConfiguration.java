package vn.hoidanit.laptopshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;

import jakarta.servlet.DispatcherType;
import vn.hoidanit.laptopshop.service.MySimpleUrlAuthenticationSuccessHandler;
import vn.hoidanit.laptopshop.service.UserService;
import vn.hoidanit.laptopshop.service.customUserDetails.CustomUserDetailsService;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

        private final String[] PUBLIC_ENDPOINT = { "/", "/client/**", "/register", "/client/**", "/css/**", "/js/**",
                        "/images/**" };

        @Bean
        PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        UserDetailsService userDetailsService(UserService userService) {
                return new CustomUserDetailsService(userService);
        }

        @Bean
        public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
                return new MySimpleUrlAuthenticationSuccessHandler();
        }

        @Bean
        public SpringSessionRememberMeServices rememberMeServices() {
                SpringSessionRememberMeServices rememberMeServices = new SpringSessionRememberMeServices();
                // optionally customize
                rememberMeServices.setAlwaysRemember(true);
                return rememberMeServices;
        }

        @Bean
        DaoAuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder,
                        UserDetailsService userDetailsService) {
                DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
                authenticationProvider.setUserDetailsService(userDetailsService);
                authenticationProvider.setPasswordEncoder(passwordEncoder);
                authenticationProvider.setHideUserNotFoundExceptions(false);
                return authenticationProvider;
        }

        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
                httpSecurity
                                .authorizeHttpRequests(request -> request
                                                .dispatcherTypeMatchers(DispatcherType.FORWARD,
                                                                DispatcherType.INCLUDE)
                                                .permitAll()
                                                .requestMatchers(PUBLIC_ENDPOINT).permitAll()
                                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                                .anyRequest().authenticated()

                                )
                                .formLogin(formLogin -> formLogin
                                                .loginPage("/login")
                                                .successHandler(myAuthenticationSuccessHandler())
                                                .failureUrl("/login?error")
                                                .permitAll()

                                )
                                .rememberMe((rememberMe) -> rememberMe
                                                .rememberMeServices(rememberMeServices()))
                                .exceptionHandling(exception -> exception
                                                .accessDeniedPage("/access-denied"))

                                .sessionManagement((sessionManagement) -> sessionManagement
                                                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                                                .invalidSessionUrl("/logout?expired")
                                                .maximumSessions(1)
                                                .maxSessionsPreventsLogin(false))

                                .logout((logout) -> logout
                                                .deleteCookies("JSESSIONID")
                                                .invalidateHttpSession(true)
                                                .logoutSuccessUrl("/")
                                                .permitAll());

                return httpSecurity.build();
        }

}
