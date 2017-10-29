package com.a7learn.expert.androidexpert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityForResultSample extends AppCompatActivity {
    public static final String RESULT_KEY="result";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_result_sample);
        final EditText fullNameEditText=(EditText)findViewById(R.id.editText_result_fullName);
        Button saveButton= (Button) findViewById(R.id.btn_result_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra(RESULT_KEY,fullNameEditText.getText().toString());
                setResult(RESULT_OK,intent);

                finish();
            }
        });
    }
}
