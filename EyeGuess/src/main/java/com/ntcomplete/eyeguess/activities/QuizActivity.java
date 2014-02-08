package com.ntcomplete.eyeguess.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;
import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;
import com.ntcomplete.eyeguess.R;
import com.ntcomplete.eyeguess.helpers.JSONHelper;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author nick
 */
public class QuizActivity extends Activity {

    public static final String EXTRA_QUIZ_CATEGORY = "QuizCategory";

    private GestureDetector mGestureDetector;

    private TextView mQuestionView;
    private TextView mScoreView;
    private TextView mTimerView;

    private View mResponseView;

    private int mScore = 0;

    private JSONHelper mJSONHelper;

    private AlphaAnimation mAlphaAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Keep the screen on, please.
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        int categoryId = getIntent().getExtras().getInt(EXTRA_QUIZ_CATEGORY);

        mQuestionView = (TextView) findViewById(R.id.activity_quiz_question);
        mScoreView = (TextView) findViewById(R.id.activity_quiz_score);
        mTimerView = (TextView) findViewById(R.id.activity_quiz_time_left);


        mScoreView.setText(String.valueOf(mScore));

        mResponseView = findViewById(R.id.activity_quiz_response_view);

        mJSONHelper = new JSONHelper(this, categoryId);

        mQuestionView.setText(mJSONHelper.getQuestion());

        mGestureDetector = new GestureDetector(this);
        mGestureDetector.setBaseListener(mBaseListener);

        mAlphaAnimation = new AlphaAnimation(1.f, 0.f);
        mAlphaAnimation.setDuration(750);

        TimerTask task = new TimerTask() {
            int milliseconds = 99;
            int seconds = 0;
            int minutes = 1;

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(seconds >= 0) {
                            mTimerView.setText(String.format("%02d:%02d:%02d", minutes, seconds, milliseconds));
                        }
                   }});
                milliseconds--;
                if(milliseconds <= 0) {
                    if(seconds == 0 && minutes == 0) {
                        cancel();
                    }
                    milliseconds = 99;
                    seconds--;
                    if(seconds <= 0) {
                        minutes--;
                        seconds = 59;
                    }
                }

            }
        };

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, 0, 10);

    }

    GestureDetector.BaseListener mBaseListener = new GestureDetector.BaseListener() {
        @Override
        public boolean onGesture(Gesture gesture) {
            if(gesture == Gesture.SWIPE_RIGHT || gesture == Gesture.TWO_SWIPE_RIGHT) {
                mScore++;
                mScoreView.setText(String.valueOf(mScore));

                mQuestionView.setText(mJSONHelper.getQuestion());

                mResponseView.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                mResponseView.clearAnimation();
                mResponseView.startAnimation(mAlphaAnimation);
                return true;
            }
            if(gesture == Gesture.SWIPE_LEFT || gesture == Gesture.TWO_SWIPE_LEFT) {
                mQuestionView.setText(mJSONHelper.getQuestion());

                mResponseView.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                mResponseView.clearAnimation();
                mResponseView.startAnimation(mAlphaAnimation);
                return true;
            }

            return false;
        }
    };

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {

        if (mGestureDetector != null) {
            return mGestureDetector.onMotionEvent(event);
        }
        return false;
    }
}
