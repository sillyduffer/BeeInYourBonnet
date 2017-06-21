package com.example.android.beeinyourbonnet;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.List;

public class BookLoader extends AsyncTaskLoader<List<Book>> {
    /**
     * Tag for log messages
     */
    private static final String LOG_TAG = BookLoader.class.getName();

    private final String mUrl;

    public BookLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "TEST: onStartLoading() called ");
        forceLoad();
    }

    @Override
    public List<Book> loadInBackground() {
        Log.i(LOG_TAG, "TEST: loadInBackground() called ");
        if (mUrl == null) {
            return null;
        }

        return Utils.fetchBookData(mUrl);
    }
}
