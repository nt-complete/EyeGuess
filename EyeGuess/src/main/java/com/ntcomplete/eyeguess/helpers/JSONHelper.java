package com.ntcomplete.eyeguess.helpers;

import android.content.Context;
import com.ntcomplete.eyeguess.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * @author nick
 */
public class JSONHelper {

    private JSONArray mCategoryArray;

    private String[] mCategories = {"movies", "actors"};
    private Random mRandom;

    public JSONHelper(Context context, int category) {
        InputStream inputStream = context.getResources().openRawResource(R.raw.eyeguessdata);

        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder total = new StringBuilder();
        String line;
        try {
            while ((line = r.readLine()) != null) {
                total.append(line);
            }

            JSONObject categoryObject = new JSONObject(total.toString());
            mCategoryArray = categoryObject.getJSONArray(mCategories[category]);

            mRandom = new Random();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getQuestion() {
        int question = mRandom.nextInt(mCategoryArray.length());

        try {
            return mCategoryArray.getString(question);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
