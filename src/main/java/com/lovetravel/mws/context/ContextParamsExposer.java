package com.lovetravel.mws.context;

import javax.servlet.ServletContext;
import org.springframework.web.context.ServletContextAware;

/**
 * Briefly describe what this class does.
 */
public class ContextParamsExposer implements ServletContextAware {
    
    private ServletContext servletContext;
    private String resourceRoot;
    public void init() {
        this.resourceRoot = "/resources";
        this.servletContext.setAttribute("resourceRoot", this.servletContext.getContextPath() + resourceRoot);
        this.servletContext.setAttribute("ctxPath", this.servletContext.getContextPath());
    }
    
    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public String getResourceRoot() {
        return resourceRoot;
    }

}
