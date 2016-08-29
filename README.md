Archaius configuration source for GitHub
========================================

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
