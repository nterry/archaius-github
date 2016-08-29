package io.github.nterry.archaius.github.config;

import com.netflix.config.PolledConfigurationSource;

/**
 * A {@link PolledConfigurationSource} backed by GitHub.
 * Uses the GitHub v3 API. See <a href="https://developer.github.com/v3/">the GitHub v3 documentation</a> for more details.
 *
 * @author Nicholas Terry
 */
public interface GitHubConfigurationSource extends PolledConfigurationSource {
}
