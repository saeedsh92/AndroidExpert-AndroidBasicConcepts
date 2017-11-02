package com.a7learn.expert.androidexpert.newsapp.view;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a7learn.expert.androidexpert.R;
import com.a7learn.expert.androidexpert.newsapp.model.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/2/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private Context context;
    private List<Article> articles=new ArrayList<>();
    public NewsAdapter(Context context,List<Article> articles){
        this.context=context;
        this.articles=articles;
    }
    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View newsView= LayoutInflater.from(context).inflate(R.layout.item_news,parent,false);
        return new NewsViewHolder(newsView);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.bindNews(articles.get(position));
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView titleTextView;
        private TextView descriptionTextView;
        private TextView authorTextView;
        private TextView dateTextView;
        public NewsViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.iv_itemNews);
            titleTextView=(TextView)itemView.findViewById(R.id.tv_itemNews_title);
            descriptionTextView=(TextView)itemView.findViewById(R.id.tv_itemNews_description);
            authorTextView=(TextView)itemView.findViewById(R.id.tv_itemNews_author);
            dateTextView=(TextView)itemView.findViewById(R.id.tv_itemNews_date);
        }
        public void bindNews(Article article){
            Picasso.with(context).load(Uri.parse(article.getImageUrl()))
                    .into(imageView);
            titleTextView.setText(article.getTitle());
            authorTextView.setText(article.getAuthor());
            descriptionTextView.setText(article.getDescription());
            dateTextView.setText(article.getDate());
        }
    }
}
