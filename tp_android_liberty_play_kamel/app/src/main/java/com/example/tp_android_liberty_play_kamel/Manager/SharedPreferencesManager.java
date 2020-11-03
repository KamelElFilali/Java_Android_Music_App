package com.example.tp_android_liberty_play_kamel.Manager;
import android.content.Context;
import android.content.SharedPreferences;
public class SharedPreferencesManager {

    public static final String getPlaylist(Context ctx){

        SharedPreferences sharedpreferences;
        String playList = "";
        try {
            sharedpreferences = ctx.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            playList = sharedpreferences.getString("playList", null);
        }catch(Exception e){
            e.printStackTrace();
        }

        if(playList == null){
            playList = "";
        }
        return playList;
    }




    public static void addPlaylist(Context ctx, String nom, String valeur) {
        SharedPreferences sharedpreferences;
        sharedpreferences = ctx.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(nom, valeur);
        editor.commit();
    }

}
