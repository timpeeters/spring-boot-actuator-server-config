Spring Boot Actuator Server Config
==================================

[![Build Status](https://api.travis-ci.org/timpeeters/spring-boot-actuator-server-config.svg?branch=master)](https://www.travis-ci.org/timpeeters/spring-boot-actuator-server-config)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/266850f2744f4d84af468b1ccd40dc2c)](https://www.codacy.com/app/timpeeters/spring-boot-actuator-server-config?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=timpeeters/spring-boot-actuator-server-config&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/266850f2744f4d84af468b1ccd40dc2c)](https://www.codacy.com/app/timpeeters/spring-boot-actuator-server-config?utm_source=github.com&utm_medium=referral&utm_content=timpeeters/spring-boot-actuator-server-config&utm_campaign=Badge_Coverage)
[![Known Vulnerabilities](https://snyk.io/test/github/timpeeters/spring-boot-actuator-server-config/badge.svg?targetFile=pom.xml)](https://snyk.io/test/github/timpeeters/spring-boot-actuator-server-config?targetFile=pom.xml)
[![Dependabot Status](https://api.dependabot.com/badges/status?host=github&repo=timpeeters/spring-boot-actuator-server-config)](https://dependabot.com)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.timpeeters/spring-boot-actuator-server-config.svg)](https://repo1.maven.org/maven2/com/github/timpeeters/spring-boot-actuator-server-config/)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.timpeeters/spring-boot-actuator-server-config/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.timpeeters/spring-boot-actuator-server-config)


This project allows to configure the Spring Boot management connector separately from the default connector.


Installation
------------

1. Add the following Maven dependency:

```xml
<dependency>
    <groupId>com.github.timpeeters</groupId>
    <artifactId>spring-boot-actuator-server-config</artifactId>
    <version>X.X.X</version>
</dependency>
```


Thread pool example
-------------------

Spring Boot allows to configure the web container to listen on multiple ports.
Using the `server.port` property, we can configure the port for the public traffic.
Using the `management.port`, we can configure the port for the management (Actuator) traffic.
It might make sense to use a big thread pool for the public traffic while using a smaller thread pool for management traffic.
By default Spring Boot will apply all the settings of the default port to the management port as well.
Using `spring-boot-actuator-server-config`, we can configure the management port independently.

Below we leverage the following properties to configure the management thread pool: `management.server.tomcat.max-threads` and `management.server.tomcat.min-spare-threads`.

```properties
management.port=8081
management.server.tomcat.max-threads=5
management.server.tomcat.min-spare-threads=${management.tomcat.max-threads}

server.port=8080
server.tomcat.max-threads=200
server.tomcat.min-spare-threads=${server.tomcat.max-threads}
```


References
----------
https://github.com/spring-projects/spring-boot/issues/9560
