package pankaj.com.repo.repoissue;

/**
 * Created by pankaj on 9/13/17.
 */

public interface RepoIssuePresenter {
    void validateCredentials(String username, String password);

    void onDestroy();
}
