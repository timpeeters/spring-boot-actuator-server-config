package com.github.timpeeters.boot.actuate.autoconfigure;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("management.server")
public class ActuatorServerProperties extends ServerProperties {
    @ConfigurationProperties(prefix = "management.server.jetty")
    @Override
    public Jetty getJetty() {
        return super.getJetty();
    }

    @ConfigurationProperties(prefix = "management.server.tomcat")
    @Override
    public Tomcat getTomcat() {
        return super.getTomcat();
    }

    @ConfigurationProperties(prefix = "management.server.undertow")
    @Override
    public Undertow getUndertow() {
        return super.getUndertow();
    }
}
