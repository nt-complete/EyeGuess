package com.ntcomplete.eyeguess.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ntcomplete.eyeguess.R;

import java.util.ArrayList;

/**
 * @author nick
 */
public class QuestionResultsAdapter extends BaseAdapter {

    private final String TAG = getClass().getSimpleName();

    private ArrayList<String> mQuestions;
    private boolean[] mResults;
    private Context mContext;

    public QuestionResultsAdapter(Context context, ArrayList<String> questions, boolean[] results) {
        mContext = context;
        mQuestions = questions;
        mResults = results;
    }

    @Override
    public int getCount() {
        Log.d(TAG, "Count: " + mQuestions.size());
        return mQuestions.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView resultTextView = (TextView) LayoutInflater.from(mContext).inflate(R.layout.row_question_result, viewGroup, false);
        resultTextView.setText(mQuestions.get(i));

        if(i < mResults.length) {
            boolean result = mResults[i];

            if(result) {
                resultTextView.setBackgroundColor(mContext.getResources().getColor(android.R.color.holo_green_dark));
            } else {
                resultTextView.setBackgroundColor(mContext.getResources().getColor(android.R.color.holo_red_dark));
            }
        } else {
            resultTextView.setBackgroundColor(mContext.getResources().getColor(android.R.color.black));
        }



        return resultTextView;
    }

}
