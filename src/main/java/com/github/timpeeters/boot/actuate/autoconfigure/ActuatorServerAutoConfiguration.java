package com.github.timpeeters.boot.actuate.autoconfigure;

import org.springframework.boot.actuate.autoconfigure.web.ManagementContextConfiguration;
import org.springframework.boot.actuate.autoconfigure.web.server.ConditionalOnManagementPort;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.embedded.TomcatWebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

@ConditionalOnBean(name = "managementServletContext")
@ConditionalOnManagementPort(ManagementPortType.DIFFERENT)
@ConditionalOnWebApplication
@ManagementContextConfiguration
public class ActuatorServerAutoConfiguration {
    @Bean("server-org.springframework.boot.autoconfigure.web.ServerProperties")
    @Primary
    public ActuatorServerProperties serverProperties() {
        return new ActuatorServerProperties();
    }

    @Bean
    @Primary
    public TomcatWebServerFactoryCustomizer tomcatWebServerFactoryCustomizer(
            Environment environment, ActuatorServerProperties props) {

        return new TomcatWebServerFactoryCustomizer(environment, props);
    }
}
