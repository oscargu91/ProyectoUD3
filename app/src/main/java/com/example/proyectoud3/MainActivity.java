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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {


    private ArrayList<Coche> listaCoches;
    private ArrayList<Coche> listaFav;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        Log.i(TAG, getString(R.string.oncreate_inicio_de_la_actividad));


        initData();

        if(savedInstanceState ==null){

            listaCoches= initData();
            BlankFragment fragmento = new BlankFragment();
            Bundle args = new Bundle();

            args.putParcelableArrayList("listaDeCoches", listaCoches);

            fragmento.setArguments(args);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainerView,fragmento);
            fragmentTransaction.commit();

        }



    }



    private ArrayList<Coche> initData() {
        ArrayList<Coche> listaCoches = new ArrayList<>();

        if (listaCoches == null) {

            return new ArrayList<>(); // Devuelve una lista vacía en caso de error
        }

        listaCoches.add(new Coche(R.drawable.mgzs, "MG ZS", "22000 €", "Híbrido", "1100 Km", false));
        listaCoches.add(new Coche(R.drawable.serie1, "BMW Serie 1", "39000 €", "Diesel", "1200 Km", false));
        listaCoches.add(new Coche(R.drawable.serie4, "BMW Serie 4", "58000 €", "Gasolina", "900 Km", false));
        listaCoches.add(new Coche(R.drawable.kia_ev_nueve, "KIA EV9", "36000 €", "Eléctrico", "600 Km", false));
        listaCoches.add(new Coche(R.drawable.rscuatro, "AUDI RS4", "105000 €", "Gasolina", "600 Km", false));
        listaCoches.add(new Coche(R.drawable.rs_tres_sedan, "AUDI RS3 Sedán", "92000 €", "Gasolina", "1100 Km", false));
        listaCoches.add(new Coche(R.drawable.clasea, "Mercedes clase A", "35000 €", "Diesel", "1200 Km", false));
        listaCoches.add(new Coche(R.drawable.claseg, "Mercedes clase G", "190000 €", "Gasolina", "800 Km", false));
        listaCoches.add(new Coche(R.drawable.nio_electrico, "Nio", "42000 €", "Eléctrico", "600 Km", false));
        listaCoches.add(new Coche(R.drawable.clase_c_amg, "Mercedes clase C AMG", "120000 €", "Gasolina", "600 Km", false));

        for (Coche coche : listaCoches) {
            if (coche.getFoto() == 0) {
                Log.w(TAG, getString(R.string.advertencia_imagen_no_encontrada_para_el_coche) + coche.getModelo());
            }
        }

        Log.i(TAG, getString(R.string.oncreate_datos_de_la_lista_de_coches_inicializados));

        return listaCoches;
    }




}
