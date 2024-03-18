package com.miguel.cerlacommobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button btn_ingresar = (Button) findViewById(R.id.btn_ingresar);
        Button btn_registrarse = (Button) findViewById(R.id.btn_registrarse);

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
}