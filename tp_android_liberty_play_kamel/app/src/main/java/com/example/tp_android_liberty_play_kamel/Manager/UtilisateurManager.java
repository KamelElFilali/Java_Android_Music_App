package com.example.tp_android_liberty_play_kamel.Manager;
import android.content.Context;
import android.content.SharedPreferences;
public class UtilisateurManager {
    public static final int getIdUtilisateur(Context ctx){

        SharedPreferences sharedpreferences;
        String id_retour = "0";
        try {
            sharedpreferences = ctx.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            id_retour = sharedpreferences.getString("id", null);
        }catch(Exception e){
            e.printStackTrace();
        }

        if(id_retour == null){
            id_retour = "0";
        }

        return Integer.parseInt(id_retour);
    }








    public static void addIdUtilisateur(Context ctx, int id) {
        SharedPreferences sharedpreferences;
        sharedpreferences = ctx.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("id", String.valueOf(id));
        editor.commit();
    }

}


