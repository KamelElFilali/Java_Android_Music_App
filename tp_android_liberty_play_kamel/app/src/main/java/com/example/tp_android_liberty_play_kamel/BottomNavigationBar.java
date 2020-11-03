package com.example.tp_android_liberty_play_kamel;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
public class BottomNavigationBar extends FragmentActivity {

    BottomNavigationView bottomNav;

    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ctx = this;
        String valeurNavigationPlaylist =  "false";
        String valeurNavigationRechercheResultat =  "false";
        String valeurNavigationRechercheDetailsAlbum =  "false";

        Intent intentValeur = getIntent();

        String extraValue = intentValeur.getStringExtra("fragment_playlist"); // recuperer la valeur de l'intent
        if(extraValue != null){
            valeurNavigationPlaylist = extraValue;
        }

        String extraValue2 = intentValeur.getStringExtra("fragment_recherche_resultat");
        if(extraValue2 != null){
            valeurNavigationRechercheResultat = extraValue2;
        }

        String extraValue3 = intentValeur.getStringExtra("fragment_details_album");
        if(extraValue3 != null){
            valeurNavigationRechercheDetailsAlbum = extraValue3;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_bar);
        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);


        if(valeurNavigationPlaylist.equals("true"))
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentPlayList()).commit();
        }
        else if (valeurNavigationRechercheResultat.equals("true")){

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ResultatRechercheFragment()).commit();
        }
        else if (valeurNavigationRechercheDetailsAlbum.equals("true")){

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DetailsAlbumFragment()).commit();
        }
        else {

            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentRecherche()).commit();
            }
        }


    }



    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    Fragment selectedFragment = new Fragment();

                    switch (item.getItemId()) {
                        case R.id.nav_bibliotheque:

                            selectedFragment = new FragmentBibliotheque();
                            break;

                        case R.id.nav_recherche:

                            selectedFragment = new FragmentRecherche();
                            break;

                        case R.id.nav_playlist:

                            selectedFragment = new FragmentPlayList();
                            break;

                        case R.id.nav_profil:

                            selectedFragment = new FragmentProfil();
                            break;
                    }
                    assert selectedFragment != null;
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }

            };





}