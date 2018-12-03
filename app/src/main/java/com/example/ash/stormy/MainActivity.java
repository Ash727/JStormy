package com.example.ash.stormy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {



    public static final String TAG = MainActivity.class.getName();
//    private CurrentWeather currentWeather ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String APIKEY = "b41658f49aff591dde64e58b873ed86d";
        String Latitude  = "37.8267";
        String Longtitdue = "-122.4233";
        String forCastURL = "https://api.darksky.net/forecast/"+APIKEY+"/"+Latitude+","+Longtitdue;
        String url2 = "https://kitsu.io/api/edge/manga";
        try {
           run(forCastURL);
       }
       catch (Exception e){
           e.printStackTrace();
       }
    }
    
    // Must amd os be loged as an synchrouns response
    public void run(String forCastURL) throws IOException {

        if (isNetworkAvailable()){

            OkHttpClient client = new OkHttpClient();

            //call.enque
            Request request = new Request.Builder()
                    .url(forCastURL)
//                    .header("X-RapidAPI-Key", "aiLRwTbVQBmshvmTjIWI1Ff9Ee0fp17Ryl9jsnEncWB46pAYLo")
                    .build();
            Call call = client.newCall(request);

            // Use asynchrouns method enque
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                }


                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try{
//                JsonReader jsonReader = new JsonReader();
                        String JSON_data= response.body().string();
                        Log.v(TAG, JSON_data);

                        // if the response is bad in correct paramterters for data passed
                        if (response.isSuccessful()){

                             getCurrentDetails(JSON_data);

                        }
                        else {
                            alertUserAboutError(); // alerting to say there is bad parameters no response
                        }
                    }
                    catch (IOException e){
                        Log.e(TAG, "IO Exception caught ",e);
                    }

                    catch (JSONException e){
                        Log.e(TAG, "IO Exception caught ",e);
                    }
                }
            });
        }
        else {
            Toast.makeText(this,"Sorry Network Unavailble ",Toast.LENGTH_LONG).show();
            alertUserAboutError();
        }

        // Top see order of log statment
        Log.d(TAG ,"MAin UI CODE is running!!");
    }

    private CurrentWeather getCurrentDetails( String Json_body) throws JSONException {

        JSONObject JSONforcast = new JSONObject(Json_body);
        String timeZone = JSONforcast.getString("timezone");

        // Get the currenlty object
        JSONObject currently = JSONforcast.getJSONObject("currently");

        String summary = currently.getString("summary");
        Double humidity  = currently.getDouble("humidity");
        long time = currently.getLong("time");
        String icon = currently.getString("icon");
        Double precepChance = currently.getDouble("precipProbability");
        Double temperature = currently.getDouble("temperature");

        CurrentWeather currentWeather = new CurrentWeather();

        // Set the objects variables
        currentWeather.setLocationLable("Alcatraz Isalnd CA");
        currentWeather.setSummary(summary);
        currentWeather.setHumidity(humidity);
        currentWeather.setTime(time);
        currentWeather.setIcon(icon);
        currentWeather.setPrecepChance(precepChance);
        currentWeather.setTemperature(temperature);
        currentWeather.setTimeZone(timeZone);

//        Log.v("PARSED FROM JSON:","TimeZone:"+timeZone+" Summary"+summary);
        Log.v("PARSED FROM JSON:","TimeZone:"+timeZone+" Summary"+summary);
        Log.d(TAG, currentWeather.getFormattedTime());
        return currentWeather;
    }

    private boolean isNetworkAvailable() { // need to check if the network is availble
        boolean isAvialble = false;
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo  networkInfo = manager.getActiveNetworkInfo();

        if((networkInfo != null ) && networkInfo.isConnected()){
            isAvialble = true;

        }

        return isAvialble;
    }

    private void alertUserAboutError() {
//        DialogFragment.instantiate(this,"Somthing cool");
        AlertDialogFragment dialogFragment = new AlertDialogFragment();
        dialogFragment.show(getSupportFragmentManager(),"error_dialog");

    }

    public void startShow() {

////        View view =
//        Intent theIntent = new Intent(this,MainActivity.class);
//        startActivity(theIntent);
    }
}
