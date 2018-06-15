package com.github.timpeeters.boot.actuate.tomcat;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(properties = {
        "management.port=0",
        "management.tomcat.max-threads=5",
        "management.tomcat.min-spare-threads=1",
        "server.tomcat.max-threads=20",
        "server.tomcat.min-spare-threads=10",
        "server.port=0"})
public class ActuatorOnDifferentPortIntegrationTest extends AbstractIntegrationTest {
    @Test
    public void verifyManagementThreadPool() {
        assertThat(getManagementProtocol()).satisfies(p -> {
            assertThat(p.getMaxThreads()).isEqualTo(5);
            assertThat(p.getMinSpareThreads()).isEqualTo(1);
        });
    }

    @Test
    public void verifyDefaultThreadPool() {
        assertThat(getDefaultProtocol()).satisfies(p -> {
            assertThat(p.getMaxThreads()).isEqualTo(20);
            assertThat(p.getMinSpareThreads()).isEqualTo(10);
        });
    }
}
