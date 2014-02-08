package com.ntcomplete.eyeguess.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import com.ntcomplete.eyeguess.R;
import com.ntcomplete.eyeguess.adapters.QuestionResultsAdapter;

import java.util.ArrayList;

/**
 * @author nick
 */
public class ResultActivity extends Activity {

    public static final String EXTRA_SCORE = "QuizScore";
    public static final String EXTRA_PASSED = "QuizPassed";
    public static final String EXTRA_QUESTIONS = "QuizQuestions";
    public static final String EXTRA_RESULTS = "QuizResults";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle extras = getIntent().getExtras();
        int score = extras.getInt(EXTRA_SCORE);
        int passed = extras.getInt(EXTRA_PASSED);
        ArrayList<String> questions = extras.getStringArrayList(EXTRA_QUESTIONS);
        boolean[] resultsList = extras.getBooleanArray(EXTRA_RESULTS);


        TextView scoreTextView = (TextView) findViewById(R.id.activity_result_score);
        TextView passedTextView = (TextView) findViewById(R.id.activity_result_passed);
        scoreTextView.setText(String.valueOf(score));
        passedTextView.setText(String.valueOf(passed));

        ListView questionsList = (ListView) findViewById(R.id.activity_result_questions);
        questionsList.setAdapter(new QuestionResultsAdapter(this, questions, resultsList));

    }
}
