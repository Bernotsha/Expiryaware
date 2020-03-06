package com.example.expiryaware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Main6Activity extends AppCompatActivity {
public static int timeout=800;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Main6Activity.this,Main3Activity.class);
                startActivity(i);
                finish();
            }
        },timeout);
    }
}
