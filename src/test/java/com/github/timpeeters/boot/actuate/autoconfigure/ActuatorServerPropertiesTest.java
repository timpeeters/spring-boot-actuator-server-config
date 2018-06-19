package com.github.timpeeters.boot.actuate.autoconfigure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = {
        "debug=true",
        "management.server.jetty.acceptors=1",
        "management.server.tomcat.max-threads=2",
        "management.server.undertow.worker-threads=3"
})
public class ActuatorServerPropertiesTest {
    @Autowired
    private ActuatorServerProperties props;

    @Test
    public void jetty() {
        assertThat(props.getJetty().getAcceptors()).isEqualTo(1);
    }

    @Test
    public void tomcat() {
        assertThat(props.getTomcat().getMaxThreads()).isEqualTo(2);
    }

    @Test
    public void undertow() {
        assertThat(props.getUndertow().getWorkerThreads()).isEqualTo(3);
    }

    @Configuration
    @EnableConfigurationProperties(ActuatorServerProperties.class)
    public static class TestConfiguration {
    }
}
