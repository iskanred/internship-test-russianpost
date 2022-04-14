package dev.iskandev.internshiptestrussianpost.github.repositories.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.iskandev.internshiptestrussianpost.github.repositories.exception.IllegalFilterValueException;
import dev.iskandev.internshiptestrussianpost.github.repositories.model.GitHubRepo;
import dev.iskandev.internshiptestrussianpost.github.repositories.model.GitHubReposResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GitHubRepoRepositoryImpl implements GitHubRepoRepository {

    private final Logger logger = LoggerFactory.getLogger(GitHubRepoRepositoryImpl.class);

    private final String GITHUB_API_URL;

    @Autowired
    public GitHubRepoRepositoryImpl(@Value("${api.github.search.repositories}") String GITHUB_API_URL) {
        this.GITHUB_API_URL = GITHUB_API_URL;
    }

    @Override
    public GitHubReposResponse getRepos(String topic, String filter, int count) throws IOException, IllegalFilterValueException {
        /* Form query line with request parameters */
        String query = getQuery(topic, filter, count);

        /* Open connection with GitHub API */
        var in = getConnectionInputStream(query);

        /* Read repos from Json, add to object and return */
        return new GitHubReposResponse(readReposJson(in));
    }

    /**
     * Convert filter criterion from service's value to corresponding API value.
     * @param filter is criterion to convert.
     * @return value of corresponding API filter criterion.
     * @throws IllegalFilterValueException if filter has illegal value
     */
    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    private String getSearchSortParameter(String filter) throws IllegalFilterValueException {
        // Switch is used for future extension of possible filter values
        switch (filter) {
            case "popular" : return "stars";
            default : {
                var exception = new IllegalFilterValueException();
                logger.info("Incorrect filter criterion: " + filter, exception);
                throw exception;
            }
        }
    }

    /**
     * Form a query line with GET request parameters.
     * @param topic is topic of repositories search (e.g. {@literal java}, {@literal devops})
     * @param filter is filter of search
     *              <br>Possible values of filter parameter:</br>
     *              <ul>
     *                  <li>popular - repositories with the most stars</li>
     *              </ul>
     * @param count is size of list to return
     * @return query line with GET request parameters which is the part of URL.
     * @throws IllegalFilterValueException if filter has illegal value
     */
    private String getQuery(String topic, String filter, int count) throws IllegalFilterValueException {
        // sort is filter criterion
        var sortParam = getSearchSortParameter(filter);
        // request parameters
        return "?q=" + topic + "&sort=" + sortParam + "&order=desc&per_page=" + count;
    }

    /**
     * Open connection with API and return input stream.
     * @param query is query line with request parameters.
     * @return input stream of opened connection
     * @throws IOException if I/O errors occurs while getting input stream
     */
    private InputStream getConnectionInputStream(String query) throws IOException {
        var connection = new URL(GITHUB_API_URL + query).openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        return connection.getInputStream();
    }

    /**
     * Read list of repos' urls from input stream that is in json format.
     * @param in is input stream to read from
     * @return list of repos' urls
     * @throws IOException if I/O errors occurs while reading from input stream
     */
    private List<GitHubRepo> readReposJson(InputStream in) throws IOException {
        var repos = new ArrayList<GitHubRepo>();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(in); // start from root
        JsonNode reposNode = rootNode.path("items");

        // Iterate through all repos obtained
        var reposItr = reposNode.elements();
        while (reposItr.hasNext()) {
            JsonNode repoNode = reposItr.next();
            JsonNode repoURLNode = repoNode.path("html_url");
            // Obtain url, add to repo object and add it to list
            repos.add(new GitHubRepo(repoURLNode.asText()));
        }

        return repos;
    }
}
