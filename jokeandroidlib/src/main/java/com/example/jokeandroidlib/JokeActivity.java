package com.example.jokeandroidlib;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class JokeActivity extends AppCompatActivity implements jokeFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle.putString("joke",intent.getStringExtra("joke"));
        Log.i("launch", "launch_activity");
        jokeFragment jokeFrag = new jokeFragment();
        jokeFrag.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.container,jokeFrag,"jokeFragment").commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
