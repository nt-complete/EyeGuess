package com.ntcomplete.eyeguess.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.ntcomplete.eyeguess.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author nick
 */
public class CountActivity extends Activity {

    @InjectView(R.id.activity_count_textview) TextView mCountTextView;

    private final String TAG = getClass().getSimpleName();

    private String mQuizCategory;

    private Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_count);

        mQuizCategory = getIntent().getExtras().getString(QuizActivity.EXTRA_QUIZ_CATEGORY);

        ButterKnife.inject(this);


    }

    @Override
    protected void onStart() {
        super.onStart();


        mTimer = new Timer();

        final TimerTask task = new TimerTask() {
            int count = 3;

            @Override
            public void run() {
                if(count == 1) {
                    mTimer.cancel();
                    startQuiz();
                } else {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mCountTextView.setText(String.valueOf(count));
                        }
                    });
                    count--;
                }


            }
        };

        mTimer.scheduleAtFixedRate(task, 500, 1000);

    }

    @Override
    protected void onStop() {
        super.onStop();
        mTimer.cancel();
        mTimer.purge();
    }

    private void startQuiz() {

        Intent quizIntent = new Intent(CountActivity.this, QuizActivity.class);
        quizIntent.putExtra(QuizActivity.EXTRA_QUIZ_CATEGORY, mQuizCategory);
        startActivity(quizIntent);
        finish();
    }
}
