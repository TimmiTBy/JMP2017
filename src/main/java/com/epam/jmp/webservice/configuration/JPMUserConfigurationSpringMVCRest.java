package com.epam.jmp.webservice.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Ales on 19.03.2017.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.epam.jmp.webservice")
public class JPMUserConfigurationSpringMVCRest {


}
