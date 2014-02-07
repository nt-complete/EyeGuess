package com.ntcomplete.eyeguess.adapters;

import android.view.View;
import android.view.ViewGroup;
import com.google.android.glass.app.Card;
import com.google.android.glass.widget.CardScrollAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nick
 */
public class CustomCardScrollAdapter extends CardScrollAdapter {

    private List<Card> mCards;

    public CustomCardScrollAdapter() {
        mCards = new ArrayList<Card>();
    }

    @Override
    public int findIdPosition(Object id) {
        return -1;
    }

    public void addCard(Card card) {
        mCards.add(card);
    }

    @Override
    public int findItemPosition(Object item) {
        return mCards.indexOf(item);
    }

    @Override
    public int getCount() {
        return mCards.size();
    }

    @Override
    public Object getItem(int position) {
        return mCards.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return mCards.get(position).toView();
    }
}
