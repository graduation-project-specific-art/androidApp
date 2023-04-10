package kfsuniversity.faculty.of.specific.art.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;




public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();


        int year = extras.getInt("year");
        if (year == 1) {
            setContentView(R.layout.level_one);
        }else if (year ==2 || year == 3){
            setContentView(R.layout.level_two);
        }else{
            setContentView(R.layout.level_four);

        }

    }
}