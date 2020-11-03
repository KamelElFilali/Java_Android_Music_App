package com.example.tp_android_liberty_play_kamel;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
public class FragmentBibliotheque extends Fragment {

    Context ctx;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View fragmentBibliotheque = inflater.inflate(R.layout.fragment_bibliotheque, container, false);
        ctx = fragmentBibliotheque.getContext();



        return fragmentBibliotheque;
    }

}