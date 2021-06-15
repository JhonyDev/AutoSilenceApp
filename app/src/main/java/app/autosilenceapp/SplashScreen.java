package app.autosilenceapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;

import app.autosilenceapp.SignUp.Login;
import app.autosilenceapp.onboardScreens.AudioService;
import app.autosilenceapp.onboardScreens.WelcomeActivity;

public class SplashScreen extends Activity {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        Log.i("TAG", "onCreate: start notification");
//        startService(new Intent(this, AudioService.class));

//        startForegroundService(new Intent(this, AudioService.class));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            SharedPreferences pref = getSharedPreferences("mypref", MODE_PRIVATE);
            if (pref.getBoolean("firststart", true)) {
                startActivity(new Intent(SplashScreen.this, WelcomeActivity.class));
                // update sharedpreference - another start wont be the first
                SharedPreferences.Editor editor = pref.edit();
                editor.putBoolean("firststart", false);
                editor.apply(); // apply changes
                // first start, show your dialog | first-run code goes here
            } else {
                startActivity(new Intent(SplashScreen.this, Login.class));
            }

            finish();
        }, 2000);
    }
}