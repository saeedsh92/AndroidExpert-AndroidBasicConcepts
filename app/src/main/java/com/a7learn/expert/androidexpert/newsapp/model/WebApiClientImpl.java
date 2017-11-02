package com.a7learn.expert.androidexpert.newsapp.model;

import android.content.Context;
import android.util.Log;

import com.a7learn.expert.androidexpert.RequestQueueContainer;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/2/2017.
 */

public class WebApiClientImpl implements WebApiClient{
    private Context context;
    private static final String TAG = "WebApiClientImpl";

    private static final String API_KEY = "7da1d69096ce497d805cc484fb90e8ee";

    public WebApiClientImpl(Context context) {
        this.context = context;
    }

    @Override
    public void getArticles(String sourcesId, final GetArticlesCallback getArticlesCallback) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v1/articles?source="+sourcesId+"&apiKey=" + API_KEY,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, "onResponse: "+response);
                        List<Article> articles=new ArrayList<>();
                        try {
                            JSONArray articlesJsonArray=response.getJSONArray("articles");
                            for (int i = 0; i < articlesJsonArray.length(); i++) {
                                Article article=new Article();
                                article=parseArticleJsonObject(articlesJsonArray.getJSONObject(i));
                                if (article!=null){
                                    articles.add(article);
                                }
                            }
                            getArticlesCallback.onArticlesReceived(articles);
                        } catch (JSONException e) {
                            getArticlesCallback.onGetArticlesError();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getArticlesCallback.onGetArticlesError();
            }
        });
        RequestQueueContainer.getInstance(context).add(request);
    }

    @Override
    public void getSources(final GetSourcesCallback getSourcesCallback) {
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v1/sources?language=en",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray sourcesJsonArray=response.getJSONArray("sources");
                            List<Source> sources=new ArrayList<>();
                            for (int i = 0; i < sourcesJsonArray.length(); i++) {
                                Source source=parseSourceJsonObject(sourcesJsonArray.getJSONObject(i));
                                if (source!=null){
                                    sources.add(source);
                                }
                            }
                            getSourcesCallback.onSourcesReceived(sources);
                        } catch (JSONException e) {
                            getSourcesCallback.onGetSourcesError();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getSourcesCallback.onGetSourcesError();
            }
        });
        RequestQueueContainer.getInstance(context).add(request);
    }

    private Article parseArticleJsonObject(JSONObject jsonObject){
        Article article=new Article();
        try {
            article.setTitle(jsonObject.getString("title"));
            article.setAuthor(jsonObject.getString("author"));
            article.setDescription(jsonObject.getString("description"));
            article.setUrl(jsonObject.getString("url"));
            article.setImageUrl(jsonObject.getString("urlToImage"));
            article.setDate(jsonObject.getString("publishedAt"));
            return article;
        }catch (JSONException e){
            Log.e(TAG, "parseArticleJsonObject: "+e.toString());
            return null;
        }
    }

    private Source parseSourceJsonObject(JSONObject jsonObject){
        Source source=new Source();
        try {
            source.setId(jsonObject.getString("id"));
            source.setTitle(jsonObject.getString("name"));
            return source;
        } catch (JSONException e) {
            Log.e(TAG, "parseSourceJsonObject: "+e.toString());
            return null;
        }
    }

}
