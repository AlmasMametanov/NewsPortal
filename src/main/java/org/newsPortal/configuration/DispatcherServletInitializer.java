package org.newsPortal.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    @Override
    protected Class<?>[] getRootConfigClasses() {
        logger.debug("DispatcherServletInitializer + getRootConfigClasses");
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        logger.debug("DispatcherServletInitializer + getServletConfigClasses");
        return new Class[] {AppConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        logger.debug("DispatcherServletInitializer + getServletMappings");
        return new String[] {"/"};
    }
}
