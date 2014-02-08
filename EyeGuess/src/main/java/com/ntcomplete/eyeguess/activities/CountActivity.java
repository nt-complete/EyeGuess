package com.ntcomplete.eyeguess.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;
import com.ntcomplete.eyeguess.R;

/**
 * @author nick
 */
public class CountActivity extends Activity {

    private final String TAG = getClass().getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_count);

        final int quizId = getIntent().getExtras().getInt(QuizActivity.EXTRA_QUIZ_CATEGORY);

        final TextView textView = (TextView) findViewById(R.id.activity_count_textview);

        new CountDownTimer(4000, 1000) {
            int count = 3;

            public void onTick(long millisUntilFinished) {
                Log.d(TAG, "secs left: " + millisUntilFinished + " count: " + count);
                textView.setText(String.valueOf(count));
                count--;

            }

            public void onFinish() {
                Intent quizIntent = new Intent(CountActivity.this, QuizActivity.class);
                quizIntent.putExtra(QuizActivity.EXTRA_QUIZ_CATEGORY, quizId);
                startActivity(quizIntent);
                finish();
            }
        }.start();


    }
}
