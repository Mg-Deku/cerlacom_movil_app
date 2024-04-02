package com.miguel.cerlacommobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.miguel.cerlacommobile.Controladores.Ctl_usuario;

public class MainActivity extends AppCompatActivity {

    public static FirebaseAuth auth;
    public static FirebaseUser firebaseUser;
    public static Ctl_usuario ctlUsuario;



    public static DatabaseReference databaseReference;

    FirebaseDatabase DB = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_ingresar = (Button) findViewById(R.id.btn_ingresar);
        Button btn_registrarse = (Button) findViewById(R.id.btn_registrarse);

        auth = FirebaseAuth.getInstance();

        databaseReference = DB.getReference();

        ctlUsuario = new Ctl_usuario(databaseReference);

        btn_ingresar.setOnClickListener(view -> {
            Intent i = new Intent();
            i.setClass(this, Login.class);
            startActivity(i);
        });

        btn_registrarse.setOnClickListener(view -> {
            Intent i = new Intent();
            i.setClass(this, Registro.class);
            startActivity(i);
        });

    }

    @Override
    protected void onStart() {
        super.onStart();


        if(auth.getCurrentUser()!= null){



                startActivity(new Intent(this, Principal.class));




        }
    }


}

