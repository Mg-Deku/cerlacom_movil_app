package com.miguel.cerlacommobile.Controladores;

import android.app.AlertDialog;

import com.miguel.cerlacommobile.Objetos.Usuario;

public class Interfaces {

    public Interfaces(){

    }

    public interface build{
        void verbuilder(AlertDialog.Builder builder);
    }


    public interface Obt_pérfil{

        void perfil(Usuario user);
    }

    public interface Obt_rol{

        void rol(String rol);
    }
}
