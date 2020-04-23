package com.example.maervit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.maervit.MainActivity;
import com.example.maervit.R;

public class HomeActivity extends AppCompatActivity {

    // VARIABLES
    Animation topAnimation;
    ImageView image;
    boolean shouldStart =true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash_screen);
        getSupportActionBar().hide();
        LogoLauncher logoLauncher = new LogoLauncher();
        logoLauncher.start();

        Handler handler = new Handler();

        handler.postDelayed(run,1410);

        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);

        image = findViewById(R.id.imageView2);
        image.setAnimation(topAnimation);
    }

    private class LogoLauncher extends Thread{
        public void run(){
            try{
                sleep(1410 );
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
            HomeActivity.this.finish();
        }
    }

    Runnable run = new Runnable() {
        @Override
        public void run() {
            if(shouldStart = true) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                HomeActivity.this.startActivity(intent);
                HomeActivity.this.finish();
            }
        }
    };

    @Override
    protected void onPause() {
        shouldStart = false;
        super.onPause();
        this.finish();
    }
}
