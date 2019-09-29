/**
 * 
 */
package com.ats.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Spring application configuration
 * @author Adondoni
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.ats.test.controller")
public class ApplicationConfiguration {

}
