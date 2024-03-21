package com.miguel.cerlacommobile.Fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseUser;
import com.miguel.cerlacommobile.Controladores.Interfaces;
import com.miguel.cerlacommobile.Login;
import com.miguel.cerlacommobile.MainActivity;
import com.miguel.cerlacommobile.Objetos.Usuario;
import com.miguel.cerlacommobile.R;

public class Fragment_perfil extends Fragment {




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_perfil, container, false);

        TextView txt_correo = vista.findViewById(R.id.txt_correo);
        TextView txt_rol = vista.findViewById(R.id.txt_rol);
        EditText editText_nombre = vista.findViewById(R.id.editText_nombre);
        EditText editText_apellido = vista.findViewById(R.id.editText_apellido);
        EditText editText_telefono = vista.findViewById(R.id.editText_telefono);
        EditText editText_direccion = vista.findViewById(R.id.editText_direccion);

        Button btn_salir = vista.findViewById(R.id.btn_salir);
        Button btn_actualizar = vista.findViewById(R.id.btn_actualizar);

        FirebaseUser firebaseUser = Login.auth.getCurrentUser();


        if(firebaseUser!= null){

            txt_correo.setText(firebaseUser.getEmail());

            Login.ctlUsuario.obtener_ususario(Login.auth.getUid(), user -> {


                editText_nombre.setText(user.nombre);
                editText_apellido.setText(user.apellido);
                editText_telefono.setText(user.telefono);
                editText_direccion.setText(user.direccion);
                txt_rol.setText(user.rol);


            });

            btn_salir.setOnClickListener(v -> {

                Login.auth.signOut();
                requireActivity().finish();
                startActivity(new Intent(vista.getContext(), MainActivity.class));
            });

            btn_actualizar.setOnClickListener(v -> {

                Usuario user = new Usuario();

                user.nombre = editText_nombre.getText().toString();
                user.apellido = editText_apellido.getText().toString();
                user.direccion = editText_direccion.getText().toString();
                user.telefono  = editText_telefono.getText().toString();

                Login.ctlUsuario.actualizar_usuario(firebaseUser.getUid(),user);

                Toast.makeText(vista.getContext(),"Usuario actualizado", Toast.LENGTH_SHORT).show();
            });
        }

        return vista;
    }
}
