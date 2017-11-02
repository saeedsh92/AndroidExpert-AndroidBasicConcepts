package com.a7learn.expert.androidexpert.newsapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.a7learn.expert.androidexpert.R;
import com.a7learn.expert.androidexpert.newsapp.model.Article;
import com.a7learn.expert.androidexpert.newsapp.model.Source;
import com.a7learn.expert.androidexpert.newsapp.model.WebApiClient;
import com.a7learn.expert.androidexpert.newsapp.model.WebApiClientImpl;

import java.util.List;

public class NewsListActivity extends AppCompatActivity implements WebApiClientImpl.GetArticlesCallback ,
WebApiClientImpl.GetSourcesCallback{
    private WebApiClient webApiClient;
    private RecyclerView newsListRecyclerView;
    private NewsAdapter newsAdapter;
    private static final String TAG = "NewsListActivity";
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        init();
        setupViews();
        getArticlesFromServer();
        getSourcesFromServer();
    }

    private void init() {
        webApiClient =new WebApiClientImpl(this);
    }

    private void setupViews() {
        newsListRecyclerView=(RecyclerView)findViewById(R.id.rv_newsList_articles);
        newsListRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        progressBar=(ProgressBar)findViewById(R.id.progressBar_newsList);
    }

    private void getArticlesFromServer(){
        progressBar.setVisibility(View.VISIBLE);
        webApiClient.getArticles("al-jazeera-english",this);
    }

    private void getSourcesFromServer(){
        webApiClient.getSources(this);
    }

    @Override
    public void onArticlesReceived(List<Article> articles) {
        newsAdapter=new NewsAdapter(this,articles);
        newsListRecyclerView.setAdapter(newsAdapter);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onGetArticlesError() {
        Toast.makeText(this,getString(R.string.newsList_getArticlesErrorMessage),Toast.LENGTH_LONG).show();
    }


    @Override
    public void onSourcesReceived(List<Source> sources) {
        RecyclerView sourcesRecyclerView=(RecyclerView)findViewById(R.id.rv_newsList_sources);
        sourcesRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        SourceAdapter sourceAdapter=new SourceAdapter(this,
                sources,
                new SourceAdapter.SourceViewCallback() {
                    @Override
                    public void onSourceItemClick(String sourceId) {
                        webApiClient.getArticles(sourceId,NewsListActivity.this);
                    }
                });
        sourcesRecyclerView.setAdapter(sourceAdapter);
    }

    @Override
    public void onGetSourcesError() {
        Toast.makeText(this,getString(R.string.newsList_getSourcesErrorMessage),Toast.LENGTH_LONG).show();
    }
}
