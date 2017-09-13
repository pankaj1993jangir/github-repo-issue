package pankaj.com.repo.repoissue;

/**
 * Created by pankaj on 9/13/17.
 */

public class RepoIssuePresenterImpl implements RepoIssuePresenter, RepoIssueInteractor.OnLoginFinishedListener {
    private RepoIssueView repoIssueView;
    private RepoIssueInteractor issueInteractor;

    public RepoIssuePresenterImpl(RepoIssueView repoIssueView) {
        this.repoIssueView = repoIssueView;
        this.issueInteractor = new RepoIssueInteractorImpl();
    }

    @Override
    public void validateCredentials(String owner, String repo) {
        if (repoIssueView != null) {
            repoIssueView.showProgress("Fetching Issue...");
        }

        issueInteractor.fetchIssue(owner, repo, this);
    }

    @Override
    public void onDestroy() {
        repoIssueView = null;
    }

    @Override
    public void onOwnerNameError() {
        if (repoIssueView != null) {
            repoIssueView.setOwnerNameError();
            repoIssueView.hideProgress();
        }
    }

    @Override
    public void onRepoNameError() {
        if (repoIssueView != null) {
            repoIssueView.setRepoNameError();
            repoIssueView.hideProgress();
        }
    }

    @Override
    public void onSuccess(String owner, String repo) {
        if (repoIssueView != null) {
            repoIssueView.fetchIssue(owner, repo);
        }
    }
}
