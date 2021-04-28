package com.example.demo.security;

import com.example.demo.auth.ApplicationUserService;
import static com.example.demo.security.ApplicationUserRole.*;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author benjie_en
 */
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder encoder;
    private final ApplicationUserService applicationUserService;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder encoder, ApplicationUserService applicationUserService) {
        this.encoder = encoder;
        this.applicationUserService = applicationUserService;
    }

//    @Autowired
//    public ApplicationSecurityConfig(PasswordEncoder encoder) {
//        this.encoder = encoder;
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        
          http
                .csrf().disable()
                //.csrf().csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse()).and()
                .authorizeRequests()
                .antMatchers("index", "/css/*", "/js/*", "/login")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/v1/products/**").hasRole(USER.name())
                .antMatchers("/v1/customers/**").hasRole(ADMIN.name())
                .antMatchers("/v1/products/**").hasRole(ADMIN.name())
                .anyRequest()
                .authenticated();
        
        
        /**
         * if using non-browsers , it is recommended to disable CSRF
         */

        //TO DO: read about configuring external token repository
//        http
//                .csrf().disable()
//                //.csrf().csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse()).and()
//                .authorizeRequests()
//                .antMatchers("index", "/css/*", "/js/*", "/login")
//                .permitAll()
//                .antMatchers(HttpMethod.GET, "/v1/products/**").hasRole(USER.name())
//                .antMatchers("/v1/customers/**").hasRole(ADMIN.name())
//                .antMatchers("/v1/products/**").hasRole(ADMIN.name())
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic()
//                .and()
//                    .formLogin()
//                    .loginPage("/login")
//                    //after loggin in on_success
//                    .defaultSuccessUrl("/home", true)
//                .and()
//                .rememberMe()
//                .tokenValiditySeconds((int) TimeUnit.MINUTES.toSeconds(1))
//                .key("somethingsecuredkey") //defaults to 2 weeks
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                //if you enable CSRF then this line should be commented
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
//                .clearAuthentication(true)
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID", "remember-me")
//                .logoutSuccessUrl("/login");

    }

//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//
//        UserDetails user2 = User.builder()
//                .username("admin")
//                .password(encoder.encode("pass"))
//                .roles(ADMIN.name()) //spring saves it --> as ROLE_ADMIN
//                .build();
//
//        UserDetails user1 = User.builder()
//                .username("ben")
//                .password(encoder.encode("pass"))
//                .roles(USER.name()) //spring saves it --> as ROLE_USER
//                .build();
//
//        return new InMemoryUserDetailsManager(user1, user2);
//    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    
   

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(encoder);
        daoAuthenticationProvider.setUserDetailsService(applicationUserService);

        return daoAuthenticationProvider;
    }

}
