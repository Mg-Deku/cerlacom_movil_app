package com.miguel.cerlacommobile.Controladores;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.miguel.cerlacommobile.Adaptadores.Adapter_usuario;
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


    public void Obtener_rol(String uid, Interfaces.Obt_rol obtRol){

        databaseReference.child("usuarios").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    if(snapshot.child("rol").exists()){
                        obtRol.rol(Objects.requireNonNull(snapshot.child("rol").getValue()).toString());
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

    public void VerUsuarios(Adapter_usuario list_usuarios,final TextView txt_existe, final ProgressBar progressBar, TextView txt_contandor){
        progressBar.setVisibility(View.VISIBLE);

        txt_existe.setVisibility(View.VISIBLE);

        databaseReference.child("usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    list_usuarios.ClearUsuario();
                    int contador = 0;

                    for(DataSnapshot datos : snapshot.getChildren()){
                        Usuario user = new Usuario();
                        user.uid = datos.getKey();

                        if(datos.child("nombre").exists()){
                            user.nombre = Objects.requireNonNull(datos.child("nombre").getValue()).toString();
                        }

                        if(datos.child("apellido").exists()){
                            user.apellido = Objects.requireNonNull(datos.child("apellido").getValue()).toString();
                        }

                        if(datos.child("telefono").exists()){
                            user.telefono = Objects.requireNonNull(datos.child("telefono").getValue()).toString();
                        }

                        if(datos.child("direccion").exists()){
                            user.direccion = Objects.requireNonNull(datos.child("direccion").getValue()).toString();
                        }

                        if(datos.child("correo").exists()){
                            user.rol = Objects.requireNonNull(datos.child("correo").getValue()).toString();
                        }

                        if(datos.child("rol").exists()){
                            user.rol = Objects.requireNonNull(datos.child("rol").getValue()).toString();
                        }

                        list_usuarios.AddUsuario(user);
                        contador++;
                    }
                    txt_contandor.setText(contador + "Usuarios");
                    progressBar.setVisibility(View.GONE);
                    txt_existe.setVisibility(list_usuarios.getItemCount() == 0 ? View.VISIBLE : View.GONE);
                    list_usuarios.notifyDataSetChanged();

                }else{
                    list_usuarios.ClearUsuario();
                    list_usuarios.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                    txt_existe.setVisibility(View.VISIBLE);
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
