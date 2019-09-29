/**
 * 
 */
package com.ats.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring security configuration
 * @author Adondoni
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
 
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("ats").password("test").roles("USER");
    }
     
    @Override
    protected void configure(HttpSecurity http) throws Exception 
    {
      http.csrf().disable();

      http.authorizeRequests()
      	.antMatchers("/index.jsp").access("hasRole('USER')")
        .and().formLogin()
        .and().exceptionHandling().accessDeniedPage("/Access_Denied");
  
    }
}
