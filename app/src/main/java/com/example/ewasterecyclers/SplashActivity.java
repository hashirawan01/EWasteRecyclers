package com.example.ewasterecyclers;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        tv = findViewById(R.id.splash);
        // to implements animation of text view
        textViewAnimation();
        //this is to delay for 5000 milisecond and start main activity
        movetomainActivity();
    }

    private void movetomainActivity() {
        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(SplashActivity.this, loginPanelsActivity.class));
                finish();
            }
        }, secondsDelayed * 5000);

    }

    private void textViewAnimation() {
        Animation animSlideDown = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_down);

        tv.startAnimation(animSlideDown);
    }
}