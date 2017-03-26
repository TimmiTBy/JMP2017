package com.epam.jmp.webservice.configuration.spring;

import com.epam.jmp.webservice.dao.UserDao;
import com.epam.jmp.webservice.dao.UserDaoImpl;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Ales on 19.03.2017.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.epam.jmp.webservice")
public class JPMContextConfigurationSpringMVCRest extends WebMvcConfigurerAdapter {

    @Bean(name = "userDao")
    public UserDao getUserDao(SessionFactory sessionFactory) {
        return new UserDaoImpl(sessionFactory);
    }


}
