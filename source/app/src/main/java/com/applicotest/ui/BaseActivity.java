package com.applicotest.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.applicotest.R;
import com.applicotest.util.BusProvider;


/**
 * Base Activity that implements the common functionality in the app's activities
 *
 * Created by Nirajan on 9/21/2015.
 */
public abstract class BaseActivity extends AppCompatActivity{

    private static final String TAG = "BaseActivity";

    /**
     * App's primary toolbar
     */
    private Toolbar mActionBarToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void setContentView(int layoutResId){
        super.setContentView(layoutResId);
        getActionBarToolbar();
    }

    /**
     * Retrieves the toolbar
     * @return Toolbar
     */
    protected Toolbar getActionBarToolbar() {
        if (mActionBarToolbar == null) {
            mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
            if (mActionBarToolbar != null) {
                setSupportActionBar(mActionBarToolbar);
            }
        }
        return mActionBarToolbar;
    }

    @Override
    protected void onResume(){
        super.onResume();
        // Register Bus provider
        BusProvider.getInstance().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Always unregister when an object no longer should be on the bus.
        BusProvider.getInstance().unregister(this);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        //trySetupSwipeRefresh();
    }

}
