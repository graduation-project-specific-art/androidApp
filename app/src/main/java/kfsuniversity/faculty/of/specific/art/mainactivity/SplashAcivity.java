package kfsuniversity.faculty.of.specific.art.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashAcivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_acivity);

        new Handler().postDelayed(()->{
                Intent i = new Intent(SplashAcivity.this,IntroActivity.class);
                startActivity(i);
                finish();
        },3000);
    }
}