/*
 * Copyright (C) 2011 Thomas Akehurst
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.timpeeters.boot.actuate.autoconfigure;

import org.apache.coyote.AbstractProtocol;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public abstract class AbstractIT {
    @Autowired
    private WebServerApplicationContext applicationContext;

    @Autowired
    private TestApplication.ManagementContextResolver managementContextResolver;

    protected AbstractProtocol<?> getManagementProtocol() {
        return getProtocol(managementContextResolver.getManagementContext().orElse(applicationContext));
    }

    protected AbstractProtocol<?> getDefaultProtocol() {
        return getProtocol(applicationContext);
    }

    private AbstractProtocol<?> getProtocol(WebServerApplicationContext context) {
        return (AbstractProtocol) getEmbeddedServletContainer(context).getTomcat().getConnector().getProtocolHandler();
    }

    private TomcatWebServer getEmbeddedServletContainer(WebServerApplicationContext context) {
        return (TomcatWebServer) context.getWebServer();
    }

    @SpringBootApplication
    public static class TestApplication {
        public static void main(String[] args) {
            SpringApplication.run(TestApplication.class, args);
        }

        @Bean
        public ManagementContextResolver managementContextResolver() {
            return new ManagementContextResolver();
        }

        public static class ManagementContextResolver {
            private WebServerApplicationContext managementContext;

            public Optional<WebServerApplicationContext> getManagementContext() {
                return Optional.ofNullable(managementContext);
            }

            @EventListener
            public void onEvent(WebServerInitializedEvent event) {
                Optional.ofNullable(event.getApplicationContext().getServerNamespace())
                        .filter(n -> "management".equals(n))
                        .ifPresent(n -> managementContext = event.getApplicationContext());
            }
        }
    }
}
