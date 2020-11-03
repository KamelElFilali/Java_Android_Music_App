package com.example.tp_android_liberty_play_kamel.services;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.tp_android_liberty_play_kamel.BottomNavigationBar;
import com.example.tp_android_liberty_play_kamel.R;
import com.google.gson.Gson;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
public class ApiRechercheFetcher extends AsyncTask<String, Nullable, String> {

    private Context  ctx;
    LinearLayout cardAlbumContainer;


    public ApiRechercheFetcher(Context context, LinearLayout llParam) {
        this.ctx = context;
        this.cardAlbumContainer = llParam;
    }

    @Override
    protected String doInBackground(String... urls) {

//        URL url = null;
//        String json = "";
//        try {
//            url = new URL("http://172.18.62.33/recherches");
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//            json = in.readLine();
//            urlConnection.disconnect();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return json;
        return  "";
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
//        Gson gsonArray = new Gson().fromJson(s,);

        URL url = null;
        String json = "";
        try {
            url = new URL("http://172.18.62.33/recherches");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            json = in.readLine();
            urlConnection.disconnect();

            JSONArray jsonArray = new JSONArray(json);



        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < 10; i++) {


            View viewCardAlbum = View.inflate(ctx , R.layout.card_album_recherche,null);

            Button btn_details = viewCardAlbum.findViewById(R.id.btn_details_album_recherche);

            btn_details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(ctx, BottomNavigationBar.class);
                    intent.putExtra("fragment_details_album",  "true");
                    ctx.startActivity(intent);
                }
            });


            cardAlbumContainer.addView(viewCardAlbum);

        }











    }
}
