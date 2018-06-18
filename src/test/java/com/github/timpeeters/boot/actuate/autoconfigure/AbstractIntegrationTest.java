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
import org.springframework.boot.actuate.autoconfigure.ManagementContextResolver;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public abstract class AbstractIntegrationTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ManagementContextResolver managementContextResolver;

    protected AbstractProtocol<?> getManagementProtocol() {
        return getProtocol(managementContextResolver.getApplicationContext());
    }

    protected AbstractProtocol<?> getDefaultProtocol() {
        return getProtocol(applicationContext);
    }

    private AbstractProtocol<?> getProtocol(ApplicationContext context) {
        return (AbstractProtocol) getEmbeddedServletContainer(context).getTomcat().getConnector().getProtocolHandler();
    }

    private TomcatEmbeddedServletContainer getEmbeddedServletContainer(ApplicationContext context) {
        return (TomcatEmbeddedServletContainer) ((EmbeddedWebApplicationContext) context).getEmbeddedServletContainer();
    }

    @SpringBootApplication
    public static class TestApplication {
        public static void main(String[] args) {
            SpringApplication.run(TestApplication.class, args);
        }
    }
}
