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

public class CocheAdapter extends RecyclerView.Adapter <CocheAdapter.CocheViewHolder>{

    private ArrayList<Coche> listaCoches = new ArrayList<>();
    private Context contexto;
    private OnItemClickListener listener;
    private boolean mostrarDetalles;





    // Interfaz para manejar los clics
    public interface OnItemClickListener {

        void OnItemClickCard(View view, int position);
        void onItemClick(View view, int position);
    }

    //Constructor del adaptador listaCoches
    public CocheAdapter(ArrayList<Coche> listaCoches, Context contexto, OnItemClickListener listener, boolean mostrarDetalles) {
        this.listaCoches = (listaCoches != null) ? listaCoches : new ArrayList<Coche>();
        this.contexto = contexto;
        this.listener = listener;
        this.mostrarDetalles = mostrarDetalles;
    }


    // Inflar el layout de cada elemento de la lista y devolver un ViewHolder asociado.
    @NonNull
    @Override
    public CocheAdapter.CocheViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflo el layout del coche
        View itemView = LayoutInflater.from(contexto)
                .inflate(R.layout.layout_coche, parent, false);
        return new CocheViewHolder(itemView,listener);
    }


    // Declaración de las vistas que componen cada tarjeta de coche.
    // Asociar las vistas del layout con los atributos del ViewHolder.

    public static class CocheViewHolder extends RecyclerView.ViewHolder {
        ImageView Imagen;
        TextView Modelo, Precio, Propulsion, Autonomia;
        Button botonFav;
        public CocheViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            Imagen = itemView.findViewById(R.id.idImagen);
            Modelo = itemView.findViewById(R.id.idModelo);
            Precio = itemView.findViewById(R.id.idPrecio);
            Propulsion = itemView.findViewById(R.id.idPropulsion);
            Autonomia = itemView.findViewById(R.id.idAutonomia);
            botonFav = itemView.findViewById(R.id.botonAnhadirFav);

            // Configurar el click para el cardView
            itemView.setOnClickListener(view -> {
                if (listener != null) {
                    listener.OnItemClickCard(view, getAdapterPosition());
                }
            });

            // Configurar el click para cuando pulso el boton de añadir a favoritos
            botonFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(v, getAdapterPosition());
                }
            });


        };
    }

    //Establece si se deben mostrar los detalles adicionales de cada coche en la lista.
    //Notifica al adaptador que los datos han cambiado
    public void setMostrarDetalles(boolean mostrarDetalles) {
        this.mostrarDetalles = mostrarDetalles;
        notifyDataSetChanged();
    }

    // Vincular los datos del coche actual a los elementos visuales del ViewHolder.
    // Configurar los elementos del card para mostrar los detalles del coche.
    @Override
    public void onBindViewHolder (@NonNull CocheViewHolder holder,int position){
        Coche coche = listaCoches.get(position);
        holder.Imagen.setImageResource(coche.getFoto());
        holder.Modelo.setText("Modelo: " + coche.getModelo());
        if (mostrarDetalles) {
            holder.Precio.setVisibility(View.VISIBLE);
            holder.Propulsion.setVisibility(View.VISIBLE);
            holder.Autonomia.setVisibility(View.VISIBLE);

            holder.Precio.setText("Precio: " + coche.getPrecio());
            holder.Propulsion.setText("Propulsión: " + coche.getPropulsion());
            holder.Autonomia.setText("Autonomía: " + coche.getAutonomia());
        } else {
            holder.Precio.setVisibility(View.GONE);
            holder.Propulsion.setVisibility(View.GONE);
            holder.Autonomia.setVisibility(View.GONE);
        }
        //holder.botonFav.setText("hopofdpsgpok");

    }


    // Devolver el tamaño de la lista, que determina la cantidad de elementos a mostrar.
    @Override
    public int getItemCount() {
        return listaCoches.size();
    }

    //Devuelve el tamaño de la lista de coches.
    public int getCount() {
        return listaCoches.size();
    }



}





