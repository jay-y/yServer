package org.yserver.core.listener;

import org.springframework.web.context.WebApplicationContext;
import org.yserver.core.config.SpringContextHolder;

import javax.servlet.ServletContext;

public class WebContextListener extends org.springframework.web.context.ContextLoaderListener
{

    @Override
    public WebApplicationContext initWebApplicationContext(ServletContext servletContext)
    {
        if (!SpringContextHolder.printKeyLoadMessage())
        {
            return null;
        }
        WebApplicationContext context = super.initWebApplicationContext(servletContext);

        return context;
    }
}
