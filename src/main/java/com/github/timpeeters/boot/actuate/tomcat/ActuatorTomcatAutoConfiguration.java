package com.github.timpeeters.boot.actuate.tomcat;

import org.apache.coyote.AbstractProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.autoconfigure.EndpointWebMvcAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.ManagementContextConfiguration;
import org.springframework.boot.actuate.autoconfigure.ManagementServerProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@ConditionalOnBean(ManagementServerProperties.class)
@ConditionalOnWebApplication
@EnableConfigurationProperties(ActuatorTomcatProperties.class)
@ManagementContextConfiguration
public class ActuatorTomcatAutoConfiguration {
    private static final Logger LOG = LoggerFactory.getLogger(EndpointWebMvcAutoConfiguration.class);

    @Bean
    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer(ActuatorTomcatProperties props) {
        return c -> castIntoOptional(c, TomcatEmbeddedServletContainerFactory.class).ifPresent(tomcat -> {
            LOG.info("Customize Tomcat management connector using {}", props);

            tomcat.addConnectorCustomizers(tomcatConnectorCustomizer(props));
        });
    }

    @Bean
    public TomcatConnectorCustomizer tomcatConnectorCustomizer(ActuatorTomcatProperties props) {
        return c -> castIntoOptional(c.getProtocolHandler(), AbstractProtocol.class).ifPresent(p -> {
            p.setMaxThreads(props.getMaxThreads());
            p.setMinSpareThreads(props.getMinSpareThreads());
        });
    }

    private <T> Optional<T> castIntoOptional(Object value, Class<T> type) {
        if (type.isInstance(value)) {
            return Optional.of(type.cast(value));
        }

        return Optional.empty();
    }
}
