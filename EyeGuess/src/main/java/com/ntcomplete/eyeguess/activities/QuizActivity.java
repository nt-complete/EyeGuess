package com.ntcomplete.eyeguess.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;
import com.ntcomplete.eyeguess.R;
import com.ntcomplete.eyeguess.helpers.JSONHelper;

/**
 * @author nick
 */
public class QuizActivity extends Activity {

    public static final String EXTRA_QUIZ_CATEGORY = "QuizCategory";

    private GestureDetector mGestureDetector;

    private TextView mQuestionView;
    private TextView mScoreView;

    private int mScore = 0;

    private JSONHelper mJSONHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        int categoryId = getIntent().getExtras().getInt(EXTRA_QUIZ_CATEGORY);

        mQuestionView = (TextView) findViewById(R.id.activity_quiz_question);
        mScoreView = (TextView) findViewById(R.id.activity_quiz_score);
        mScoreView.setText(String.valueOf(mScore));

        mJSONHelper = new JSONHelper(this, categoryId);

        mQuestionView.setText(mJSONHelper.getQuestion());

        mGestureDetector = new GestureDetector(this);
        mGestureDetector.setBaseListener(mBaseListener);
    }

    GestureDetector.BaseListener mBaseListener = new GestureDetector.BaseListener() {
        @Override
        public boolean onGesture(Gesture gesture) {
            if(gesture == Gesture.TAP) {
                Toast.makeText(QuizActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                mScore++;
                mScoreView.setText(String.valueOf(mScore));

                mQuestionView.setText(mJSONHelper.getQuestion());
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
