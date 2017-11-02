package com.a7learn.expert.androidexpert;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.a7learn.expert.androidexpert.newsapp.view.NewsListActivity;
import com.a7learn.expert.androidexpert.newsapp.model.Article;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int REQUEST_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int x = 100;

        x += 200;

        Button viewsSampleButton = (Button) findViewById(R.id.button_Views);
        viewsSampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewsSampleActivity.class);
                intent.putExtra("first_name", "Keivan");
                intent.putExtra("last_name", "AliMohammadi");
                startActivity(intent);
            }
        });

        Button linearLayoutSample = (Button) findViewById(R.id.button_linearLayout);
        linearLayoutSample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LinearLayoutSampleActivity.class);
                startActivity(intent);
            }
        });

        Button relativeLayoutSample = (Button) findViewById(R.id.button_RelativeLayout);
        relativeLayoutSample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RelativeLayoutSampleActivity.class);
                startActivity(intent);
            }
        });
        Button frameLayoutSample = (Button) findViewById(R.id.button_FrameLayout);
        frameLayoutSample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FrameLayoutSampleActivity.class);
                startActivity(intent);
            }
        });
        Button constraintLayoutSample = (Button) findViewById(R.id.button_ConstraintLayout);
        constraintLayoutSample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button btnStartActivityForResult = (Button) findViewById(R.id.button_startActivityForResult);
        btnStartActivityForResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityForResultSample.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        JSONArray students = new JSONArray();
        JSONObject student = new JSONObject();
        try {
            student.put("first_name", "sdsd");
            student.put("last_name", "asdasdasd");
            student.put("age", 24);

            JSONArray skills = new JSONArray();
            skills.put("HTML");
            skills.put("CSS");
            student.put("skills", skills);

            students.put(student);

            JSONObject certificate = new JSONObject();
            certificate.put("name", "dasdas");
            certificate.put("university", "sdasd");
            
        } catch (JSONException e) {
            e.printStackTrace();
        }

        NewsApiService newsApiService=new NewsApiService(this);
        newsApiService.getArticles("techcrunch", new NewsApiService.ResultCallback() {
            @Override
            public void onArticlesReceived(List<Article> articles) {
                if (articles.size() > 0) {
                    Toast.makeText(MainActivity.this, "دریافت شد", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });

        Button newsAppButton=(Button)findViewById(R.id.button_newsApp);
        newsAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NewsListActivity.class));
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE &&
                resultCode == RESULT_OK &&
                data != null) {
            String fullName = data.getStringExtra(ActivityForResultSample.RESULT_KEY);
            Toast.makeText(MainActivity.this, fullName, Toast.LENGTH_LONG).show();
        }
    }
}
