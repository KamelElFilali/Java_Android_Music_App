package com.example.tp_android_liberty_play_kamel;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.tp_android_liberty_play_kamel.Manager.UtilisateurManager;
public class MainActivity extends Activity {
    Button connexion_button;
    Context ctx;

    @Override
    protected void onStart() {
        super.onStart();

//        final MediaPlayer[] son_accueille = {MediaPlayer.create(ctx, R.raw.son_accueille_friendly)};

        VideoView videoView ;
        videoView = findViewById(R.id.videoView);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.video_background2;
        videoView.setMediaController(mediaController);
        videoView.setVideoPath(path);
        videoView.requestFocus();
        mediaController.setVisibility(View.GONE);
        videoView.setMediaController(mediaController);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setVolume(1f, 1f);
                mp.setLooping(true);
                //son_accueille[0].setLooping(true);

            }
        });
        videoView.start();


        //ouvrir l'activite connexion
        connexion_button = findViewById(R.id.connexion_button);
        connexion_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ctx, LoginFingerPrint.class);

//                if (son_accueille[0].isPlaying()) {
//                    son_accueille[0].stop();
////                        son_accueille[0].release();
////                        son_accueille[0] = MediaPlayer.create(ctx, R.raw.son_message_envoye);
//                }
//                //son_accueille[0].start();

                startActivity(intent);

            }
        });


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ctx = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ConnexionBd.copyBdFromAssets(this);

        int idUtilisateur = UtilisateurManager.getIdUtilisateur(ctx);

        if(idUtilisateur > 0){ // Utilisateur s'est deja connecte

            try {
                Intent intent = new Intent(ctx, BottomNavigationBar.class);
                startActivity(intent);
            }catch(Exception e){
                e.printStackTrace();
            }

        }
        else { // si c'est la premiere fois qu'il ouvre l'application

            setContentView(R.layout.activity_main);
//            ConnexionBd.copyBdFromAssets(this);
        }





    }
}