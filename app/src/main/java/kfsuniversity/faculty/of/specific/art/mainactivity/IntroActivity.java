package kfsuniversity.faculty.of.specific.art.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

public class IntroActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        findViewById(R.id.employees_ll).setOnClickListener((v)->{
            startActivity(new Intent(IntroActivity.this,EmployeeLoginActivity.class));
        });


        findViewById(R.id.students_ll).setOnClickListener((v)->{

            startActivity(new Intent(IntroActivity.this,MainActivity.class));

        });
    }
}