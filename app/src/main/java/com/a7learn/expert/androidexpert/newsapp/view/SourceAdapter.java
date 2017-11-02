package com.a7learn.expert.androidexpert.newsapp.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a7learn.expert.androidexpert.R;
import com.a7learn.expert.androidexpert.newsapp.model.Source;

import java.util.List;

/**
 * Created by user on 11/2/2017.
 */

public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.SourceViewHolder> {
    private Context context;
    private List<Source> sources;
    private SourceViewCallback sourceViewCallback;
    public SourceAdapter(Context context, List<Source> sources,SourceViewCallback sourceViewCallback){
        this.context=context;
        this.sources=sources;
        this.sourceViewCallback=sourceViewCallback;
    }
    @Override
    public SourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View sourceView= LayoutInflater.from(context).inflate(R.layout.item_source,parent,false);
        return new SourceViewHolder(sourceView);
    }

    @Override
    public void onBindViewHolder(SourceViewHolder holder, int position) {
        holder.bindSource(sources.get(position));
    }

    @Override
    public int getItemCount() {
        return sources.size();
    }

    public class SourceViewHolder extends RecyclerView.ViewHolder{
        private TextView sourceTitleTextView;
        public SourceViewHolder(View itemView) {
            super(itemView);
            sourceTitleTextView= (TextView) itemView;
        }
        public void bindSource(final Source source){
            sourceTitleTextView.setText(source.getTitle());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sourceViewCallback.onSourceItemClick(source.getId());
                }
            });
        }
    }

    public interface SourceViewCallback{
        void onSourceItemClick(String sourceId);
    }
}
