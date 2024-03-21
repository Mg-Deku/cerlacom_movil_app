package com.miguel.cerlacommobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.miguel.cerlacommobile.Objetos.Usuario;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setOnClickListener(view -> finish());

        EditText editText_correo = findViewById(R.id.editText_correo);
        EditText editText_contraseña = findViewById(R.id.editText_contraseña);
        EditText editText_nombre = findViewById(R.id.editText_nombre);
        EditText editText_apellido = findViewById(R.id.editText_apellido);
        EditText editText_telefono = findViewById(R.id.editText_telefono);


        Button btn_registrarse = findViewById(R.id.btn_registrarse);

        btn_registrarse.setOnClickListener(v -> {

            String usuario = editText_correo.getText().toString().trim();
            String contraseña = editText_contraseña.getText().toString().trim();
            String nombre = editText_nombre.getText().toString().trim();
            String apellido = editText_apellido.getText().toString().trim();
            String telefono = editText_telefono.getText().toString().trim();

            if(!usuario.isEmpty() && !contraseña.isEmpty() && !nombre.isEmpty() && !apellido.isEmpty()){

                Login.auth.signInWithEmailAndPassword(usuario,contraseña).addOnCompleteListener(this,task -> {

                    if(task.isSuccessful()){

                        if(Login.auth.getCurrentUser()!= null){

                            Usuario user = new Usuario();

                            user.nombre = nombre;
                            user.apellido = apellido;
                            user.correo = usuario;
                            user.telefono = telefono;
                            user.rol = "Usuario";


                            Login.ctlUsuario.crear_usuario(Login.auth.getUid(),user);

                            Login.auth.signOut();

                            Toast.makeText(this,"Usuario creado correctamente", Toast.LENGTH_SHORT).show();


                        }
                    }
                }).addOnFailureListener(this,e -> {

                    Toast.makeText(this,"Error al crear ususario", Toast.LENGTH_LONG).show();
                });


            }else{

                Toast.makeText(this, "Campos vacios", Toast.LENGTH_SHORT).show();


            }


        });


    }
}