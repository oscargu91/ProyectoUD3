package com.example.proyectoud3;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CocheAdapter.OnItemClickListener, CocheAdapterFav.OnItemClickListener {

    private RecyclerView recyclerView;
    private RecyclerView recyclerFav;
    private CocheAdapter adapter;
    private CocheAdapterFav adapterFavoritos;
    private List<Coche> listaCoches;
    private List<Coche> listaFav;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Log.i(TAG, getString(R.string.oncreate_inicio_de_la_actividad));

        // Inicializar RecyclerViews
        recyclerView = findViewById(R.id.recyclerView);
        recyclerFav = findViewById(R.id.recyclerFavoritos);
        if (recyclerView == null || recyclerFav == null) {
            Log.e(TAG, getString(R.string.error_recyclerview_o_recyclerfav_no_est_n_inicializados));
            return;
        }

        // Crear datos para la lista
        listaCoches = new ArrayList<>();
        listaFav = new ArrayList<>();
        if (listaCoches == null || listaFav == null) {
            Log.e(TAG, getString(R.string.error_listacoches_o_listafav_no_est_n_inicializadas));
            return;
        }
        listaCoches.add(new Coche(R.drawable.mgzs, "MG ZS", "22000 €", "Híbrido", "1100 Km", false));
        listaCoches.add(new Coche(R.drawable.serie1, "BMW Serie 1", "39000 €", "Diesel", "1200 Km",false));
        listaCoches.add(new Coche(R.drawable.serie4, "BMW Serie 4", "58000 €", "Gasolina", "900 Km", false));
        listaCoches.add(new Coche(R.drawable.kia_ev_nueve, "KIA EV9", "36000 €", "Eléctrico", "600 Km",false));
        listaCoches.add(new Coche(R.drawable.rscuatro, "AUDI RS4", "105000 €", "Gasolina", "600 Km",false));
        listaCoches.add(new Coche(R.drawable.rs_tres_sedan, "AUDI RS3 Sedán", "92000 €", "Gasolina", "1100 Km",false));
        listaCoches.add(new Coche(R.drawable.clasea, "Mercedes clase A", "35000 €", "Diesel", "1200 Km",false));
        listaCoches.add(new Coche(R.drawable.claseg, "Mercedes clase G", "190000 €", "Gasolina", "800 Km",false));
        listaCoches.add(new Coche(R.drawable.nio_electrico, "Nio", "42000 €", "Eléctrico", "600 Km",false));
        listaCoches.add(new Coche(R.drawable.clase_c_amg, "Mercedes clase C AMG", "120000 €", "Gasolina", "600 Km",false));

        for (Coche coche : listaCoches) {
            if (coche.getFoto() == 0) {
                Log.w(TAG, getString(R.string.advertencia_imagen_no_encontrada_para_el_coche) + coche.getModelo());
            }
        }
        Log.i(TAG, getString(R.string.oncreate_datos_de_la_lista_de_coches_inicializados));

        
        Switch switchMostrarDetalles = findViewById(R.id.switchMostrarDetalles);

        // Adaptador para la lista principal de coches
        adapter = new CocheAdapter(listaCoches, MainActivity.this, this, switchMostrarDetalles.isChecked());
        if (adapter == null) {
            Log.e(TAG, getString(R.string.error_adaptador_no_inicializado));
        }
        Log.i(TAG, getString(R.string.oncreate_adaptador_de_lista_principal_configurado));

        // Adaptador para la lista de favoritos
        adapterFavoritos = new CocheAdapterFav(listaFav, MainActivity.this,this);
        if (adapterFavoritos == null) {
            Log.e(TAG, getString(R.string.error_adaptador_de_favoritos_no_inicializado));
        }
        Log.i(TAG, getString(R.string.oncreate_adaptador_de_favoritos_configurado));

        recyclerView.setAdapter(adapter);
        recyclerFav.setAdapter(adapterFavoritos);

        // Cambiar visibilidad de detalles dependiendo del switch
        switchMostrarDetalles.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Log.i(TAG, getString(R.string.switch_cambiado_mostrar_detalles) + isChecked);
            adapter.setMostrarDetalles(isChecked);
        });

        // Establecer el layoutManager para la lista principal de coches
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setVerticalScrollBarEnabled(true);

        // Establecer el layoutManager para la lista de favoritos (horizontal)
        LinearLayoutManager layoutManagerFav = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerFav.setLayoutManager(layoutManagerFav);

        // Manejo de los márgenes de las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configurar el botón para alternar la visibilidad de la lista de favoritos.
        // Si la lista está oculta, se muestra y se cambia el texto del botón a "Ocultar Favoritos".
        // Si la lista está visible, se oculta y el texto del botón cambia a "Ver Favoritos".

        Button btVerFav = findViewById(R.id.verFavoritos);
        btVerFav.setOnClickListener(v -> {
            if (recyclerFav.getVisibility() == View.GONE) {
                recyclerFav.setVisibility(View.VISIBLE); // Mostrar lista de favoritos
                btVerFav.setText(R.string.ocultar_favoritos); // Cambiar texto del botón
                Log.i(TAG, getString(R.string.bot_n_pulsado_mostrando_favoritos));
            } else {
                recyclerFav.setVisibility(View.GONE); // Ocultar lista de favoritos
                btVerFav.setText(R.string.ver_favoritos); // Cambiar texto del botón
                Log.i(TAG, getString(R.string.bot_n_pulsado_ocultando_favoritos));
            }
        });
    }

    // Metodo onItemClick en cualquier parte (excepto boton) del cardView para mostrar Toast
    @Override
    public void OnItemClickCard(View view, int position) {

        if (position < 0 || position >= listaCoches.size()) {
            Log.e(TAG, getString(R.string.error_posici_n_inv_lida_en_listacoches) + position);
            return;
        }

        Coche coche = listaCoches.get(position);
        String mensaje = "Modelo: " + coche.getModelo() + "\nPrecio: " + coche.getPrecio();
        Log.i(TAG, getString(R.string.cardview_pulsado) + mensaje);
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    //Metodo en el boton Añadir a favoritos del cardView
    @Override
    public void onItemClick(View view, int position) {
        Coche cocheFav = listaCoches.get(position);

        // Verificar si el coche ya está en la lista de favoritos
        if (!listaFav.contains(cocheFav)) {
            // Si no está en favoritos, se añade
            listaFav.add(cocheFav);
            Log.i(TAG, getString(R.string.coche_a_adido_a_favoritos) + cocheFav.getModelo());
            Toast.makeText(this, R.string.coche_a_adido_a_favoritos, Toast.LENGTH_SHORT).show();

        } else {
            Log.w(TAG, getString(R.string.el_coche_ya_est_en_favoritos) + cocheFav.getModelo());
            Toast.makeText(this, R.string.el_coche_ya_esta_en_favoritos, Toast.LENGTH_SHORT).show();
        }

       // Notificar al adaptador de favoritos que hay un cambio
        adapterFavoritos.notifyDataSetChanged();
        Log.i(TAG, getString(R.string.adapter_de_favoritos_notificado_del_cambio));

    }

    // Acción de eliminar un coche de la lista de favoritos.
    // Se obtiene el coche correspondiente en la posición seleccionada, se elimina de la lista
    @Override
    public void onItemClickEliminar(View view, int position) {
        if (position < 0 || position >= listaFav.size()) {
            Log.e(TAG, getString(R.string.error_posici_n_inv_lida_en_listafav) + position);
            return;
        }
        Coche cocheFavEliminar = listaFav.get(position);
        listaFav.remove(cocheFavEliminar);
        Log.w(TAG, getString(R.string.coche_eliminado_de_favoritos) + cocheFavEliminar.getModelo());
        // Notificar al adaptador de favoritos que hay un cambio
        adapterFavoritos.notifyDataSetChanged();
        Log.i(TAG, getString(R.string.adapter_de_favoritos_notificado_del_cambio_tras_eliminaci_n));
    }
}











