package com.miguel.cerlacommobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.miguel.cerlacommobile.Controladores.Ctl_usuario;
import com.miguel.cerlacommobile.Controladores.Interfaces;

public class Login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setOnClickListener(view -> finish());

        EditText editText_correo = findViewById(R.id.editText_correo);
        EditText editText_contraseña = findViewById(R.id.editText_contraseña);
        Button btn_ingresar = findViewById(R.id.btn_ingresar);



        btn_ingresar.setOnClickListener(v -> {


            String usuario = editText_correo.getText().toString().trim();
            String contraseña = editText_contraseña.getText().toString().trim();

            if(!usuario.isEmpty() && !contraseña.isEmpty()){

                MainActivity.auth.signInWithEmailAndPassword(usuario,contraseña).addOnCompleteListener(this,task -> {

                    if(task.isSuccessful()){

                        MainActivity.firebaseUser = MainActivity.auth.getCurrentUser();

                        if(MainActivity.firebaseUser!=null){

                            startActivity(new Intent(this, Principal.class));


                        }
                    }
                }).addOnFailureListener(this,e -> {
                    Toast.makeText(this, "Error al iniciar sesion", Toast.LENGTH_LONG).show();
                });

            }else{

                Toast.makeText(this, "Campos vacios", Toast.LENGTH_SHORT).show();
            }







        });


    }

}