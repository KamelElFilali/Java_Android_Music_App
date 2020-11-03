package com.example.tp_android_liberty_play_kamel.services;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.tp_android_liberty_play_kamel.BottomNavigationBar;
import com.example.tp_android_liberty_play_kamel.R;
public class ApiPlaylistFetcher extends AsyncTask<String, Nullable, String> {

    private Context  ctx;
    LinearLayout containerCardPlaylist;
    LinearLayout containerVideoYoutubePlaylist;
    Dialog dialog_choisir_playlist;
    Button btnChoisirTrackPlaylist;


    public ApiPlaylistFetcher(Context context, LinearLayout llParam, LinearLayout llParam2, Dialog dialog_choisir_playlist) {
        this.ctx = context;
        this.containerCardPlaylist = llParam;
        this.containerVideoYoutubePlaylist = llParam2;
        this.dialog_choisir_playlist = dialog_choisir_playlist;
    }

    @Override
    protected String doInBackground(String... urls) {

        return "";
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        final View viewCardPlaylist = View.inflate(ctx , R.layout.card_track_playlist,null);
        final View viewVideoYoutube = View.inflate(ctx , R.layout.video_youtube,null);
        Button btn_ecouter_track_playlist = viewCardPlaylist.findViewById(R.id.btn_ecouter_track_playlist);

        btnChoisirTrackPlaylist = dialog_choisir_playlist.findViewById(R.id.btn_dialog_ok);

        btnChoisirTrackPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                for(int i = 0; i < 10; i++) {
                    final View viewCardPlaylist = View.inflate(ctx , R.layout.card_track_playlist,null);
                    containerCardPlaylist.addView(viewCardPlaylist);
                    final View viewVideoYoutube = View.inflate(ctx , R.layout.video_youtube,null);
                    Button btn_ecouter_track_playlist = viewCardPlaylist.findViewById(R.id.btn_ecouter_track_playlist);


                    btn_ecouter_track_playlist.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

//                Intent intent = new Intent(ctx, BottomNavigationBar.class);
//                intent.putExtra("fragment_details_album",  "true");
//                ctx.startActivity(intent);

                            containerVideoYoutubePlaylist.addView(viewVideoYoutube);


                        }
                    });



                }

                dialog_choisir_playlist.dismiss();


            }
        });












    }
}
