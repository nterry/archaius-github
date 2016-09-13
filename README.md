Archaius configuration source for GitHub
========================================

[![Travis](https://travis-ci.org/nterry/archaius-github.svg?branch=master)](https://travis-ci.org/nterry/archaius-github)&nbsp;&nbsp;&nbsp;&nbsp;[![Maven Central](https://img.shields.io/maven-central/v/io.github.nterry/archaius-github.svg?maxAge=2592000)](https://search.maven.org/#search%7Cga%7C1%7Cio.github.nterry)&nbsp;&nbsp;&nbsp;&nbsp;[![SonarQube Coverage](https://img.shields.io/sonar/http/sonarqube.com/io.github.nterry:archaius-github/coverage.svg?maxAge=2592000)](https://sonarqube.com/overview?id=io.github.nterry%3Aarchaius-github)&nbsp;&nbsp;&nbsp;&nbsp;[![SonarQube Tech Debt](https://img.shields.io/sonar/http/sonarqube.com/io.github.nterry:archaius-github/tech_debt.svg?maxAge=2592000)](https://sonarqube.com/overview?id=io.github.nterry%3Aarchaius-github)

Overview
--------
This project provides an implementation of
<a href="https://netflix.github.io/archaius/archaius-core-javadoc/com/netflix/config/PolledConfigurationSource.html">PolledConfigurationSource</a>
that is backed by a file hosted in either a GitHub repository or a Gist.


Usage
-----
The best option is using a repository as it is aware of changes and will only update when a change occurs.

Here is an example usage:

    DynamicConfiguration dynamicConfiguration = new DynamicConfiguration(
      new GitHubRepositoryConfigurationSource(
        new GitHubDetails("some-owner", "some-repository"), "path/to/properties/file.properties"),
        new FixedDelayPollingScheduler(10000, 10000, false));
        
    String prop = dynamicConfiguration.getString("some-property");    
    // There are many more methods in the DynamicConfiguration class for different types
    
Logging
-------

This library uses SLF4J for logging, but there is a small caveat. Some of the dependencies for this library use
java.util.commons.Logging. If you use that framework and have a logging.properties, simply put the following into
your logging.properties file:

    handlers = org.slf4j.bridge.SLF4JBridgeHandler
    
If you do not have a logging.properties, and you want to include the http client logs, simply put the following into 
your bootstrap code (App.main() or otherwise):

    SLF4JBridgeHandler.removeHandlersForRootLogger();
    SLF4JBridgeHandler.install();
    
Finally, there is a HUGE performance penalty in logging if you use the bridge handler above. Fortunately, there is a
simple way to fix the problem that causes said penalty. Simply put the following into your logback.xml (Assuming you
use logback. If you dont, look up the equivalent for your logging framework):

    <?xml version="1.0" encoding="UTF-8"?>
    <configuration>
    
        <contextListener class="ch.qos.logback.classic.jul.LevelChangePropagator">
            <!-- reset all previous level configurations of all j.u.l. loggers -->
            <resetJUL>true</resetJUL>
        </contextListener> 
    
        ...
        ...
        ...
    
    </configuration>
