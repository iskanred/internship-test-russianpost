package dev.iskandev.internshiptestrussianpost.github.repositories.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

/**
 * Represents one GitHub repository
 */
@JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer", "handler"})
public class GitHubRepo {

    private String url;

    public GitHubRepo() { }

    public GitHubRepo(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GitHubRepo)) return false;
        GitHubRepo that = (GitHubRepo) o;
        return Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }

    @Override
    public String toString() {
        return "GitHubRepo{" +
                "url='" + url + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
