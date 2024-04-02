package com.miguel.cerlacommobile.Fragmentos;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.miguel.cerlacommobile.Controladores.Ctl_agendar;
import com.miguel.cerlacommobile.Login;
import com.miguel.cerlacommobile.MainActivity;
import com.miguel.cerlacommobile.Objetos.Agendar;
import com.miguel.cerlacommobile.Objetos.Usuario;
import com.miguel.cerlacommobile.R;

public class Fragment_agendar extends Fragment {

    Ctl_agendar ctlAgendar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_agendar,container,false);

        Button btn_agendar = vista.findViewById(R.id.btn_agendar);
        EditText editText_inconveniente = vista.findViewById(R.id.editText_inconveniente);




        ctlAgendar = new Ctl_agendar(MainActivity.databaseReference);

        if(MainActivity.firebaseUser!= null){

            btn_agendar.setOnClickListener(v -> {

                if (!editText_inconveniente.getText().toString().isEmpty()){

                    Agendar ob_agendar = new Agendar();
                    ob_agendar.inconveniente = editText_inconveniente.getText().toString();

                    ctlAgendar.agendar_cita(MainActivity.firebaseUser.getUid(), ob_agendar);

                    Toast.makeText(vista.getContext(),"Agendamiento realizado, un operador se pondra en contacto con usd dentro de pronto", Toast.LENGTH_SHORT).show();
                } else{

                    Toast.makeText(vista.getContext(),"Complete los campos requeridos", Toast.LENGTH_SHORT).show();
                }


            });
        }

        return vista;
    }
}
