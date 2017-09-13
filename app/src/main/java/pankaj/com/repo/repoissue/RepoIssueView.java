package pankaj.com.repo.repoissue;

import java.util.List;

import pankaj.com.repo.model.RepoIssue;

/**
 * Created by pankaj on 9/13/17.
 */

public interface RepoIssueView {
    void showProgress(String progressDialogMsg);

    void hideProgress();

    void setOwnerNameError();

    void setRepoNameError();

    void fetchIssue(String owner, String repo);

    void uploadIssueInList(List<RepoIssue> issueViewList);

    void showToast(String msg);
}
