package com.github.timpeeters.boot.actuate.tomcat;

import org.junit.Test;
import org.springframework.boot.context.embedded.AbstractEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.EmbeddedServletContainer;
import org.springframework.boot.web.servlet.ServletContextInitializer;

import static org.assertj.core.api.Assertions.assertThatCode;

public class ActuatorTomcatAutoConfigurationTest {
    @Test
    public void ignoreNonTomcatContainers() {
        assertThatCode(() -> new ActuatorTomcatAutoConfiguration().embeddedServletContainerCustomizer(null)
                .customize(new AbstractEmbeddedServletContainerFactory() {
                    @Override
                    public EmbeddedServletContainer getEmbeddedServletContainer(ServletContextInitializer... init) {
                        return null;
                    }
                })).doesNotThrowAnyException();
    }
}
