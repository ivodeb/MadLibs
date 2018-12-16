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

    private int menu_buttons[] = {R.id.simple, R.id.tarzan, R.id.university, R.id.clothes,
            R.id.dance};

    private int raw_files[] = {R.raw.madlib0_simple, R.raw.madlib1_tarzan,
                                R.raw.madlib2_university, R.raw.madlib3_clothes,
                                R.raw.madlib4_dance};

    private String stories[] = {"madlib0_simple", "madlib1_tarzan", "madlib2_university",
                                "madlib3_clothes", "madlib4_dance"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        for (int i = 0; i < raw_files.length; i++) {
            Button menu_button = findViewById(menu_buttons[i]);
            menu_button.setText(stories[i].substring(8));
            menu_button.setOnClickListener(new OnStoryClickListener());
        }
    }

    private class OnStoryClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            int story_id = 0;
            int button_id = view.getId();

            for (int i = 0; i < menu_buttons.length; i++) {
                if (button_id == menu_buttons[i]) {
                    story_id = raw_files[i];
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
