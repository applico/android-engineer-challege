package com.applicotest.util;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;


import com.applicotest.model.Data;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Nirajan on 9/22/2015.
 */
public class HttpManager extends AsyncTask<Object, Void, Void> implements Callback {

    private final OkHttpClient client = new OkHttpClient();

    private static final String TAG = "HttpManager";

    public static final String API_BASE_URL = "https://api.github.com/repos/rails/rails/commits";

    Activity mActivity;

    boolean flag = false;
    private List<Data> list;

    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p/>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param params The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected Void doInBackground(Object... params) {
        this.mActivity = (Activity) params[0];
        final Request request = new Request.Builder().url(API_BASE_URL).build();
        client.newCall(request).enqueue(this);
        return null;
    }


    /**
     * Called when the request could not be executed due to cancellation, a
     * connectivity problem or timeout. Because networks can fail during an
     * exchange, it is possible that the remote server accepted the request
     * before the failure.
     *
     * @param request
     * @param e
     */
    @Override
    public void onFailure(Request request, IOException e) {
        if (mActivity != null && !mActivity.isFinishing()) {
            Log.d(TAG, "Send Failure event");
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    BusProvider.getInstance().post(new ErrorEvent(Util.ERROR_MSG));
                }
            });
        }
    }

    /**
     * Called when the HTTP response was successfully returned by the remote
     * server. The callback may proceed to read the response body with {@link
     * Response#body}. The response is still live until its response body is
     * closed with {@code response.body().close()}. The recipient of the callback
     * may even consume the response body on another thread.
     * <p/>
     * <p>Note that transport-layer success (receiving a HTTP response code,
     * headers and body) does not necessarily indicate application-layer
     * success: {@code response} may still indicate an unhappy HTTP response
     * code like 404 or 500.
     *
     * @param response
     */
    @Override
    public void onResponse(Response response) throws IOException {
        if (!response.isSuccessful()) throw new IOException("IOException: " + response);
        String result = response.body().string();
        ObjectMapper mapper = new ObjectMapper();
        list = mapper.readValue(result,
                new TypeReference<ArrayList<Data>>() {
                });

        for (Data d : list) {
            Log.d(TAG, d.getCommit().getMessage());
        }
        if (mActivity != null && !mActivity.isFinishing()) {
            Log.d(TAG, "Send Success event");
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    BusProvider.getInstance().post(new ResultEvent(list));
                }
            });

        }

    }

    /**
     * Events for Otto
     */
    public class ResultEvent {
        public List<Data> dataList;

        public ResultEvent(List<Data> dataList) {
            this.dataList = dataList;
        }
    }

    public class ErrorEvent {
        public String message;

        public ErrorEvent(String message) {
            this.message = message;
        }
    }
}
