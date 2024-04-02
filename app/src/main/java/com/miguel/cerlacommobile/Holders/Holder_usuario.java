package com.miguel.cerlacommobile.Holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.miguel.cerlacommobile.R;

public class Holder_usuario extends RecyclerView.ViewHolder {

    public TextView card_nombre, card_apellido, card_correo, card_rol;
    public CardView cardView;
    public Holder_usuario(@NonNull View itemView) {
        super(itemView);

        cardView = itemView.findViewById(R.id.card_usuario);

        card_nombre = itemView.findViewById(R.id.card_nombre);
        card_apellido = itemView.findViewById(R.id.card_apellido);
        card_correo= itemView.findViewById(R.id.card_correo);
        card_rol = itemView.findViewById(R.id.card_rol);
    }
}
