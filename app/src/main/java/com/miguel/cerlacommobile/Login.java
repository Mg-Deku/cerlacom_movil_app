package com.miguel.cerlacommobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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

            startActivity(new Intent(this, Principal.class));

        });


    }
}