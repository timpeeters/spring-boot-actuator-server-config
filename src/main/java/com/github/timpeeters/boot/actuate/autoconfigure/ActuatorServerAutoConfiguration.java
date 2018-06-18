package com.github.timpeeters.boot.actuate.autoconfigure;

import org.springframework.boot.actuate.autoconfigure.ManagementContextConfiguration;
import org.springframework.boot.actuate.autoconfigure.ManagementServerProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@ConditionalOnBean(ManagementServerProperties.class)
@ConditionalOnWebApplication
@ManagementContextConfiguration
public class ActuatorServerAutoConfiguration {
    @Bean
    @Primary
    public ActuatorServerProperties serverProperties() {
        return new ActuatorServerProperties();
    }
}
