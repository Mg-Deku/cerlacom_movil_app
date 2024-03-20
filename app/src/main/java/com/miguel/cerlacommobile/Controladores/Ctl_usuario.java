package com.miguel.cerlacommobile.Controladores;

import com.google.firebase.database.DatabaseReference;
import com.miguel.cerlacommobile.Objetos.Usuario;

public class Ctl_usuario {


    DatabaseReference databaseReference;

    public Ctl_usuario(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    public void crear_usuario(String uid, Usuario user){

        databaseReference.child("usuarios").child(uid).setValue(user);


    }

}
