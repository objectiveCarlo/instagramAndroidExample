package com.example.carlobation.instagramgalleryapp.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.carlobation.instagramgalleryapp.R;
import com.example.carlobation.instagramgalleryapp.oauth.instagram.InstagramApp;


public class MainActivity extends ActionBarActivity {

    public static final String CLIENT_ID = "3dfa01bb3e2c4fb0b386aea6998a8005";
    public static final String CLIENT_SECRET = "e0b200327e184092903736e051fc8d3c";
    public static final String CALLBACK_URL = "ig3dfa01bb3e2c4fb0b386aea6998a8005://authorize";

    private InstagramApp mApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mApp = new InstagramApp(this, CLIENT_ID,
                CLIENT_SECRET, CALLBACK_URL);
        mApp.setListener(listener);

        Button loginButton = (Button)this.findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mApp.authorize();
            }
        });

        if (mApp.hasAccessToken()) {

            launchGalleryActivity();
        }
    }

    private void launchGalleryActivity() {

        Intent intent =  new Intent(MainActivity.this, ImageGalleryActivity.class);
        startActivity(intent);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    InstagramApp.OAuthAuthenticationListener listener = new InstagramApp.OAuthAuthenticationListener() {

        @Override
        public void onSuccess() {

            Toast.makeText(MainActivity.this, mApp.getUserName(), Toast.LENGTH_LONG).show();

            launchGalleryActivity();


        }

        @Override
        public void onFail(String error) {
            Toast.makeText(MainActivity.this, error, Toast.LENGTH_LONG).show();
        }
    };
}
