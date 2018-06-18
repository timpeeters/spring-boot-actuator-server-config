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
