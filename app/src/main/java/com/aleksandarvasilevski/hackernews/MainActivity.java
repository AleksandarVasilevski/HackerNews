package com.aleksandarvasilevski.hackernews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String HACKERNEWS_REQUEST_URL = "https://newsapi.org/v1/articles?source=hacker-news&sortBy=latest&apiKey={API_KEY}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<News> newsList = QueryUtils.extractNewsList();

        ListView newsListView = (ListView)findViewById(R.id.list);
        CustomAdapter adapter = new CustomAdapter(this, newsList);
        newsListView.setAdapter(adapter);
    }
}
