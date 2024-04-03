package com.miguel.cerlacommobile.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.miguel.cerlacommobile.Holders.Holder_usuario;
import com.miguel.cerlacommobile.Objetos.Usuario;
import com.miguel.cerlacommobile.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_usuario extends RecyclerView.Adapter<Holder_usuario> {

    public List<Usuario> list_usuario = new ArrayList<>();

    Context context;

    public Adapter_usuario(Context context) {

        this.context = context;
    }

    public void AddUsuario(Usuario user){
        list_usuario.add(user);
        notifyItemInserted(list_usuario.size());
    }

    public void ClearUsuario(){

        list_usuario.clear();
    }



    @NonNull
    @Override
    public Holder_usuario onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_usuarios, parent, false);
        return new Holder_usuario(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder_usuario holder, int position) {

        holder.card_nombre.setText(list_usuario.get(position).nombre);
        holder.card_apellido.setText(list_usuario.get(position).apellido);
        holder.card_correo.setText(list_usuario.get(position).correo);
        holder.card_rol.setText(list_usuario.get(position).rol);

        holder.cardView.setOnClickListener(v -> {
            //
        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
