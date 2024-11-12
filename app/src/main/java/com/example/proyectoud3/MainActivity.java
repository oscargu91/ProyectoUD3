package com.example.proyectoud3;

import android.os.Bundle;
import android.view.View;
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

public class MainActivity extends AppCompatActivity implements CocheAdapter.OnItemClickListener{


    private RecyclerView recyclerView;
    private CocheAdapter adapter;
    private List<Coche> listaCoches;
    private Coche cocheSeleccionado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        // Inicializar RecyclerView
        recyclerView = findViewById(R.id.recyclerView);

        // Crear datos para la lista
        listaCoches = new ArrayList<>();
        listaCoches.add(new Coche(R.drawable.mgzs, "MG ZS", "22000 €", "Híbrido", "1100 Km"));
        listaCoches.add(new Coche(R.drawable.serie1, "BMW Serie 1", "39000 €", "Diesel", "1200 Km"));
        listaCoches.add(new Coche(R.drawable.serie4, "BMW Serie 4", "58000 €", "Gasolina", "900 Km"));
        listaCoches.add(new Coche(R.drawable.kia_ev_nueve, "KIA EV9", "36000 €", "Eléctrico", "600 Km"));
        listaCoches.add(new Coche(R.drawable.rscuatro, "AUDI RS4", "105000 €", "Gasolina", "600 Km"));
        listaCoches.add(new Coche(R.drawable.rs_tres_sedan, "AUDI RS3 Sedán", "92000 €", "Gasolina", "1100 Km"));
        listaCoches.add(new Coche(R.drawable.clasea, "Mercedes clase A", "35000 €", "Diesel", "1200 Km"));
        listaCoches.add(new Coche(R.drawable.claseg, "Mercedes clase G", "190000 €", "Gasolina", "800 Km"));
        listaCoches.add(new Coche(R.drawable.nio_electrico, "Nio", "42000 €", "Eléctrico", "600 Km"));
        listaCoches.add(new Coche(R.drawable.clase_c_amg, "Mercedes clase C AMG", "120000 €", "Gasolina", "600 Km"));

        // Crear y asociar el adaptador
        Switch switchMostrarDetalles = findViewById(R.id.switchMostrarDetalles);
        adapter = new CocheAdapter(listaCoches, MainActivity.this,MainActivity.this, switchMostrarDetalles.isChecked());
        recyclerView.setAdapter(adapter);

        switchMostrarDetalles.setOnCheckedChangeListener((buttonView, isChecked) -> {
            adapter.setMostrarDetalles(isChecked);
        });

        // Establecer el LayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setVerticalScrollBarEnabled(true);





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });




    }

    @Override
    public void onItemClick(View view, int position) {

        Coche coche = listaCoches.get(position);
        String mensaje = "Modelo: " + coche.getModelo() + "\nPrecio: " + coche.getPrecio();

        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();

    }
}