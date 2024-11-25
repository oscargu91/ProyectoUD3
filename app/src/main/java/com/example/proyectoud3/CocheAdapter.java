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

import java.util.List;

public class CocheAdapter extends RecyclerView.Adapter <CocheAdapter.CocheViewHolder>{

    private List<Coche> listaCoches;
    private Context contexto;
    private OnItemClickListener listener;
    private boolean mostrarDetalles;





    // Interfaz para manejar los clics
    public interface OnItemClickListener {

        void OnItemClickCard(View view, int position);
        void onItemClick(View view, int position);
    }

    //Constructor del adaptador listaCoches
    public CocheAdapter(List<Coche> listaCoches, Context contexto, OnItemClickListener listener, boolean mostrarDetalles) {
        this.listaCoches = listaCoches;
        this.contexto = contexto;
        this.listener = listener;
        this.mostrarDetalles = mostrarDetalles;
    }



    @NonNull
    @Override
    public CocheAdapter.CocheViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflo el layout del coche
        View itemView = LayoutInflater.from(contexto)
                .inflate(R.layout.layout_coche, parent, false);
        return new CocheViewHolder(itemView,listener);
    }


    // Clase ViewHolder para mantener las vistas de cada coche
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


    public void setMostrarDetalles(boolean mostrarDetalles) {
        this.mostrarDetalles = mostrarDetalles;
        notifyDataSetChanged();
    }


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



    @Override
    public int getItemCount() {
        return listaCoches.size();
    }


    public int getCount() {
        return listaCoches.size();
    }



}





