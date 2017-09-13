package pankaj.com.repo.controller;

import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import pankaj.com.repo.model.RepoIssue;
import pankaj.com.repo.repoissue.RepoActivity;
import pankaj.com.repo.repoissue.RepoIssuePresenter;
import pankaj.com.repo.repoissue.RepoIssueView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pankaj on 9/13/17.
 */

public class GetRepoIssueController implements Callback<List<RepoIssue>> {


    private static final String BASE_URL = "https://api.github.com/";
    private RepoIssueView viewModel;

    public GetRepoIssueController(RepoIssueView viewModel) {
        this.viewModel = viewModel;
    }

    public void search(String owner, String repo) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RepoIssueAPI api = retrofit.create(RepoIssueAPI.class);

        Call<List<RepoIssue>> call = api.getResult(owner, repo);
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<List<RepoIssue>> call, Response<List<RepoIssue>> response) {
        viewModel.hideProgress();
        viewModel.uploadIssueInList(response.body());
    }

    @Override
    public void onFailure(Call<List<RepoIssue>> call, Throwable t) {
        try {
            viewModel.showToast("Something Went Wrong. Trying Again, hang on...");
            call.enqueue(this);
        } catch (Exception e) {

        }
    }
}
