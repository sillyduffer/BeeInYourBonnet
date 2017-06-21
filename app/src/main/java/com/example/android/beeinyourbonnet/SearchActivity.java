package com.example.android.beeinyourbonnet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Button searchButton = (Button) findViewById(R.id.search_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText searchBox = (EditText) findViewById(R.id.search_box);
                String userIn = searchBox.getText().toString().toLowerCase().replaceAll("\\s","");
                Intent resultIntent = new Intent(SearchActivity.this, ResultsActivity.class);
                resultIntent.putExtra(EXTRA_MESSAGE, userIn);
                startActivity(resultIntent);
            }
        });
    }
}
