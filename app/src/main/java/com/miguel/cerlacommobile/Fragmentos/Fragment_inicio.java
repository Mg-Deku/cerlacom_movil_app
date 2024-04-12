package com.miguel.cerlacommobile.Fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.miguel.cerlacommobile.Mapa;
import com.miguel.cerlacommobile.Principal;
import com.miguel.cerlacommobile.R;

public class Fragment_inicio extends Fragment {

    Button btn_agendar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_inicio,container,false);

        btn_agendar = vista.findViewById(R.id.btn_agendar);

        btn_agendar.setOnClickListener(v -> {

            startActivity(new Intent(vista.getContext(), Mapa.class));
        });

        return vista;
    }
}
