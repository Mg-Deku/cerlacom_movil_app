package com.miguel.cerlacommobile.Controladores;

import com.google.firebase.database.DatabaseReference;
import com.miguel.cerlacommobile.Objetos.Agendar;
import com.miguel.cerlacommobile.Objetos.Usuario;

import java.util.HashMap;
import java.util.Map;

public class Ctl_agendar {

    DatabaseReference databaseReference;

    public Ctl_agendar(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    public void agendar_cita(String uid, Agendar agendar){

        databaseReference.child("usuarios").child(uid).child("agendas").push().setValue(agendar);
    }

}
