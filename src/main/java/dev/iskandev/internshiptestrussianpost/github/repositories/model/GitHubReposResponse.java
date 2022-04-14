package dev.iskandev.internshiptestrussianpost.github.repositories.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

/**
 * Simple data class for response to request for a list of GitHub repositories
 */
@JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer", "handler"})
public class GitHubReposResponse {

    @JsonProperty("repositories_list")
    private final List<GitHubRepo> repos;

    public GitHubReposResponse(List<GitHubRepo> repos) {
        this.repos = repos;
    }

    public List<GitHubRepo> getRepos() {
        return repos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GitHubReposResponse)) return false;
        GitHubReposResponse that = (GitHubReposResponse) o;
        return Objects.equals(repos, that.repos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(repos);
    }

    @Override
    public String toString() {
        return "GitHubReposResponse{" +
                "repos=" + repos +
                '}';
    }
}
