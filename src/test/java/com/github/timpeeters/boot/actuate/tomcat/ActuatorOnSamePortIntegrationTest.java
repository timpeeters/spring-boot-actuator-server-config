package com.github.timpeeters.boot.actuate.tomcat;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
        "management.tomcat.max-threads=5",
        "management.tomcat.min-spare-threads=1",
        "server.tomcat.max-threads=20",
        "server.tomcat.min-spare-threads=10"})
public class ActuatorOnSamePortIntegrationTest extends AbstractIntegrationTest {
    @Test
    public void verifyManagementThreadPool() {
        assertThat(getManagementProtocol())
                .as("When running on the same port, there is no separate management thread pool. " +
                        "The management specific thread pool settings should not be taken into account.")
                .satisfies(p -> {
                    assertThat(p.getMaxThreads()).isEqualTo(20);
                    assertThat(p.getMinSpareThreads()).isEqualTo(10);
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
