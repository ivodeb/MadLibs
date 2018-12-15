package com.example.ivode.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class StoryActivity extends AppCompatActivity {

    private Story retrievedStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        Intent intent = getIntent();
        retrievedStory = (Story) intent.getSerializableExtra("story");

        TextView textViewStory = findViewById(R.id.textViewStory);
        textViewStory.setText(retrievedStory.toString());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(StoryActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
