package com.example.tp_android_liberty_play_kamel;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.MenuPopupWindow;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tp_android_liberty_play_kamel.services.ApiDetailsAlbumFetcher;
import com.example.tp_android_liberty_play_kamel.services.ApiPlaylistFetcher;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
public class FragmentPlayList extends Fragment implements AdapterView.OnItemSelectedListener, View.OnTouchListener, View.OnKeyListener {

    Context ctx;
    Dialog dialog_choisir_playlist;
    LinearLayout containerCardPlaylist;
    LinearLayout containerVideoYoutubePlaylist;
    Button button_selectionner_playlist;
    TextView txt;
    Button btn__dialog_ok;
    Button btn__dialog_annuler;
    Spinner drop_down_playlist;
    int playlistId;
    LinearLayout ll;
    String[] categorieMusic = new String[] {"Sport", "Romantique", "Détente", "Voyage", "Motivation"};
    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View fragmentPlayList = inflater.inflate(R.layout.fragment_play_list, container, false);
        ctx = fragmentPlayList.getContext();
        button_selectionner_playlist = fragmentPlayList.findViewById(R.id.btn_selectioner_playlist);
        dialog_choisir_playlist = new Dialog(ctx);
        txt = fragmentPlayList.findViewById(R.id.textDescriptionPlaylist);
        containerCardPlaylist = fragmentPlayList.findViewById(R.id.layoutContainerCardPlaylist);
        containerVideoYoutubePlaylist = fragmentPlayList.findViewById(R.id.layoutContainerVideoYoutube);
        dialog_choisir_playlist.setContentView(R.layout.dialog_select_playlist);
        drop_down_playlist = dialog_choisir_playlist.findViewById(R.id.dropdown_playlist);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_item, categorieMusic);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        drop_down_playlist.setAdapter(adapter);
        drop_down_playlist.setOnItemSelectedListener(this);


        button_selectionner_playlist.setOnTouchListener(this);

        ApiPlaylistFetcher apiPlaylistFetcher = new ApiPlaylistFetcher(ctx, containerCardPlaylist,containerVideoYoutubePlaylist,dialog_choisir_playlist);
        apiPlaylistFetcher.execute("http://localhost:8080/playlists/tracks/" + playlistId);

        return fragmentPlayList;
    }

    private void afficherAlertDialogSelectPlaylist() {



        btn__dialog_ok = dialog_choisir_playlist.findViewById(R.id.btn_dialog_ok);
        btn__dialog_annuler = dialog_choisir_playlist.findViewById(R.id.btn_dialog_annuler);

//        btn__dialog_ok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(ctx, BottomNavigationBar.class);
////              intent.putExtra("fragment_playlist", "true");
//                ctx.startActivity(intent);
//            }
//        });

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
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {

        Toast.makeText(ctx.getApplicationContext(), "Selected User: "+ categorieMusic [position] ,Toast.LENGTH_SHORT).show();
        playlistId = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    // Evenement Ontouch sur le boutton qui change la couleur du boutton a l'appuie et qui ouvre le Dialog
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
//              button_selectionner_playlist.setBackgroundColor(Color.argb(50,0,0,0));
                button_selectionner_playlist.setBackgroundResource(R.color.colorAccent);
                afficherAlertDialogSelectPlaylist();
                Log.i("TAG", "touched down");
                break;
            case MotionEvent.ACTION_MOVE:
                button_selectionner_playlist.setBackgroundResource(R.color.colorAccent);
                Log.i("TAG", "moving: (" + x + ", " + y + ")");
                break;
            case MotionEvent.ACTION_UP:
                button_selectionner_playlist.setBackgroundColor(Color.BLACK);
                dialog_choisir_playlist.show();
                Log.i("TAG", "touched up");
                break;
        }

        return true;
    }


//    @Override
//    public boolean dispatchKeyEvent(KeyEvent event) {
//        int action = event.getAction();
//        int keyCode = event.getKeyCode();
//        switch (keyCode) {
//            case KeyEvent.KEYCODE_VOLUME_UP:
//                if (action == KeyEvent.ACTION_DOWN) {
//                    txt.setText("DOWN");
//                }
//                return true;
//            case KeyEvent.KEYCODE_VOLUME_DOWN:
//                if (action == KeyEvent.ACTION_DOWN) {
//                    txt.setText("UP");
//                }
//                return true;
//
//        }
//    }


//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
//            // Do something
//        }
//        return true;
//    }




//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//        int action = event.getAction();
//        keyCode = event.getKeyCode();
//
//        if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)) {
//            if (action == KeyEvent.ACTION_DOWN) {
//                txt.setText("DOWN");
//            }
//            return true;
//        } else if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)) {
//
//            return true;
//        } else
//            return super.onKeyDown(keyCode, event);
//    }
//
    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {

        int keyCode = keyEvent.getKeyCode();

        if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)) {
            if (i == KeyEvent.ACTION_DOWN) {
                txt.setText("DOWN");
            }
            return true;
        } else {
            txt.setText("UP");
            return true;
        }

//        return true;
    }
}