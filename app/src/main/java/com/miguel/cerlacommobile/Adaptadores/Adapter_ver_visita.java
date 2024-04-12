package com.miguel.cerlacommobile.Adaptadores;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miguel.cerlacommobile.Holders.Holder_ver_visita;
import com.miguel.cerlacommobile.Objetos.Agendar;
import com.miguel.cerlacommobile.Objetos.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Adapter_ver_visita extends RecyclerView.Adapter<Holder_ver_visita> {

    public List<Agendar> list_ver_visita = new ArrayList<>();

    Context context;

    public Adapter_ver_visita(Context context) {

        this.context = context;
    }

    public void AddVisita(Agendar visita){
        list_ver_visita.add(visita);
        notifyItemInserted(list_ver_visita.size());
    }

    public void Clear_ver_visita(){

        list_ver_visita.clear();
    }
    @NonNull
    @Override
    public Holder_ver_visita onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
        //return new Holder_ver_visita(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder_ver_visita holder, int position) {



    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
