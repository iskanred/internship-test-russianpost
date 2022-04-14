package dev.iskandev.internshiptestrussianpost.github.repositories.controller;

import dev.iskandev.internshiptestrussianpost.github.repositories.exception.IllegalFilterValueException;
import dev.iskandev.internshiptestrussianpost.github.repositories.model.GitHubReposResponse;
import dev.iskandev.internshiptestrussianpost.github.repositories.service.GitHubRepoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@RequestMapping("${server.version_based_url_prefix}/github/repositories")
public class GitHubRepoController {

    private final Logger logger = LoggerFactory.getLogger(GitHubRepoController.class);

    private final GitHubRepoService service;

    @Autowired
    public GitHubRepoController(GitHubRepoService service) {
        this.service = service;
    }

    /**
     * Return object that contains list (of specified size) of GitHub repositories
     *  on specified topic and filtered by specified filter.
     * <br>Possible response status codes:<br>
     * <ol>
     *     <li>200 OK - if operation has been performed successfully</li>
     *     <li>400 Bad Request - if input data is not valid</li>
     *     <li>500 Internal Server Error</li>
     * </ol>
     * @param topic is topic of repositories search (e.g. {@literal java}, {@literal devops})
     * @param filter is filter of search
     *              <br>Possible values of filter parameter:</br>
     *              <ul>
     *                  <li>popular - repositories with the most stars</li>
     *              </ul>
     * @param count is size of list to return i.e. number of entries to show
     * @return object that contains list (of specified size) of GitHub repositories
     *  on specified topic and filtered by specified filter.
     * @throws IllegalFilterValueException if {@code filter} has illegal value
     * @throws IOException if I/O error occurred while request to service {@link GitHubRepoService}
     * @throws ResponseStatusException with code:
     *  <ul>
     *      <li>400 Bad Request - if some request parameters have incorrect values</li>
     *  </ul>
     */
    @GetMapping
    public GitHubReposResponse getRepos(@RequestParam String topic,
                                        @RequestParam String filter,
                                        @RequestParam(defaultValue = "20") int count)
            throws IllegalFilterValueException, IOException, ResponseStatusException
    {
        logger.info("GET /github/repositories request for getting repositories is received with parameters " +
                "topic = " + topic + "; filter = " + filter + "; count = " + count);

        /* Validation */
        if (count < 1 || count > 100) {
            var exception = new ResponseStatusException(HttpStatus.BAD_REQUEST, "Count must be in range [1, 100], but got: " + count);
            logger.info("Incorrect count parameter", exception);
            throw exception;
        }

        return service.getRepos(topic, filter, count);
    }
}
