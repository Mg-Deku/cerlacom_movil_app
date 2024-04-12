package com.miguel.cerlacommobile.Holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.miguel.cerlacommobile.R;

public class Holder_cuadrilla extends RecyclerView.ViewHolder{

    public CardView cardView;

    public TextView card_numero_cuadrilla,card_nombre, card_apellido, card_telefono, card_estado_cuadrilla;

    public Holder_cuadrilla(@NonNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.card_cuadrilla);

        card_numero_cuadrilla = itemView.findViewById(R.id.card_Numero_cuadrilla);
        card_nombre = itemView.findViewById(R.id.card_nombre);
        card_apellido = itemView.findViewById(R.id.card_apellido);
        card_telefono= itemView.findViewById(R.id.card_telefono);
        card_estado_cuadrilla = itemView.findViewById(R.id.card_estado_cuadrilla);

    }
}
