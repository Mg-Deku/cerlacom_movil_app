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
import com.miguel.cerlacommobile.Principal;
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

        FirebaseUser firebaseUser = MainActivity.auth.getCurrentUser();


        if(firebaseUser!= null){

            txt_correo.setText(firebaseUser.getEmail());

            txt_rol.setText(Principal.rol_usuario);

            MainActivity.ctlUsuario.obtener_ususario(MainActivity.auth.getUid(), user -> {


                editText_nombre.setText(user.nombre);
                editText_apellido.setText(user.apellido);
                editText_telefono.setText(user.telefono);
                editText_direccion.setText(user.direccion);
                txt_rol.setText(user.rol);


            });

            btn_salir.setOnClickListener(v -> {

                MainActivity.auth.signOut();
                requireActivity().finish();
                startActivity(new Intent(vista.getContext(), MainActivity.class));
            });

            btn_actualizar.setOnClickListener(v -> {

                String nombre = editText_nombre.getText().toString().trim();
                String apellido = editText_apellido.getText().toString().trim();
                String direccion = editText_direccion.getText().toString().trim();
                String telefono = editText_telefono.getText().toString().trim();


                Usuario user = new Usuario();

                if (!nombre.isEmpty()){

                    user.nombre = nombre;
                    user.apellido = apellido;
                    user.direccion = direccion;
                    user.telefono  = telefono;

                    MainActivity.ctlUsuario.actualizar_usuario(firebaseUser.getUid(),user);

                    Toast.makeText(vista.getContext(),"Usuario actualizado", Toast.LENGTH_SHORT).show();
                } else{

                    Toast.makeText(vista.getContext(),"Complete los campos requeridos", Toast.LENGTH_SHORT).show();
                }





            });
        }

        return vista;
    }
}
