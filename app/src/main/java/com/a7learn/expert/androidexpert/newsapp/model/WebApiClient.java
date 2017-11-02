package com.a7learn.expert.androidexpert.newsapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/2/2017.
 */

public interface WebApiClient {
    void getArticles(String sourcesId, GetArticlesCallback getArticlesCallback);

    void getSources(GetSourcesCallback getSourcesCallback);

    public interface GetArticlesCallback {
        void onArticlesReceived(List<Article> articles);

        void onGetArticlesError();
    }

    public interface GetSourcesCallback {
        void onSourcesReceived(List<Source> sources);

        void onGetSourcesError();
    }
}
