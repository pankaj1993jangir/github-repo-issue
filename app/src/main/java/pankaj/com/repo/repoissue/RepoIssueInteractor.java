package pankaj.com.repo.repoissue;

/**
 * Created by pankaj on 9/13/17.
 */

public interface RepoIssueInteractor {
    interface OnLoginFinishedListener {
        void onOwnerNameError();

        void onRepoNameError();

        void onSuccess(String owner, String repo);
    }

    void fetchIssue(String owner, String repo, OnLoginFinishedListener listener);
}
