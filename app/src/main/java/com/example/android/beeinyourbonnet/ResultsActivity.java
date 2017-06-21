package com.example.android.beeinyourbonnet;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class ResultsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    private static final int BOOK_LOADER_ID = 1;

    private BookAdapter mAdapter;

    private TextView mEmptyView;

    private View mLoadingIndicator;

    public static final String LOG_TAG = ResultsActivity.class.getName();

    //Replace with call from SearchActivity
    private static final String SAMPLE_REQUEST_URL =
            "https://www.googleapis.com/books/v1/volumes?q=";

    private String userIn;

    private String searchUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();
        userIn = intent.getStringExtra(EXTRA_MESSAGE);
        searchUrl = SAMPLE_REQUEST_URL + userIn + "&maxResults=20";

        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        ListView bookListView = (ListView) findViewById(R.id.list);

        mLoadingIndicator = findViewById(R.id.progress_bar);

        mEmptyView = (TextView) findViewById(R.id.empty_view);

        bookListView.setEmptyView(mEmptyView);

        mAdapter = new BookAdapter(this, new ArrayList<Book>());

        bookListView.setAdapter(mAdapter);

        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book currentBook = mAdapter.getItem(position);
                Uri bookUri = Uri.parse(currentBook.getmUrl());

                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, bookUri);

                startActivity(websiteIntent);
            }
        });

        if (isConnected) {
            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(BOOK_LOADER_ID, null, ResultsActivity.this);
        } else {
            mLoadingIndicator.setVisibility(View.GONE);
            mEmptyView.setText(R.string.no_internet_message);
        }
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        return new BookLoader(this, searchUrl);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        mLoadingIndicator.setVisibility(View.GONE);
        mAdapter.clear();

        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        }

        mEmptyView.setText(R.string.no_books_message);
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        mAdapter.clear();
    }
}
