package com.miguel.cerlacommobile.Adaptadores;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miguel.cerlacommobile.Holders.Holder_cuadrilla;
import com.miguel.cerlacommobile.Objetos.Agendar;
import com.miguel.cerlacommobile.Objetos.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Adapter_cuadrilla extends RecyclerView.Adapter<Holder_cuadrilla> {

    public List<Usuario> list_Cuadrilla = new ArrayList<>();

    Context context;

    public Adapter_cuadrilla(Context context){

        this.context = context;
    }



    @NonNull
    @Override
    public Holder_cuadrilla onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder_cuadrilla holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
