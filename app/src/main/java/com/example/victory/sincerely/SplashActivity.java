package com.example.victory.sincerely;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.victory.sincerely.Tabs.Tabs;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.main_activity);
        Intent intent = new Intent(this, Tabs.class);
        startActivity(intent);
        finish();
    }
}
