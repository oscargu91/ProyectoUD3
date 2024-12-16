package com.example.proyectoud3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CocheAdapterFav extends RecyclerView.Adapter<CocheAdapterFav.CocheViewHolder> {

    private ArrayList<Coche> listaFav;
    private Context contexto;
    private static OnItemClickListener listenerEliminar;


    // Interfaz para manejar los clics
    public interface OnItemClickListener {

        void onItemClickEliminar(View view, int position);

    }

    //Constructor del adapter de coches Favoritos
    public CocheAdapterFav(ArrayList<Coche> listaFav, Context contexto, OnItemClickListener listenerEliminar) {
        this.listaFav = (listaFav != null) ? listaFav : new ArrayList<Coche>();
        this.contexto = contexto;
        this.listenerEliminar = listenerEliminar;
    }

    // Inflar el layout de cada elemento de la lista de favoritos y devolver un ViewHolder asociado.
    @NonNull
    @Override
    public CocheViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(contexto)
                .inflate(R.layout.layout_coche_favorito, parent, false);
        return new CocheViewHolder(itemView);
    }

    // Vincular los datos del coche actual a los elementos visuales del ViewHolder.
    // Configurar los elementos del card para mostrar los detalles del coche.
    @Override
    public void onBindViewHolder(@NonNull CocheViewHolder holder, int position) {
        Coche coche = listaFav.get(position);

        holder.Imagen.setImageResource(coche.getFoto());
        holder.Modelo.setText("Modelo: " + coche.getModelo());
        holder.Precio.setText("Precio: " + coche.getPrecio());
        holder.Propulsion.setText("Propulsión: " + coche.getPropulsion());
        holder.Autonomia.setText("Autonomía: " + coche.getAutonomia());
    }

    // Devolver el tamaño de la lista de favoritos, que determina la cantidad de elementos a mostrar.
    // Devolver el tamaño de la lista de favoritos, que determina la cantidad de elementos a mostrar.
    @Override
    public int getItemCount() {
        if (listaFav == null || listaFav.size() == 0) {
            return 0;
        }
        return listaFav.size();
    }

    // Declaración de las vistas que componen cada tarjeta de coche.
    // Asociar las vistas del layout con los atributos del ViewHolder.
    public static class CocheViewHolder extends RecyclerView.ViewHolder {
        ImageView Imagen;
        TextView Modelo, Precio, Propulsion, Autonomia;
        Button botonEliminar;

        public CocheViewHolder(View itemView) {
            super(itemView);
            Imagen = itemView.findViewById(R.id.idImagen);
            Modelo = itemView.findViewById(R.id.idModelo);
            Precio = itemView.findViewById(R.id.idPrecio);
            Propulsion = itemView.findViewById(R.id.idPropulsion);
            Autonomia = itemView.findViewById(R.id.idAutonomia);
            botonEliminar = itemView.findViewById(R.id.botonEliminarFav);

            botonEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerEliminar.onItemClickEliminar(v, getAdapterPosition());
                }
            });
        }

    }
}