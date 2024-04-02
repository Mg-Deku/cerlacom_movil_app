package com.miguel.cerlacommobile.Fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.miguel.cerlacommobile.R;

public class Frament_cuadrilla extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_cuadrilla,container,false);

        return vista;
    }
}
