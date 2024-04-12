package com.miguel.cerlacommobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Add_usuarios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_usuarios);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setOnClickListener(view -> finish());

        EditText editText_correo = findViewById(R.id.editText_correo);
        EditText editText_contraseña = findViewById(R.id.editText_contraseña);
        EditText editText_nombre = findViewById(R.id.editText_nombre);
        EditText editText_apellido = findViewById(R.id.editText_apellido);
        EditText editText_telefono = findViewById(R.id.editText_telefono);
        Spinner spinner_rol = findViewById(R.id.spinner_rol);

        ArrayAdapter<CharSequence> adapter_rol = ArrayAdapter.createFromResource(this,R.array.Roles, android.R.layout.simple_spinner_item);
        adapter_rol.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner_rol.setAdapter(adapter_rol);

        Button btn_crear_usuario = findViewById(R.id.btn_crear_usuario);

        btn_crear_usuario.setOnClickListener(v -> {
            String usuario = editText_correo.getText().toString().trim();
            String clave = editText_contraseña.getText().toString().trim();
            String nombre = editText_nombre.getText().toString().trim();
            String apellido = editText_apellido.getText().toString().trim();
            String telefono = editText_telefono.getText().toString().trim();
            String rol = spinner_rol.getSelectedItem().toString();

            if(!usuario.isEmpty() && !clave.isEmpty() && !nombre.isEmpty() && !apellido.isEmpty() && !rol.equalsIgnoreCase("Selecciona")){

            }else{

                Toast.makeText(this, "Completa toda la informacion requerida", Toast.LENGTH_SHORT).show();

            }
        });
    }
}