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
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
public class FragmentRecherche extends Fragment {

    Button btn_recherche;
    TextInputEditText editTextRecherche;

    Context ctx;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View fragmentRecherche  = inflater.inflate(R.layout.fragment_recherche, container, false);
        ctx = fragmentRecherche.getContext();

        btn_recherche = fragmentRecherche.findViewById(R.id.btn_rechercher);
        editTextRecherche = fragmentRecherche.findViewById(R.id.edit_text_recherche);


        editTextRecherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String infoRecherche = editTextRecherche.getText().toString();
//                Intent intent = new Intent(ctx, ResultatRechercheFragment.class);
//                intent.putExtra("text_recherche", infoRecherche);
            }
        });


        btn_recherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String infoRecherche = editTextRecherche.getText().toString();
                Intent intent = new Intent(ctx, BottomNavigationBar.class);
                intent.putExtra("fragment_recherche_resultat",  "true");
//                Intent intentInfoRecherche = new Intent(ctx, ResultatRechercheFragment.class);
                intent.putExtra("text_recherche", infoRecherche);
                ctx.startActivity(intent);
            }
        });

        return fragmentRecherche;
    }




}