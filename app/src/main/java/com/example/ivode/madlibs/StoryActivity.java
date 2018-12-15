/*
 *  Mad Libs app by Ivo de Brouwer 11045841
 *  Extra: Filled-in words bold in the final text
 */

package com.example.ivode.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

public class StoryActivity extends AppCompatActivity {

    private Story story;

    /** create the story */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        Intent intent = getIntent();
        story = (Story) intent.getSerializableExtra("story");

        TextView story_text = findViewById(R.id.story);
        story_text.setText(Html.fromHtml(story.toString(),0));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(StoryActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    public void onReplayClicked(View view) {
        Intent intent = new Intent(StoryActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}
