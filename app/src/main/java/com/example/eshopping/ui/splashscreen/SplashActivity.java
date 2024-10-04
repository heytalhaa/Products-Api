package com.example.eshopping.ui.splashscreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eshopping.ui.loginui.LoginActivity;
import com.example.eshopping.R;
import com.example.eshopping.ui.mainactivity.MainActivity;
import com.example.eshopping.ui.onboardingscreen.OnBoardingActivity;
import com.example.eshopping.utils.SharedPrefs;
import com.example.eshopping.utils.Utils;

public class SplashActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        sharedPreferences = getSharedPreferences("onBoardingPrefs", MODE_PRIVATE);
//        boolean isCompleted = sharedPreferences.getBoolean("isOnBoardingCompleted", false);
//        String isLogin = sharedPreferences.getString("access_token", null);
        boolean isCompleted = SharedPrefs.getBoolean(this, "OnBoarding", false);
        String isLogin = SharedPrefs.getString(this, "access_token", null);

        Handler handler = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
              if (!isCompleted){
                    startActivity(new Intent(SplashActivity.this, OnBoardingActivity.class));
                } else if(isCompleted && isLogin == null) {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                } else {
                  startActivity(new Intent(SplashActivity.this, MainActivity.class));
              }
                finish();
            }
        };
        handler.postDelayed(runnable, 2000);
    }
}