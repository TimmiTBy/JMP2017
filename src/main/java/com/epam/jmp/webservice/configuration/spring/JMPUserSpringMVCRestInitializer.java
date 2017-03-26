package com.epam.jmp.webservice.configuration.spring;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by Ales on 19.03.2017.
 */
public class JMPUserSpringMVCRestInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{JPMContextConfigurationSpringMVCRest.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{JPMContextConfigurationSpringMVCRest.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
