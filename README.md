Spring Boot Actuator Server Config
==================================

[![Known Vulnerabilities](https://snyk.io/test/github/timpeeters/spring-boot-actuator-server-config/badge.svg?targetFile=pom.xml)](https://snyk.io/test/github/timpeeters/spring-boot-actuator-server-config?targetFile=pom.xml)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.timpeeters/spring-boot-actuator-server-config/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.timpeeters/spring-boot-actuator-server-config)


This project allows to configure the Spring Boot management connector separately from the default connector.


Versions
--------

Multiple branches are maintained to support multiple Spring Boot versions.
The following tables show the relation between the Spring Boot version and the Spring Boot Actuator Server Config version.

| Spring Boot | Spring Boot Actuator Server Config | Branch |
| :---        | :---                               | :---   |
| 1.5.x       | 1.0.x                              | 1.0.x  |
| 2.0.x       | 2.0.x                              | 2.0.x  |
| 2.1.x       | 2.1.x                              | master |


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
management.server.port=8081
management.server.tomcat.max-threads=5
management.server.tomcat.min-spare-threads=${management.tomcat.max-threads}

server.port=8080
server.tomcat.max-threads=200
server.tomcat.min-spare-threads=${server.tomcat.max-threads}
```


References
----------
- https://github.com/spring-projects/spring-boot/issues/9560
