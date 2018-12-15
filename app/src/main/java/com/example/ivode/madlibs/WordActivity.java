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
import android.widget.TextView;
import android.support.design.widget.TextInputEditText;


public class WordActivity extends AppCompatActivity {

    private Story story;
    private int words_left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        Intent intent = getIntent();
        story = (Story) intent.getSerializableExtra("story");

        words_left = story.getPlaceholderRemainingCount();
        TextView remaining_text = findViewById(R.id.placeholders);
        remaining_text.setText(words_left + " word(s) left");

        String placeholder = story.getNextPlaceholder();
        TextInputEditText input_word = findViewById(R.id.input_word);
        input_word.setHint(placeholder);

        Button continue_button = findViewById(R.id.continue_button);
        continue_button.setOnClickListener(new OnButtonClickListener());
    }

    /** fills in the words and calls the story when no more words are needed */
    private class OnButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            TextInputEditText input_word = findViewById(R.id.input_word);
            if (input_word.getText() != null) {
                String words_done = input_word.getText().toString();
                story.fillInPlaceholder(words_done);
                String placeholder = story.getNextPlaceholder();

                if (!placeholder.equals("")) {
                    input_word.setHint(placeholder);
                    words_left = story.getPlaceholderRemainingCount();
                    TextView words_remaining_text = findViewById(R.id.placeholders);
                    words_remaining_text.setText(words_left + " word(s) left");
                    input_word.setText("");
                }
                else {
                    Intent intent = new Intent(WordActivity.this, StoryActivity.class);
                    intent.putExtra("story", story);
                    startActivity(intent);
                }
            }
        }
    }
}
