package kfsuniversity.faculty.of.specific.art.mainactivity.api;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ApiThread extends Thread {
    private String apiUrl;
    private JSONObject requestBody;
    private ApiCallback callback;

    public ApiThread(String apiUrl, JSONObject requestBody, ApiCallback callback) {
        this.apiUrl = apiUrl;
        this.requestBody = requestBody;
        this.callback = callback;
    }

    @Override
    public void run() {
        String responseMessage = "";
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);


            OutputStream os = connection.getOutputStream();
            os.write(requestBody.toString().getBytes(StandardCharsets.UTF_8));

//            Log.i("signup fra", connection.getContentEncoding());

            os.flush();
            os.close();

            int responseCode = connection.getResponseCode();
            InputStream is = connection.getErrorStream();
            if(is!=null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                responseMessage = response.toString();
            }

            if (responseCode >=200 && responseCode <300) {
                InputStream inputStream = connection.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder response = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                String responseData = response.toString();

                // Call the callback with the API response data
                callback.onApiSuccess(responseData);
            } else {
                // Call the callback with the API error message
                String errorMessage = "API call failed because "+ responseMessage+" with response code " + responseCode;
                callback.onApiError(errorMessage);
            }

            connection.disconnect();
        } catch (MalformedURLException e) {
            // Call the callback with the exception message

            String errorMessage = "API call failed with exception: " + e.getLocalizedMessage();
//            e.printStackTrace();
            callback.onApiError(errorMessage);
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}