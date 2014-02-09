package com.ntcomplete.eyeguess.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import com.ntcomplete.eyeguess.R;
import com.ntcomplete.eyeguess.adapters.QuestionResultsAdapter;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author nick
 */
public class ResultActivity extends Activity {

    private final String TAG = getClass().getSimpleName();

    public static final String EXTRA_SCORE = "QuizScore";
    public static final String EXTRA_PASSED = "QuizPassed";
    public static final String EXTRA_QUESTIONS = "QuizQuestions";
    public static final String EXTRA_RESULTS = "QuizResults";

    private ListView mQuestionsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle extras = getIntent().getExtras();
        int score = extras.getInt(EXTRA_SCORE);
        int passed = extras.getInt(EXTRA_PASSED);
        ArrayList<String> questions = extras.getStringArrayList(EXTRA_QUESTIONS);
        Log.d(TAG, questions.toString());
        boolean[] resultsList = extras.getBooleanArray(EXTRA_RESULTS);


        TextView scoreTextView = (TextView) findViewById(R.id.activity_result_score);
        TextView passedTextView = (TextView) findViewById(R.id.activity_result_passed);
        scoreTextView.setText(String.valueOf(score));
        passedTextView.setText(String.valueOf(passed));

        mQuestionsListView = (ListView) findViewById(R.id.activity_result_questions);
        mQuestionsListView.setAdapter(new QuestionResultsAdapter(this, questions, resultsList));

    }

    @Override
    protected void onStart() {
        super.onStart();


        final Timer timer = new Timer();

        final TimerTask task = new TimerTask() {

            int count = 0;
            @Override
            public void run() {
                count++;
                int lastVisible = mQuestionsListView.getLastVisiblePosition();

                if(count % 30 == 0) {
                    Log.d(TAG, "lastVisible: " + lastVisible + " count: " + mQuestionsListView.getCount());
                }

//                last == listView.getCount() - 1 && listView.getChildAt(last).getBottom() <= listView.getHeight()
                if(lastVisible < mQuestionsListView.getCount() - 1) {
//                        && mQuestionsListView.getChildAt(lastVisible).getBottom() > mQuestionsListView.getHeight()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mQuestionsListView.scrollBy(0, 1);
                        }
                    });
                }
            }
        };


//        timer.scheduleAtFixedRate(task, 2000, 25);

    }
}
