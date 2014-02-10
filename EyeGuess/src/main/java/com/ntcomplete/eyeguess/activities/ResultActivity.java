package com.ntcomplete.eyeguess.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;
import com.ntcomplete.eyeguess.R;
import com.ntcomplete.eyeguess.adapters.QuestionResultsAdapter;

import java.util.ArrayList;

/**
 * @author nick
 */
public class ResultActivity extends Activity {

    private final String TAG = getClass().getSimpleName();

    public static final String EXTRA_SCORE = "QuizScore";
    public static final String EXTRA_PASSED = "QuizPassed";
    public static final String EXTRA_QUESTIONS = "QuizQuestions";
    public static final String EXTRA_RESULTS = "QuizResults";


    private GestureDetector mGestureDetector;

    private int mCategory;

    @InjectView(R.id.activity_result_questions) ListView mQuestionsListView;
    @InjectView(R.id.activity_result_instructions) FrameLayout mInstructionsView;
    @InjectView(R.id.activity_result_score) TextView mScoreTextView;
    @InjectView(R.id.activity_result_passed) TextView mPassedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.inject(this);


        Bundle extras = getIntent().getExtras();
        int score = extras.getInt(EXTRA_SCORE);
        int passed = extras.getInt(EXTRA_PASSED);

        mCategory = extras.getInt(QuizActivity.EXTRA_QUIZ_CATEGORY);

        ArrayList<String> questions = extras.getStringArrayList(EXTRA_QUESTIONS);
        Log.d(TAG, questions.toString());
        boolean[] resultsList = extras.getBooleanArray(EXTRA_RESULTS);



        mScoreTextView.setText(String.valueOf(score));
        mPassedTextView.setText(String.valueOf(passed));

        mQuestionsListView.setAdapter(new QuestionResultsAdapter(this, questions, resultsList));

        mInstructionsView.setVisibility(View.GONE);

        mGestureDetector = new GestureDetector(this);
        mGestureDetector.setBaseListener(mBaseListener);
    }

    @Override
    protected void onStart() {
        super.onStart();


//        final Timer timer = new Timer();
//
//        final TimerTask task = new TimerTask() {
//
//            int count = 0;
//            @Override
//            public void run() {
//                count++;
//                int lastVisible = mQuestionsListView.getLastVisiblePosition();
//
//                if(count % 30 == 0) {
//                    Log.d(TAG, "lastVisible: " + lastVisible + " count: " + mQuestionsListView.getCount());
//                }
//
////                last == listView.getCount() - 1 && listView.getChildAt(last).getBottom() <= listView.getHeight()
//                if(lastVisible < mQuestionsListView.getCount() - 1) {
////                        && mQuestionsListView.getChildAt(lastVisible).getBottom() > mQuestionsListView.getHeight()) {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            mQuestionsListView.scrollBy(0, 1);
//                        }
//                    });
//                }
//            }
//        };


//        timer.scheduleAtFixedRate(task, 2000, 25);

    }


    GestureDetector.BaseListener mBaseListener = new GestureDetector.BaseListener() {
        @Override
        public boolean onGesture(Gesture gesture) {
            if(gesture == Gesture.TAP) {
                if(mInstructionsView.getVisibility() == View.GONE) {
                    mInstructionsView.setVisibility(View.VISIBLE);
                } else {
                    mInstructionsView.setVisibility(View.GONE);
                }
                return true;
            }

            if(gesture == Gesture.TWO_TAP) {
                Intent countIntent = new Intent(ResultActivity.this, CountActivity.class);
                countIntent.putExtra(QuizActivity.EXTRA_QUIZ_CATEGORY, mCategory);
                startActivity(countIntent);
                finish();
            }

            if(gesture == Gesture.THREE_TAP) {
                Intent mainIntent = new Intent(ResultActivity.this, MainActivity.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(mainIntent);
                finish();

            }
            return false;
        };
    };


    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {

        if (mGestureDetector != null) {
            return mGestureDetector.onMotionEvent(event);
        }
        return false;
    }
}
