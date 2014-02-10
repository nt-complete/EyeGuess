package com.ntcomplete.eyeguess.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
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
        ViewHolder holder;


        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.row_question_result, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        holder.resultTextView.setText(mQuestions.get(i));

        if(i < mResults.length) {
            boolean result = mResults[i];

            if(result) {
                holder.resultTextView.setBackgroundColor(mContext.getResources().getColor(android.R.color.holo_green_dark));
            } else {
                holder.resultTextView.setBackgroundColor(mContext.getResources().getColor(android.R.color.holo_red_dark));
            }
        } else {
            holder.resultTextView.setBackgroundColor(mContext.getResources().getColor(android.R.color.black));
        }



        return view;
    }

    public class ViewHolder {
        @InjectView(R.id.row_question_result_textview) TextView resultTextView;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
