package com.example.listaprisutnosti;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

/**
 * Created by: Andrea
 *
 * The combination of scaling and fading animations creates an effect where the image grows and fades out simultaneously
 * during the splash screen. The delay introduced before starting the MainActivity ensures that users have enough time
 * to see the splash screen
 */

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Hide the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Hide the navigation bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        // Get reference to the ImageView in the layout
        ImageView splashImageView = findViewById(R.id.imageView);

        // Create a ScaleAnimation for resizing the image
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f, 3.0f, // X-axis scale: from 1.0 to 2.0
                1.0f, 3.0f, // Y-axis scale: from 1.0 to 2.0
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point X (center)
                Animation.RELATIVE_TO_SELF, 0.5f  // Pivot point Y (center)
        );

// Create an AlphaAnimation for fading out
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, -0.1f);

// Create an AnimationSet to run both animations together
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setDuration(3500); // 3.5 seconds

// Apply the AnimationSet to the ImageView
        splashImageView.startAnimation(animationSet);

        // Use Handler to delay the start of the MainActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Create an Intent to start the MainActivity
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);

                // Finish the splash screen activity to remove it from the stack
                finish();
            }
        }, 3000); //3 seconds delay before starting MainActivity


    }


}