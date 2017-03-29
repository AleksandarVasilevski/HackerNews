package com.aleksandarvasilevski.hackernews;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<News> {

    public CustomAdapter(Context context, List<News> newsList) {
        super(context, 0, newsList);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.hacker_news_list_item, parent, false);

        }

        News currentNews = getItem(position);

        TextView titleTextView = (TextView)listItemView.findViewById(R.id.titleTextView);
        titleTextView.setText(currentNews.getTitle());

        TextView descriptionTextView = (TextView)listItemView.findViewById(R.id.descriptionTextView);
        descriptionTextView.setText(currentNews.getDescription());

        return listItemView;
    }
}
