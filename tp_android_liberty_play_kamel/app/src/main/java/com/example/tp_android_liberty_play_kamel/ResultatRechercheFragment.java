package com.example.tp_android_liberty_play_kamel;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tp_android_liberty_play_kamel.services.ApiRechercheFetcher;
public class ResultatRechercheFragment extends Fragment {

    Context ctx;
    Intent intentFromRecherche;
    LinearLayout cardAlbumContainer;
    TextView txt;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View resultatRechercheFragment  = inflater.inflate(R.layout.resultat_recherche_fragment, container, false);
        ctx = resultatRechercheFragment.getContext();
        txt =  resultatRechercheFragment.findViewById(R.id.textDescriptionResultat);
        cardAlbumContainer = resultatRechercheFragment.findViewById(R.id.containerAlbum);


        ApiRechercheFetcher apiRechercheFetcher = new ApiRechercheFetcher(ctx, cardAlbumContainer);
        apiRechercheFetcher.execute("http://172.18.62.33/recherches");

        if(intentFromRecherche != null){
            String infoRecherche = intentFromRecherche.getStringExtra("text_recherche");
            txt.setText(infoRecherche);
        }




        return resultatRechercheFragment;
    }

}