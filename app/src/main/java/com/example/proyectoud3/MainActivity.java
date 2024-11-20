package com.example.proyectoud3;

import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity implements CocheAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private RecyclerView recyclerFav;
    private CocheAdapter adapter;
    private CocheAdapterFav adapterFavoritos;
    private List<Coche> listaCoches;
    private List<Coche> listaFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Inicializar RecyclerViews
        recyclerView = findViewById(R.id.recyclerView);
        recyclerFav = findViewById(R.id.recyclerFavoritos);

        // Crear datos para la lista
        listaCoches = new ArrayList<>();
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

        listaFav = new ArrayList<>();

        // Crear y asociar los adaptadores para ambos RecyclerViews
        Switch switchMostrarDetalles = findViewById(R.id.switchMostrarDetalles);

        // Adaptador para la lista principal de coches
        adapter = new CocheAdapter(listaCoches, MainActivity.this, this, switchMostrarDetalles.isChecked());

        // Adaptador para la lista de favoritos
        adapterFavoritos = new CocheAdapterFav(listaFav, MainActivity.this);

        recyclerView.setAdapter(adapter);
        recyclerFav.setAdapter(adapterFavoritos);

        // Cambiar visibilidad de detalles dependiendo del switch
        switchMostrarDetalles.setOnCheckedChangeListener((buttonView, isChecked) -> {
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

        Button btVerFav = findViewById(R.id.verFavoritos);

        btVerFav.setOnClickListener(v -> {
            if (recyclerFav.getVisibility() == View.GONE) {
                recyclerFav.setVisibility(View.VISIBLE); // Mostrar lista de favoritos
                btVerFav.setText("Ocultar Favoritos"); // Cambiar texto del botón
            } else {
                recyclerFav.setVisibility(View.GONE); // Ocultar lista de favoritos
                btVerFav.setText("Ver Favoritos"); // Cambiar texto del botón
            }
        });

        Button botonEliminar = findViewById(R.id.botonAñadirFav);




    }

    @Override
    public void OnItemClickCard(View view, int position) {
        Coche coche = listaCoches.get(position);
        String mensaje = "Modelo: " + coche.getModelo() + "\nPrecio: " + coche.getPrecio();
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(View view, int position) {
        Coche cocheFav = listaCoches.get(position);



        // Verificar si el coche ya está en la lista de favoritos
        if (!listaFav.contains(cocheFav)) {
            // Si no está en favoritos, se añade
            listaFav.add(cocheFav);  // Añadir coche a la lista de favoritos
            cocheFav.setBooleanoFav(true);  // Actualizar el campo booleanoFav a true
            Button bt = (Button) view.findViewById(R.id.botonAñadirFav);
            bt.setText("Favorito");
            Toast.makeText(this, "Coche añadido a favoritos", Toast.LENGTH_SHORT).show();

        } else {

            // Si el coche ya está en favoritos, lo eliminamos
            listaFav.remove(cocheFav);  // Eliminar coche de la lista de favoritos
            cocheFav.setBooleanoFav(false);  // Actualizar el campo booleanoFav a false
            Button bt = (Button) view.findViewById(R.id.botonAñadirFav);  // Asegúrate de usar el ID correcto
            bt.setText("Añadir a favoritos");  // Cambiar el texto del botón de vuelta a "Añadir a favoritos"
            Toast.makeText(this, "Coche eliminado de favoritos", Toast.LENGTH_SHORT).show();

        }

        // Notificar al adaptador de favoritos que hay un cambio
        adapterFavoritos.notifyDataSetChanged();


    }



}











