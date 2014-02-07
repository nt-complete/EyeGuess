package com.ntcomplete.eyeguess.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
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

        final TextView textView = (TextView) findViewById(R.id.activity_count_textview);

//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        new CountDownTimer(4000, 1000) {
            int count = 3;

            public void onTick(long millisUntilFinished) {
                Log.d(TAG, "secs left: " + millisUntilFinished + " count: " + count);
                textView.setText(String.valueOf(count));
                count--;

            }

            public void onFinish() {

                Toast.makeText(CountActivity.this, "Finished!", Toast.LENGTH_SHORT).show();
            }
        }.start();


    }
}
