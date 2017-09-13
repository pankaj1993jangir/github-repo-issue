package pankaj.com.repo.repoissue;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pankaj.com.repo.R;
import pankaj.com.repo.adapter.IssueAdapter;
import pankaj.com.repo.controller.GetRepoIssueController;
import pankaj.com.repo.model.RepoIssue;

public class RepoActivity extends AppCompatActivity implements RepoIssueView, AdapterView.OnClickListener {

    public ProgressDialog pDialog;
    private EditText owner;
    private EditText repo;
    private RepoIssuePresenter presenter;
    private TextView totalIssue;

    private List<RepoIssue> issueList = new ArrayList<>();
    private RecyclerView issueRecyclerView;
    private IssueAdapter issueAdapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);
        owner = (EditText) findViewById(R.id.owner);
        repo = (EditText) findViewById(R.id.repo);
        totalIssue = (TextView) findViewById(R.id.totalIssue);
        findViewById(R.id.btGetIssue).setOnClickListener(this);

        iniliazieListView();

        presenter = new RepoIssuePresenterImpl(this);
    }

    private void iniliazieListView() {
        issueRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        issueAdapter = new IssueAdapter(issueList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        issueRecyclerView.setLayoutManager(mLayoutManager);
        issueRecyclerView.setItemAnimator(new DefaultItemAnimator());
        issueRecyclerView.setAdapter(issueAdapter);
        issueAdapter.notifyDataSetChanged();
    }


    @Override
    public void showProgress(String progressDialogMsg) {
        if (pDialog == null) {
            pDialog = new ProgressDialog(this);
            pDialog.setCanceledOnTouchOutside(false);
        }
        pDialog.setCancelable(true);
        pDialog.setMessage(progressDialogMsg);
        try {
            pDialog.show();
        } catch (Exception e) {

        }
    }

    @Override
    public void hideProgress() {
        if (pDialog != null) {
            pDialog.hide();
        }
    }

    @Override
    public void setOwnerNameError() {
        owner.setError(getString(R.string.owner_error));
    }

    @Override
    public void setRepoNameError() {
        repo.setError(getString(R.string.repo_error));
    }

    @Override
    public void fetchIssue(String owner, String repo) {
        new GetRepoIssueController(this).search(owner, repo);
    }

    @Override
    public void uploadIssueInList(List<RepoIssue> list) {
        issueList.clear();


        if (list == null || list.size() < 1) {
            showToast(getResources().getString(R.string.other_repo));
            totalIssue.setText(getResources().getString(R.string.other_repo));
            issueAdapter.notifyDataSetChanged();
            return;
        }

        issueList.addAll(list);
        issueAdapter.notifyDataSetChanged();

        totalIssue.setText("Total Issue: " + list.size());
        totalIssue.setVisibility(View.VISIBLE);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
    }

    @Override
    public void onClick(View v) {
        presenter.validateCredentials(owner.getText().toString(), repo.getText().toString());
    }
}
