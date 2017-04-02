package com.aleksandarvasilevski.hackernews;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String HACKERNEWS_REQUEST_URL = " https://newsapi.org/v1/articles?source=hacker-news&sortBy=top&apiKey=182f7eaaa40c487abe388ac68f15f648";

    private CustomAdapter mAdapter;
    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        task();

        final ListView newsListView = (ListView)findViewById(R.id.list);
        mAdapter = new CustomAdapter(this, new ArrayList<News>());
        newsListView.setAdapter(mAdapter);

        swipeRefresh = (SwipeRefreshLayout)findViewById(R.id.swiperefresh);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.setRefreshing(false);
                task();
            }
        });
    }

    private void task(){
        NewsAsyncTask task = new NewsAsyncTask();
        task.execute(HACKERNEWS_REQUEST_URL);
    }

    private class NewsAsyncTask extends AsyncTask<String, Void, List<News>> {

        private ProgressDialog progressDialog;

        @Override
        protected List<News> doInBackground(String... strings) {
            List<News> result = QueryUtils.fetchNewsJson(HACKERNEWS_REQUEST_URL);
            return result;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(List<News> data) {
            mAdapter.clear();
            if (progressDialog.isShowing()){
                progressDialog.dismiss();
            }

            if (data != null && !data.isEmpty()){
                mAdapter.addAll(data);
            }
        }
    }
}
