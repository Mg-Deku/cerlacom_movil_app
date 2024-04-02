package com.miguel.cerlacommobile.Controladores;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.miguel.cerlacommobile.Objetos.Usuario;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Ctl_usuario {


    DatabaseReference databaseReference;

    public Ctl_usuario(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    public void crear_usuario(String uid, Usuario user){

        databaseReference.child("usuarios").child(uid).setValue(user);


    }

    public void actualizar_usuario(String uid, Usuario user){

        Map<String,Object> datos = new HashMap<>();
        datos.put("nombre",user.nombre);
        datos.put("apellido",user.apellido);
        datos.put("telefono",user.telefono);
        datos.put("direccion",user.direccion);

        databaseReference.child("usuarios").child(uid).updateChildren(datos);
    }

    public void obtener_rol(String uid, Interfaces.Obt_rol obtRol){

        databaseReference.child("Usuarios").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    if (snapshot.child("rol").exists()){
                        obtRol.rol(snapshot.child("rol").getValue().toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    public void obtener_ususario(String uid, Interfaces.Obt_pérfil obtPérfil){

        databaseReference.child("usuarios").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

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

                    obtPérfil.perfil(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

    }

}
