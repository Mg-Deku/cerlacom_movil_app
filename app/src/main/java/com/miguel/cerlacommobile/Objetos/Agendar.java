package com.miguel.cerlacommobile.Objetos;

public class Agendar {

    public String inconveniente;
    public String horaInicio;
    public String estado;
    public String horaFinalizado;

    public double latitud;
    public double longitud;

    public String operador;


    public Agendar(){


    }

    public Agendar(String horaInicio, String estado, String horaFinalizado) {
        this.horaInicio = horaInicio;
        this.estado = estado;
        this.horaFinalizado = horaFinalizado;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getHoraFinalizado() {
        return horaFinalizado;
    }

    public void setHoraFinalizado(String horaFinalizado) {
        this.horaFinalizado = horaFinalizado;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }


}
