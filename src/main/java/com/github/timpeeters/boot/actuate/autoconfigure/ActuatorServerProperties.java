package com.github.timpeeters.boot.actuate.autoconfigure;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("management.server")
public class ActuatorServerProperties extends ServerProperties {
}
