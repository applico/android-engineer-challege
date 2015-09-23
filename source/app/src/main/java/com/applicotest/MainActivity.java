package com.applicotest;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.applicotest.model.Data;
import com.applicotest.ui.BaseActivity;
import com.applicotest.ui.MyAdapter;
import com.applicotest.util.HttpManager;
import com.applicotest.util.Util;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    private LinearLayout progressBarLayout;
    private List<Data> myData = new ArrayList<Data>();
    private ListView listView;
    private MyAdapter myAdapter;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.primary));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                makeApiRequest();
            }
        });
        progressBarLayout = (LinearLayout) findViewById(R.id.progressbar_layout);
        listView = (ListView) findViewById(R.id.list);

        makeApiRequest();
    }

    private void makeApiRequest() {
        if (Util.isNetworkAvailable(this)) {
            progressBarLayout.setVisibility(View.VISIBLE);
            new HttpManager().execute(this);
        } else {
            if (swipeRefreshLayout.isRefreshing()) {
                swipeRefreshLayout.setRefreshing(false);
            }
            Toast.makeText(MainActivity.this, Util.ERROR_MSG, Toast.LENGTH_LONG ).show();
        }
    }

    @Subscribe
    public void getDataApiSuccess(HttpManager.ResultEvent event) {
        Log.d(TAG, "Success");
        myData = event.dataList;
        myAdapter = new MyAdapter(this, myData);
        listView.setAdapter(myAdapter);
        for(Data a : myData) {
            Log.d(TAG, a.getCommit().getAuthor().getName());
        }

        progressBarLayout.setVisibility(View.GONE);
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }

    }

    @Subscribe
    public void getDataApiFailure(HttpManager.ErrorEvent event) {
        Log.d(TAG, "Failure");
        progressBarLayout.setVisibility(View.GONE);
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        Toast.makeText(MainActivity.this, event.message, Toast.LENGTH_LONG ).show();
    }


}
