package com.myproject.contactbook.configuration;

import com.myproject.contactbook.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.IGNORED_ORDER)
@Import(UserAuthService.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserAuthService userAuthService;
    @Value("${controller.path.dummylogout")
    private String dummyLogout;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                cors().
                and().
                csrf().disable().
                authorizeRequests().
                antMatchers("/manage/**").
                authenticated().
                and().
                httpBasic().
                and().
                headers().
                frameOptions().
                and().
                xssProtection();

        http.logout().logoutRequestMatcher(new AntPathRequestMatcher(dummyLogout));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(userAuthService);
    }

}