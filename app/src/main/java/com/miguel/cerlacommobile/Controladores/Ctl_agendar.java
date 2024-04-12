package com.miguel.cerlacommobile.Controladores;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.miguel.cerlacommobile.Adaptadores.Adapter_ver_visita;
import com.miguel.cerlacommobile.Objetos.Agendar;
import com.miguel.cerlacommobile.Objetos.Usuario;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Ctl_agendar {

    DatabaseReference databaseReference;


    public Ctl_agendar(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    public void agendar_cita(String uid, Agendar agendar, double latitud, double longitud){

        // Obtener la hora actual
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String horaInicio = sdf.format(new Date());

        // Establecer la hora de inicio, estado, latitud y longitud
        agendar.setHoraInicio(horaInicio);
        agendar.setEstado("En proceso");
        agendar.setLatitud(latitud);
        agendar.setLongitud(longitud);

        // Guardar la cita en la base de datos
        databaseReference.child("usuarios").child(uid).child("agendas").push().setValue(agendar);
    }

    public void VerAgendas(Adapter_ver_visita list_ver_visita, String uid, final TextView txt_existe, final ProgressBar progressBar, TextView txt_contandor){
        progressBar.setVisibility(View.VISIBLE);

        txt_existe.setVisibility(View.VISIBLE);

        databaseReference.child("usuarios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    list_ver_visita.Clear_ver_visita();
                    int contador = 0;

                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                        if (snapshot.child("agendas").exists()){

                            for (DataSnapshot datos : snapshot.child("agendas").getChildren()) {

                                Agendar agendas =new Agendar();
                                Usuario user = new Usuario();

                                if(snapshot.child("nombre").exists()){
                                    user.nombre = Objects.requireNonNull(snapshot.child("nombre").getValue()).toString();
                                }

                                if(snapshot.child("apellido").exists()){
                                    user.apellido = Objects.requireNonNull(snapshot.child("apellido").getValue()).toString();
                                }

                                if(snapshot.child("telefono").exists()){
                                    user.telefono = Objects.requireNonNull(snapshot.child("telefono").getValue()).toString();
                                }

                                if(snapshot.child("direccion").exists()){
                                    user.direccion = Objects.requireNonNull(snapshot.child("direccion").getValue()).toString();
                                }

                                if(snapshot.child("rol").exists()){
                                    user.rol = Objects.requireNonNull(snapshot.child("rol").getValue()).toString();
                                }

                                if (datos.child("incoveniente").exists()) {
                                    agendas.inconveniente = Objects.requireNonNull(datos.child("incoveniente").getValue()).toString();
                                }

                                if (datos.child("horaInicio").exists()) {
                                    agendas.horaInicio = Objects.requireNonNull(datos.child("horaInicio").getValue()).toString();
                                }

                                if (datos.child("estado").exists()) {
                                    agendas.estado = Objects.requireNonNull(datos.child("estado").getValue()).toString();
                                }

                                if (datos.child("horaFinalizado").exists()) {
                                    agendas.horaFinalizado = Objects.requireNonNull(datos.child("horaFinalizado").getValue()).toString();
                                }

                                if (datos.child("latitud").exists()) {
                                    agendas.latitud = Double.parseDouble(Objects.requireNonNull(datos.child("latitud").getValue()).toString());
                                }

                                if (datos.child("longitud").exists()) {
                                    agendas.longitud = Double.parseDouble(Objects.requireNonNull(datos.child("longitud").getValue()).toString());
                                }

                                if (datos.child("operador").exists()) {
                                    agendas.operador = Objects.requireNonNull(datos.child("operador").getValue()).toString();
                                }

                            }

                        }

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

    }

}




