package com.shtura.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.shtura.helper.service.security.AuthentificationService;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthentificationService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
        auth.authenticationEventPublisher(new AuthenticationEventPublisher() {
            
            @Override
            public void publishAuthenticationSuccess(Authentication arg0) {
                // TODO Auto-generated method stub
                System.out.println("true" + arg0.getName());
            }
            
            @Override
            public void publishAuthenticationFailure(AuthenticationException arg0, Authentication arg1) {
                // TODO Auto-generated method stub
                System.out.println("false");
                
            }
        });
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
            .antMatchers("/login", "/register", "/myerror", "/css/*", "/js/*", "/fonts/*").permitAll()
            .antMatchers("/welcome").access("hasRole('ROLE_DEFAULT')")
            .antMatchers("/connect", "/transference", "/sendMessag", "/fixIndexFileCorrupt", "/updatePosForTest", "/posVersion", "/createLinkiRetailManagement", "/createLinkiRetailPOS").access("hasAnyRole('ROLE_IT', 'ROLE_PO', 'ROLE_DEMONSTRATION')")
            .antMatchers("/it", "/installCloudClient", "/preparationForWorkWithHelper", "/runPlaybook", "/test").access("hasAnyRole('ROLE_IT', 'ROLE_DEMONSTRATION')")
            .antMatchers("/po").access("hasAnyRole('ROLE_PO', 'ROLE_DEMONSTRATION')")
            .antMatchers("/demonstration").access("hasRole('ROLE_DEMONSTRATION')")
            .antMatchers("/admin", "/addRoleToUser", "/disableOrEnableUser", "/editUserEmail", "/fileUpload").access("hasAnyRole('ROLE_ADMIN', 'ROLE_DEMONSTRATION')")
            .anyRequest().authenticated()
            .and()
            //.formLogin().defaultSuccessUrl("/", true).and()
            .formLogin().loginPage("/login").and().logout()
            .clearAuthentication(true).and().csrf().disable();
        // // Authenticate users
        // with HTTP basic
        // authentication
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}