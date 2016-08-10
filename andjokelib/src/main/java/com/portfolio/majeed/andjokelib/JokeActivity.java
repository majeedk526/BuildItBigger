package com.portfolio.majeed.andjokelib;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        getSupportActionBar().setHomeButtonEnabled(true);

        Intent intent = this.getIntent();

        if(intent.hasExtra("joke")){

            Toast.makeText(this, intent.getStringExtra("joke"),Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this, intent.getStringExtra("no joke received!"),Toast.LENGTH_LONG).show();
        }
    }
}
