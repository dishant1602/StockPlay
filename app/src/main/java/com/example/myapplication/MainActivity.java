package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Dictionary;

public class MainActivity extends AppCompatActivity {

    String shortName = "";
    String longName = "";

    String currentpr = "";

    String change = "";
    String changePercent = "";

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout parentContainer = findViewById(R.id.parentContainer);
        LayoutInflater inflater = LayoutInflater.from(this);

        String[] famousCompanies = {"reliance.ns", "tcs.ns", "hdfcbank.ns",
                "infy.ns", "hindunilvr.ns", "itc.ns", "sbin.ns", "hdfc.ns", "lt.ns",
                "m&m.ns", "tatamotors.ns", "maruti.ns", "coalindia.ns", "wipro.ns",
                "bhartiartl.ns", "axisbank.ns", "sunpharma.ns", "asianpaint.ns", "bajfinance.ns"};

        OkHttpClient client = new OkHttpClient();

        String url = "https://yahoo-finance127.p.rapidapi.com/price/";
        Request request = new Request.Builder()
                    .url(url+"hindunilvr.ns")
                    .header("X-RapidAPI-Key", "62a6de7aa4msh7890a53e28aa6b9p1ab03djsnf41166239aa9")
                    .header("X-RapidAPI-Host", "yahoo-finance127.p.rapidapi.com")
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String responseBody = response.body().string();
                        Log.d(TAG, responseBody);
                        try {
                            JSONObject jsonResponse = new JSONObject(responseBody);
                            shortName = jsonResponse.getString("shortName");
                            longName = jsonResponse.getString("longName");
                            JSONObject regularMarketPrice = jsonResponse.getJSONObject("regularMarketPrice");
                            JSONObject regularMarketChange = jsonResponse.getJSONObject("regularMarketChange");
                            JSONObject regularMarketChangePercent = jsonResponse.getJSONObject("regularMarketChangePercent");
                            currentpr = regularMarketPrice.getString("fmt");
                            change = regularMarketChange.getString("fmt");
                            changePercent = regularMarketChangePercent.getString("fmt");










                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Log.e(TAG, "Error: " + response.code() + " " + response.message());
                    }
                    response.close();
                }
            });




//        for(int i=0;i<famousCompanies.length;i++){
//
//            View cardViewLayout = inflater.inflate(R.layout.cardview_layout, parentContainer, false);
//
//            ImageView imageView = cardViewLayout.findViewById(R.id.imageView5);
//            TextView textView1 = cardViewLayout.findViewById(R.id.textView11);
//            TextView textView2 = cardViewLayout.findViewById(R.id.textView12);
//            TextView textView3 = cardViewLayout.findViewById(R.id.textView16);
//            TextView textView4 = cardViewLayout.findViewById(R.id.textView13);
//            TextView textView5 = cardViewLayout.findViewById(R.id.textView14);
//
//
//
//
//            Request request = new Request.Builder()
//                    .url(url+famousCompanies[i])
//                    .header("X-RapidAPI-Key", "62a6de7aa4msh7890a53e28aa6b9p1ab03djsnf41166239aa9")
//                    .header("X-RapidAPI-Host", "yahoo-finance127.p.rapidapi.com")
//                    .build();
//            client.newCall(request).enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    e.printStackTrace();
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    if (response.isSuccessful()) {
//                        String responseBody = response.body().string();
//                        Log.d(TAG, responseBody);
//                        try {
//                            JSONObject jsonResponse = new JSONObject(responseBody);
//                            shortName = jsonResponse.getString("shortName");
//                            longName = jsonResponse.getString("longName");
//                            JSONObject regularMarketPrice = jsonResponse.getJSONObject("regularMarketPrice");
//                            JSONObject regularMarketChange = jsonResponse.getJSONObject("regularMarketChange");
//                            JSONObject regularMarketChangePercent = jsonResponse.getJSONObject("regularMarketChangePercent");
//                            currentpr = regularMarketPrice.getString("fmt");
//                            change = regularMarketChange.getString("fmt");
//                            changePercent = regularMarketChangePercent.getString("fmt");
//
//
//
//
//
//
//
//
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    } else {
//                        Log.e(TAG, "Error: " + response.code() + " " + response.message());
//                    }
//                    response.close();
//                }
//            });
//
//            Log.d(TAG,shortName);
//
//            textView1.setText(shortName);
//            textView2.setText(longName);
//            textView3.setText("₹"+currentpr);
//            if(change.contains("-")){
//                change.replace("-","");
//                textView4.setText("-₹"+change);
//            }
//            else{
//                textView4.setText("+₹"+change);
//            }
//            if(change.contains("-")){
//
//                textView5.setText(change);
//            }
//            else{
//                textView5.setText("+"+change);
//            }
//
//            parentContainer.addView(cardViewLayout);
//
//        }


    }
}
