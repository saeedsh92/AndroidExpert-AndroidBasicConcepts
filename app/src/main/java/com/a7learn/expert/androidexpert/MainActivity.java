package com.a7learn.expert.androidexpert;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int x=100;

        x+=200;

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
                Intent intent=new Intent(MainActivity.this,LinearLayoutSampleActivity.class);
                startActivity(intent);
            }
        });

        Button relativeLayoutSample = (Button) findViewById(R.id.button_RelativeLayout);
        relativeLayoutSample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RelativeLayoutSampleActivity.class);
                startActivity(intent);
            }
        });
        Button frameLayoutSample = (Button) findViewById(R.id.button_FrameLayout);
        frameLayoutSample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,FrameLayoutSampleActivity.class);
                startActivity(intent);
            }
        });
        Button constraintLayoutSample = (Button) findViewById(R.id.button_ConstraintLayout);
        constraintLayoutSample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

}
