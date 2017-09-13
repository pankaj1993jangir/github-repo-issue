package pankaj.com.repo.repoissue;

import android.text.TextUtils;

/**
 * Created by pankaj on 9/13/17.
 */

public class RepoIssueInteractorImpl implements RepoIssueInteractor {
    @Override
    public void fetchIssue(String owner, String repo, OnLoginFinishedListener listener) {
        boolean error = false;
        if (TextUtils.isEmpty(owner)) {
            listener.onOwnerNameError();
            error = true;
        }
        if (TextUtils.isEmpty(repo)) {
            listener.onRepoNameError();
            error = true;
        }
        if (!error) {
            listener.onSuccess(owner, repo);
        }
    }
}
