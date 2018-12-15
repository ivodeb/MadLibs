package com.example.ivode.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.design.widget.TextInputEditText;


public class WordActivity extends AppCompatActivity {

    private Story retrievedStory;
    private int placeholderRemainingCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        Intent intent = getIntent();
        retrievedStory = (Story) intent.getSerializableExtra("story");

        placeholderRemainingCount = retrievedStory.getPlaceholderRemainingCount();
        TextView amountFilled = findViewById(R.id.filledBox);
        amountFilled.setText("Placeholders left: " + placeholderRemainingCount);

        String placeholder = retrievedStory.getNextPlaceholder();
        if (!placeholder.equals("")) {
            TextInputEditText textInput = findViewById(R.id.textInput);
            textInput.setHint(placeholder);
        }

        Button continue_button = findViewById(R.id.fillButton);
        continue_button.setOnClickListener(new OnButtonClickListener());
    }

    private class OnButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            TextInputEditText textInput = findViewById(R.id.textInput);
            Editable filledInPlaceholderEditable = textInput.getText();
            if (filledInPlaceholderEditable != null) {
                String filledInPlaceholder = filledInPlaceholderEditable.toString();

                if (filledInPlaceholder.length() != 0) {
                    retrievedStory.fillInPlaceholder(filledInPlaceholder);
                    String placeholder = retrievedStory.getNextPlaceholder();

                    if (!placeholder.equals("")) {
                        textInput.setHint(placeholder);
                        placeholderRemainingCount = retrievedStory.getPlaceholderRemainingCount();
                        TextView amountFilled = findViewById(R.id.filledBox);
                        amountFilled.setText("Placeholders left: " + placeholderRemainingCount);
                        textInput.setText("");
                    }
                    else {
                        Intent intent = new Intent(WordActivity.this, StoryActivity.class);
                        intent.putExtra("story", retrievedStory);
                        startActivity(intent);
                    }

                }
            }
        }
    }
}
