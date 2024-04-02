package com.miguel.cerlacommobile.Controladores;

import com.miguel.cerlacommobile.Objetos.Usuario;

public class Interfaces {

    public Interfaces(){

    }

    public interface Obt_pérfil{

        void perfil(Usuario user);
    }

    public interface Obt_rol{

        void rol(String rol);
    }
}
