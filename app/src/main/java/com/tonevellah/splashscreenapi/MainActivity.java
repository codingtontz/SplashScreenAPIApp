package com.tonevellah.splashscreenapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewTreeObserver;

public class MainActivity extends AppCompatActivity {
    //define a boolean variable
    boolean isAndroidReady = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //handle the splash screen transitions
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);

        //keep splash screen for a long time
        View content = findViewById(android.R.id.content);
        //add the pre draw listener to the view tree observer
        content.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (isAndroidReady){
                    content.getViewTreeObserver().removeOnPreDrawListener(this);
                }

                //define a method to alter isAndroidReady boolean
                dismissSplashScreen();
                return false;
            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void dismissSplashScreen() {
        //use an handler
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //change the boolean
                isAndroidReady = true;
            }
        }, 5000);
    }
}