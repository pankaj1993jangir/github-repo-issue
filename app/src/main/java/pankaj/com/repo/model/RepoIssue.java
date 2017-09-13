
package pankaj.com.repo.model;

import java.util.List;

import lombok.Data;

/**
 * Created by pankaj on 9/13/17.
 */

@Data
public class RepoIssue {

    private String url;
    private String repositoryUrl;
    private String labelsUrl;
    private String commentsUrl;
    private String eventsUrl;
    private String htmlUrl;
    private Integer id;
    private Integer number;
    private String title;
    private User user;
    private List<Label> labels = null;
    private String state;
    private Boolean locked;
    private Object assignee;
    private List<Object> assignees = null;
    private Object milestone;
    private Integer comments;
    private String createdAt;
    private String updatedAt;
    private Object closedAt;
    private String authorAssociation;
    private String body;

}
