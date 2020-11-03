package com.example.tp_android_liberty_play_kamel;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tp_android_liberty_play_kamel.services.ApiDetailsAlbumFetcher;
import com.example.tp_android_liberty_play_kamel.services.ApiRechercheFetcher;
public class DetailsAlbumFragment extends Fragment  implements AdapterView.OnItemSelectedListener, View.OnTouchListener, View.OnKeyListener {

    Context ctx;
    LinearLayout containerPochetteAlbum;
    LinearLayout containerTrackMusic;
    Button button_selectionner_playlist_ajouter;
    Dialog dialog_choisir_playlist;
    String[] categorieMusic = new String[] {"Sport", "Romantique", "Détente", "Voyage", "Motivation"};
    Spinner drop_down_playlist;
    int idAlbum;

    TextView txt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View detailsAlbumFragment  = inflater.inflate(R.layout.details_album, container, false);
        ctx = detailsAlbumFragment.getContext();
        txt =  detailsAlbumFragment.findViewById(R.id.textDescriptionDetail);
        button_selectionner_playlist_ajouter = detailsAlbumFragment.findViewById(R.id.btn_selectioner_playlist_ajouter);
        dialog_choisir_playlist = new Dialog(ctx);
        dialog_choisir_playlist.setContentView(R.layout.dialog_select_playlist);
        drop_down_playlist = dialog_choisir_playlist.findViewById(R.id.dropdown_playlist);
        containerPochetteAlbum = detailsAlbumFragment.findViewById(R.id.layoutContainerPochetteAlbum);
        containerTrackMusic = detailsAlbumFragment.findViewById(R.id.layoutContainerTrackMusic);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_item, categorieMusic);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        drop_down_playlist.setAdapter(adapter);
        drop_down_playlist.setOnItemSelectedListener(this);

        button_selectionner_playlist_ajouter.setOnTouchListener(this);

        ApiDetailsAlbumFetcher apiDetailsAlbumFetcher = new ApiDetailsAlbumFetcher(ctx, containerPochetteAlbum,containerTrackMusic);
        apiDetailsAlbumFetcher.execute("http://localhost:8080/playlists/"+ idAlbum);


        return detailsAlbumFragment;
    }


    private void afficherAlertDialogSelectPlaylistDetailAlbum() {

        Button btn__dialog_ok;
        Button btn__dialog_annuler;

        btn__dialog_ok = dialog_choisir_playlist.findViewById(R.id.btn_dialog_ok);
        btn__dialog_annuler = dialog_choisir_playlist.findViewById(R.id.btn_dialog_annuler);

        btn__dialog_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(ctx, BottomNavigationBar.class);
////              intent.putExtra("fragment_playlist", "true");
//                ctx.startActivity(intent);

                dialog_choisir_playlist.dismiss();

            }
        });

        btn__dialog_annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ctx, BottomNavigationBar.class);
//              intent.putExtra("fragment_playlist", "true");
                ctx.startActivity(intent);
            }
        });

    }


    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        return false;
    }
    // Evenement Ontouch sur le boutton qui change la couleur du boutton a l'appuie et qui ouvre le Dialog
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
//              button_selectionner_playlist.setBackgroundColor(Color.argb(50,0,0,0));
                button_selectionner_playlist_ajouter.setBackgroundResource(R.color.colorAccent);
                afficherAlertDialogSelectPlaylistDetailAlbum();
                Log.i("TAG", "touched down");
                break;
            case MotionEvent.ACTION_MOVE:
                button_selectionner_playlist_ajouter.setBackgroundResource(R.color.colorAccent);
                Log.i("TAG", "moving: (" + x + ", " + y + ")");
                break;
            case MotionEvent.ACTION_UP:
                button_selectionner_playlist_ajouter.setBackgroundColor(Color.BLACK);
                dialog_choisir_playlist.show();
                Log.i("TAG", "touched up");
                break;
        }

        return true;
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        Toast.makeText(ctx.getApplicationContext(), "Ajoutez les morceaux a la Playlist : "+ categorieMusic [position] ,Toast.LENGTH_LONG).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}