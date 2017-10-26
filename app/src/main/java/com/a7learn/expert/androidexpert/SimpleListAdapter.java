package com.a7learn.expert.androidexpert;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 10/26/2017.
 */

public class SimpleListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ADS=3;
    private static final int EMAIL=1;
    private Context context;
    private List<Email> emails=new ArrayList<>();

    public SimpleListAdapter(Context context,List<Email> emails) {
        this.context = context;
        this.emails=emails;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        if (viewType==EMAIL) {
            View view = layoutInflater.inflate(R.layout.item_sample, parent, false);
            return new SimpleItemViewHolder(view);
        }else {
            View view=layoutInflater.inflate(R.layout.item_ads,parent,false);
            return new AdsViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position)==EMAIL) {
            ((SimpleItemViewHolder)holder).bindEmail(emails.get(position));
        }else {
            ((AdsViewHolder)holder).bindAdsBanner(emails.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return emails.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (emails.get(position).isAds()){
            return ADS;
        }else {
            return EMAIL;
        }
    }

    public void addEmail(Email email) {
        emails.add(email);
        notifyItemInserted(emails.size()-1);
    }

    public void remove(int position) {
        emails.remove(position);
        notifyItemRemoved(position);
    }

    public void changeItems(List<Email> emails) {
        this.emails = emails;
        notifyDataSetChanged();
    }

    public class SimpleItemViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView descriptionTextView;

        public SimpleItemViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.tv_itemSample_title);
            descriptionTextView = itemView.findViewById(R.id.tv_itemSample_description);
        }

        public void bindEmail(Email email) {
            titleTextView.setText(email.getTitle());
            descriptionTextView.setText(email.getDescription());
        }
    }

    public class AdsViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        public AdsViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView;
        }

        public void bindAdsBanner(Email email){
            imageView.setImageResource(email.getImageResourceId());
        }
    }
}
