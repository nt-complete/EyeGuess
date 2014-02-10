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
import java.util.ArrayList;
import java.util.Random;

/**
 * @author nick
 */
public class JSONHelper {

    private static JSONHelper mHelperInstance = null;

    private JSONArray mCategoryArray;

    private Random mRandom;

    private JSONObject mCategoryObject;

    private JSONArray mCategories;

    public static JSONHelper getInstance(Context context) {
        if(mHelperInstance == null) {
            mHelperInstance = new JSONHelper(context);
        }

        return mHelperInstance;
    }

    private JSONHelper(Context context) {
        InputStream inputStream = context.getResources().openRawResource(R.raw.eyeguessdata);

        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder total = new StringBuilder();
        String line;
        try {
            while ((line = r.readLine()) != null) {
                total.append(line);
            }

            JSONObject object = new JSONObject(total.toString());
            mCategories = object.getJSONArray("categories");



        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setCategory(String categoryStr) {

        mRandom = new Random();

        for(int i = 0; i < mCategories.length(); i++) {
            JSONObject category = null;
            try {
                category = mCategories.getJSONObject(i);
                if(category.getString("title").equalsIgnoreCase(categoryStr)) {
                    mCategoryArray = category.getJSONArray("data");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    public ArrayList<String> getKeys() {
        ArrayList<String> keyList = new ArrayList<String>();
        for(int i = 0; i < mCategories.length(); i++) {
            try {
                keyList.add(mCategories.getJSONObject(i).getString("title"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return keyList;
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
