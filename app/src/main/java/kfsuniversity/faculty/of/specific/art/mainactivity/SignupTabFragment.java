package kfsuniversity.faculty.of.specific.art.mainactivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONObject;

import kfsuniversity.faculty.of.specific.art.mainactivity.api.ApiCallback;
import kfsuniversity.faculty.of.specific.art.mainactivity.api.ApiThread;

public class SignupTabFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup_tab, container, false);
        EditText email = view.findViewById(R.id.signup_email);
        EditText password = view.findViewById(R.id.signup_password);
        EditText ssid = view.findViewById(R.id.signup_ssid);
        EditText name = view.findViewById(R.id.signup_name);
        Spinner grade = view.findViewById(R.id.study_year);
        Button signupBtn = view.findViewById(R.id.signup_button);

        signupBtn.setOnClickListener((v)->{
            Log.i("signup fragment", "onCreateView: onCLick");
            if (email.getText().toString().length()!=0&&
                    password.getText().toString().length()!=0&&
                    name.getText().toString().length()!=0&&
                    ssid.getText().toString().length()!=0){
                Integer s = Integer.parseInt(grade.getSelectedItem().toString().trim());
                JSONObject requestBody = new JSONObject();

                try {
                    requestBody.put("ssid", ssid.getText().toString().trim());
                    requestBody.put("password", password.getText().toString().trim());
                    requestBody.put("email", email.getText().toString().trim());
                    requestBody.put("name", name.getText().toString().trim());
                    requestBody.put("grade", s);
                }catch (Exception e){
                    Log.i("signup fragment", "onCreateView: "+e.getMessage());
                }


                 String apiUrl = "http://192.168.1.7:9897/users/create-user";
                ApiThread apiThread = new ApiThread(apiUrl, requestBody, new ApiCallback() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onApiSuccess(String response) {
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(()->{
                            Log.i("signup fragment response",response);
                            Toast.makeText(getContext(), response, Toast.LENGTH_LONG*9879).show();
                            Intent intent  = new Intent(getContext(),HomeActivity.class);
                            intent.putExtra("year",1);
                            startActivity(intent);

                        });                    }

                    @Override
                    public void onApiError(String errorMessage) {
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(()->{
                            Log.i("signup fragment error",errorMessage);
                            Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG*8987).show();
                        });

                    }
                });
                apiThread.start();

            }
        });


        return view;
    }
}