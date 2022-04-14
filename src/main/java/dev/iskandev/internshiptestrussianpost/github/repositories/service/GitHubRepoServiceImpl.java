package dev.iskandev.internshiptestrussianpost.github.repositories.service;

import dev.iskandev.internshiptestrussianpost.github.repositories.exception.IllegalFilterValueException;
import dev.iskandev.internshiptestrussianpost.github.repositories.model.GitHubReposResponse;
import dev.iskandev.internshiptestrussianpost.github.repositories.repository.GitHubRepoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GitHubRepoServiceImpl implements GitHubRepoService {

    private final GitHubRepoRepository repository;

    @Autowired
    public GitHubRepoServiceImpl(GitHubRepoRepository repository) {
        this.repository = repository;
    }

    @Override
    public GitHubReposResponse getRepos(String topic, String filter, int count) throws IOException, IllegalFilterValueException {
        return repository.getRepos(topic, filter, count);
    }
}
