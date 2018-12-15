/*
 *  Mad Libs app by Ivo de Brouwer 11045841
 *  Extra: Filled-in words bold in the final text
 */

package com.example.ivode.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.InputStream;

public class MenuActivity extends AppCompatActivity {

    // initialize arrays for story variables

    private int files[] = {R.raw.madlib0_simple, R.raw.madlib1_tarzan,
                                R.raw.madlib2_university, R.raw.madlib3_clothes,
                                R.raw.madlib4_dance};

    private String stories[] = {"madlib0_simple", "madlib1_tarzan", "madlib2_university",
                                "madlib3_clothes", "madlib4_dance"};

    private int buttons[] = {R.id.simple, R.id.tarzan, R.id.university, R.id.clothes,
                                R.id.dance};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        for (int i = 0; i < files.length; i++) {
            Button button = findViewById(buttons[i]);
            button.setText(stories[i].substring(8));
            button.setOnClickListener(new OnStoryClickListener());
        }
    }

    private class OnStoryClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            int story_id = 0;
            int button_id = view.getId();

            for (int i = 0; i < buttons.length; i++) {
                if (button_id == buttons[i]) {
                    story_id = files[i];
                }
            }

            InputStream input_file = getResources().openRawResource(story_id);
            Story story = new Story(input_file);
            Intent intent = new Intent(MenuActivity.this, WordActivity.class);
            intent.putExtra("story", story);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
