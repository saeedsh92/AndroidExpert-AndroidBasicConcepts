package com.a7learn.expert.androidexpert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewSample extends AppCompatActivity {
    private List<Email> emails=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_sample);
        emails=generateEmails();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.rv_recyclerViewSample);
        final SimpleListAdapter adapter=new SimpleListAdapter(this,emails);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
//        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        Button addButton= (Button) findViewById(R.id.btn_recyclerViewSample_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email email=new Email();
                email.setTitle("New Email");
                email.setDescription("New Email Description");
                adapter.addEmail(email);
            }
        });
        Button removeButton= (Button) findViewById(R.id.btn_recyclerViewSample_remove);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.remove(emails.size()-1);
            }
        });
        Button notifyAllButton= (Button) findViewById(R.id.btn_recyclerViewSample_notifyAll);
        notifyAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.changeItems(generateNewEmails());
            }
        });
    }

    private List<Email> generateEmails(){
        List<Email> emails=new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Email email=new Email();
            email.setTitle("Title "+i);
            email.setDescription("Description "+i);
            emails.add(email);
        }
        //Add Ads
        Email ads1=new Email();
        ads1.setAds(true);
        ads1.setImageResourceId(R.drawable.ads_banner_1);
        emails.add(4,ads1);

        Email ads2=new Email();
        ads2.setAds(true);
        ads2.setImageResourceId(R.drawable.ads_banner_2);
        emails.add(9,ads2);

        Email ads3=new Email();
        ads3.setAds(true);
        ads3.setImageResourceId(R.drawable.ads_banner_3);
        emails.add(14,ads3);
        return emails;
    }

    private List<Email> generateNewEmails(){
        List<Email> emails=new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Email email=new Email();
            email.setTitle("New Title "+i);
            email.setDescription("New Description "+i);
            emails.add(email);
        }
        return emails;
    }
}
