package com.api.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.stereotype.Component;

@Component
public class PortalTomcatWebServerCustomizer implements WebServerFactoryCustomizer {

    @Override
    public void customize(WebServerFactory factory) {
        TomcatServletWebServerFactory containerFactory = (TomcatServletWebServerFactory) factory;
        containerFactory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
            @Override
            public void customize(Connector connector) {
                connector.setAttribute("relaxedQueryChars", "[]|{}^");
                connector.setAttribute("relaxedPathChars", "[]|");
            }
        });
    }
}