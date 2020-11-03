package com.example.tp_android_liberty_play_kamel.services;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.tp_android_liberty_play_kamel.BottomNavigationBar;
import com.example.tp_android_liberty_play_kamel.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
public class ApiDetailsAlbumFetcher extends AsyncTask<String, Nullable, String> {

    private Context  ctx;
    LinearLayout containerPochetteAlbum;
    LinearLayout containerTrackMusic;


    public ApiDetailsAlbumFetcher(Context context, LinearLayout llParam, LinearLayout llParam2) {
        this.ctx = context;
        this.containerPochetteAlbum = llParam;
        this.containerTrackMusic = llParam2;
    }

    @Override
    protected String doInBackground(String... urls) {

        String result = null;

        try {



            URL url = new URL(urls[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(false);
            urlConnection.setDoInput(true);
            urlConnection.connect();

            urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            urlConnection.setRequestMethod("POST");

            OutputStream os = urlConnection.getOutputStream();
            os.write(urls[1].getBytes("UTF-8"));

            int codeRetour = urlConnection.getResponseCode();
            if (codeRetour == HttpURLConnection.HTTP_OK) {
                String line;
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                while ((line = in.readLine()) != null)
                    result += line;
            }





        } catch (Exception ex) {

        }

        return result;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        View viewPochetteAlbum = View.inflate(ctx , R.layout.pochette_album,null);





        for(int i = 0; i < 5; i++) {

            final View viewTrackListAlbum = View.inflate(ctx , R.layout.view_track_list,null);
            Button btn_ajouter_morceaux = viewTrackListAlbum.findViewById(R.id.btn_ajouter_morceau);

            btn_ajouter_morceaux.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

//                    Intent intent = new Intent(ctx, BottomNavigationBar.class);
//                    intent.putExtra("fragment_playlist",  "true");
//                    ctx.startActivity(intent);
                    Toast.makeText(ctx.getApplicationContext(), "Le morceau a ete ajoute ",Toast.LENGTH_SHORT).show();

                }


            });

            containerTrackMusic.addView(viewTrackListAlbum);

        }





        containerPochetteAlbum.addView(viewPochetteAlbum);







    }
}
