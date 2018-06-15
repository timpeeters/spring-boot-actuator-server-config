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
package com.github.timpeeters.boot.actuate.tomcat;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.StringJoiner;

@ConfigurationProperties(prefix = "management.tomcat")
public class ActuatorTomcatProperties {
    /**
     * Maximum amount of worker threads.
     */
    private int maxThreads = 5;

    /**
     * Minimum amount of worker threads.
     */
    private int minSpareThreads = 5;

    public int getMaxThreads() {
        return maxThreads;
    }

    public void setMaxThreads(int maxThreads) {
        this.maxThreads = maxThreads;
    }

    public int getMinSpareThreads() {
        return minSpareThreads;
    }

    public void setMinSpareThreads(int minSpareThreads) {
        this.minSpareThreads = minSpareThreads;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", getClass().getSimpleName() + "[", "]")
                .add("maxThreads=" + getMaxThreads())
                .add("minSpareThreads=" + getMinSpareThreads())
                .toString();
    }
}
