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
