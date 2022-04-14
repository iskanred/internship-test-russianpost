package dev.iskandev.internshiptestrussianpost.github.repositories.service;

import dev.iskandev.internshiptestrussianpost.github.repositories.exception.IllegalFilterValueException;
import dev.iskandev.internshiptestrussianpost.github.repositories.model.GitHubReposResponse;

import java.io.IOException;

public interface GitHubRepoService {

    /**
     * Return object that contains list (of specified size) of GitHub repositories
     *  on specified topic and filtered by specified filter.
     * @param topic is topic of repositories search (e.g. {@literal java}, {@literal devops})
     * @param filter is filter of search
     *              <br>Possible values of filter parameter:</br>
     *              <ul>
     *                  <li>popular - repositories with the most stars</li>
     *              </ul>
     * @param count is size of list to return
     * @return object that contains list of urls (of specified size) of GitHub repositories
     *  on specified topic and filtered by specified filter
     * @throws IOException if I/O error occurs while working with datasource (e.g. Website API)
     * @throws IllegalFilterValueException if {@code filter} is incorrect value
     */
    GitHubReposResponse getRepos(String topic, String filter, int count)
            throws IOException, IllegalFilterValueException;
}
