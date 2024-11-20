package com.example.proyectoud3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CocheAdapterFav extends RecyclerView.Adapter<CocheAdapterFav.CocheViewHolder> {

    private List<Coche> listaFav;
    private Context contexto;

    public CocheAdapterFav(List<Coche> listaFav, Context contexto) {
        this.listaFav = listaFav;
        this.contexto = contexto;
    }

    @NonNull
    @Override
    public CocheViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(contexto)
                .inflate(R.layout.layout_coche_favorito, parent, false);
        return new CocheViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CocheViewHolder holder, int position) {
        Coche coche = listaFav.get(position);

        // Configuración de la tarjeta
        holder.Imagen.setImageResource(coche.getFoto());
        holder.Modelo.setText("Modelo: " + coche.getModelo());
        holder.Precio.setText("Precio: " + coche.getPrecio());
        holder.Propulsion.setText("Propulsión: " + coche.getPropulsion());
        holder.Autonomia.setText("Autonomía: " + coche.getAutonomia());
    }

    @Override
    public int getItemCount() {
        return listaFav.size();
    }

    public static class CocheViewHolder extends RecyclerView.ViewHolder {
        ImageView Imagen;
        TextView Modelo, Precio, Propulsion, Autonomia;

        public CocheViewHolder(View itemView) {
            super(itemView);
            Imagen = itemView.findViewById(R.id.idImagen);
            Modelo = itemView.findViewById(R.id.idModelo);
            Precio = itemView.findViewById(R.id.idPrecio);
            Propulsion = itemView.findViewById(R.id.idPropulsion);
            Autonomia = itemView.findViewById(R.id.idAutonomia);

        }
    }
}