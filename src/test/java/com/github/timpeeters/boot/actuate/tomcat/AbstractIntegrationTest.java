package com.github.timpeeters.boot.actuate.tomcat;

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
