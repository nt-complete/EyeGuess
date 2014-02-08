package com.ntcomplete.eyeguess.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        int categoryId = getIntent().getExtras().getInt(EXTRA_QUIZ_CATEGORY);

        TextView questionView = (TextView) findViewById(R.id.activity_quiz_question);

        JSONHelper jsonHelper = new JSONHelper(this, categoryId);

        questionView.setText(jsonHelper.getQuestion());

        mGestureDetector = new GestureDetector(this);
        mGestureDetector.setBaseListener(mBaseListener);
    }

    GestureDetector.BaseListener mBaseListener = new GestureDetector.BaseListener() {
        @Override
        public boolean onGesture(Gesture gesture) {

            
            return false;
        }
    };
}
