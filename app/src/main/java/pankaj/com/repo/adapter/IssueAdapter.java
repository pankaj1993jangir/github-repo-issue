package pankaj.com.repo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pankaj.com.repo.R;
import pankaj.com.repo.model.RepoIssue;

/**
 * Created by pankaj on 9/13/17.
 */

public class IssueAdapter extends RecyclerView.Adapter<IssueAdapter.MyViewHolder> {

    private List<RepoIssue> issueList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
        }
    }


    public IssueAdapter(List<RepoIssue> issueList) {
        this.issueList = issueList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repo_issue_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RepoIssue issue = issueList.get(position);
        holder.name.setText(issue.getTitle());
    }

    @Override
    public int getItemCount() {
        if (issueList == null) {
            issueList = new ArrayList<>();
        }
        return issueList.size();
    }
}