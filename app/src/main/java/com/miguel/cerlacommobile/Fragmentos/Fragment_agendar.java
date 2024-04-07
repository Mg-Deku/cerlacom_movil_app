package com.miguel.cerlacommobile.Fragmentos;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.miguel.cerlacommobile.Controladores.Ctl_agendar;
import com.miguel.cerlacommobile.Login;
import com.miguel.cerlacommobile.MainActivity;
import com.miguel.cerlacommobile.Objetos.Agendar;
import com.miguel.cerlacommobile.Objetos.Usuario;
import com.miguel.cerlacommobile.R;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class Fragment_agendar extends Fragment {

    Ctl_agendar ctlAgendar;

    DatabaseReference databaseReference;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_agendar,container,false);

        Button btn_agendar = vista.findViewById(R.id.btn_agendar);
        EditText editText_inconveniente = vista.findViewById(R.id.editText_inconveniente);




        ctlAgendar = new Ctl_agendar(MainActivity.databaseReference);

        if (MainActivity.firebaseUser != null) {
            btn_agendar.setOnClickListener(v -> {
                if (!editText_inconveniente.getText().toString().isEmpty()) {
                    // Verificar si ya hay un agendamiento en proceso
                    databaseReference.child("usuarios").child(MainActivity.firebaseUser.getUid()).child("agendas")
                            .orderByChild("estado").equalTo("En proceso")
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        // Ya hay un agendamiento en proceso, mostrar mensaje
                                        Toast.makeText(vista.getContext(), "Ya tienes un agendamiento en proceso", Toast.LENGTH_SHORT).show();
                                    } else {
                                        // No hay agendamientos en proceso, proceder a guardar en la base de datos

                                        Agendar ob_agendar = new Agendar();
                                        ob_agendar.inconveniente = editText_inconveniente.getText().toString();

                                        ctlAgendar.agendar_cita(MainActivity.firebaseUser.getUid(), ob_agendar);

                                        Toast.makeText(vista.getContext(), "Agendamiento realizado, un operador se pondrá en contacto con usted pronto", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    // Manejar cualquier error que ocurra durante la lectura de datos de la base de datos
                                }
                            });
                } else {
                    // Campos requeridos incompletos
                    Toast.makeText(vista.getContext(), "Complete los campos requeridos", Toast.LENGTH_SHORT).show();
                }
            });
        }


        return vista;
    }
}
