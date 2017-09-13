package pankaj.com.repo.controller;

import java.util.List;

import pankaj.com.repo.model.RepoIssue;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by pankaj on 9/13/17.
 */

public interface RepoIssueAPI {

    @GET("repos/{owner}/{repo}/issues")
    Call<List<RepoIssue>> getResult(@Path("owner") String owner, @Path("repo") String repo);
}
