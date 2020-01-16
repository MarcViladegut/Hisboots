package com.domingo.hisboots.api;

import com.domingo.hisboots.Models.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("top-headlines")
    Call<News> getNews(
            @Query("country") String country,
            @Query("category") String category,
            @Query("q") String theme,
            @Query("apiKey") String apiKey
    );
}
