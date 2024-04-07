package com.miguel.cerlacommobile.Controladores;

import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
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
public class Ctl_agendar {

    DatabaseReference databaseReference;


    public Ctl_agendar(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    public void agendar_cita(String uid, Agendar agendar){

            // Obtener la hora actual
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String horaInicio = sdf.format(new Date());

            // Establecer la hora de inicio y estado
            agendar.setHoraInicio(horaInicio);
            agendar.setEstado("En proceso");

            // Guardar la cita en la base de datos
            databaseReference.child("usuarios").child(uid).child("agendas").push().setValue(agendar);
        }








    }



