package com.a7learn.expert.androidexpert;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ViewsSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_views_sample);
        Intent intent = getIntent();
        String firstName = intent.getStringExtra("first_name");
        String lastName = intent.getStringExtra("last_name");
        Button viewWebsiteButton=(Button)findViewById(R.id.button_viewsSample_viewWebsite);
        viewWebsiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/saeedsh92"));
                startActivity(Intent.createChooser(intent,"Please Select Browser"));
            }
        });

        final EditText firstNameEditText = (EditText) findViewById(R.id.editText_viewsSample_firstName);
        final EditText lastNameEditText = (EditText) findViewById(R.id.editText_viewsSample_lastName);
        final TextView fullNameTextView = (TextView) findViewById(R.id.tv_viewsSample_fullName);
        fullNameTextView.setText(firstName + " "+lastName );
        final Button editProfileButton = (Button) findViewById(R.id.hint);
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstNameEditText.getVisibility() == View.GONE) {
                    firstNameEditText.setVisibility(View.VISIBLE);
                    lastNameEditText.setVisibility(View.VISIBLE);
                    editProfileButton.setText(getString(R.string.views_saveName));
                } else {
                    firstNameEditText.setVisibility(View.GONE);
                    lastNameEditText.setVisibility(View.GONE);
                    String firstName = firstNameEditText.getText().toString();
                    String lastName = lastNameEditText.getText().toString();
                    editProfileButton.setText(getString(R.string.views_editName));
                    fullNameTextView.setText(firstName + " " + lastName);
                }
            }
        });

    }

}
