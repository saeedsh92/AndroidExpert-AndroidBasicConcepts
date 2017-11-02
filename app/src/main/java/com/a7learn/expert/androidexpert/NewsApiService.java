package com.a7learn.expert.androidexpert;

import android.content.Context;

import com.a7learn.expert.androidexpert.newsapp.model.Article;
import com.android.volley.DefaultRetryPolicy;
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
 * Created by user on 10/29/2017.
 */

public class NewsApiService {
    private Context context;
    public NewsApiService(Context context){
        this.context=context;
    }

    public void getArticles(String source, final ResultCallback resultCallback){
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET,
                "https://newsapi.org/v1/articles?source=" + source + "&apiKey=7da1d69096ce497d805cc484fb90e8ee",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        List<Article> articles=new ArrayList<>();
                        try {
                            JSONArray articlesJsonArray=response.getJSONArray("articles");
                            for (int i = 0; i < articlesJsonArray.length(); i++) {
                                JSONObject articlesJSONObject=articlesJsonArray.getJSONObject(i);
                                Article article=new Article();
                                article.setAuthor(articlesJSONObject.getString("author"));
                                article.setTitle(articlesJSONObject.getString("title"));
                                article.setDescription(articlesJSONObject.getString("description"));
                                article.setUrl(articlesJSONObject.getString("url"));
                                article.setImageUrl(articlesJSONObject.getString("urlToImage"));
                                article.setDate(articlesJSONObject.getString("publishedAt"));
                                articles.add(article);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        resultCallback.onArticlesReceived(articles);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultCallback.onError("اختلال در دریافت اطلاعات");
            }
        });
        //send
        request.setRetryPolicy(new DefaultRetryPolicy(20000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueueContainer.getInstance(context).add(request);
    }

    public interface ResultCallback{
        void onArticlesReceived(List<Article> articles);
        void onError(String message);
    }

}
