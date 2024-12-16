package com.example.proyectoud3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment implements CocheAdapter.OnItemClickListener, CocheAdapterFav.OnItemClickListener {

    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private RecyclerView recyclerFav;
    private ArrayList<Coche> listaCoches;
    private ArrayList<Coche> listaFav;
    private CocheAdapterFav adapterFavoritos;
    private CocheAdapter cocheAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            listaCoches = getArguments().getParcelableArrayList("listaDeCoches");
           // listaFav = getArguments().getParcelableArrayList("listaDeFav");
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            listaFav=new ArrayList<Coche>();
            if (listaCoches == null) {
                Log.w("onCreate", "La lista de coches no se inicializó correctamente.");
                listaCoches = new ArrayList<>(); // Inicializa como una lista vacía si es null.
            }




            Log.i("onCreate", "Lista de coches: " + listaCoches.toString());
        } else {
            Log.e("onCreate", "No se recibieron argumentos.");
            listaCoches = new ArrayList<>(); // Inicializa como una lista vacía por seguridad.

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        initRecyclerViews(view,listaCoches);
        setUpSwitch(view);
        setupButtonToggleFavorites(view);


        // Inflate the layout for this fragment
        return view;
    }

    private void initRecyclerViews(View view, ArrayList<Coche> coche) {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerFav = view.findViewById(R.id.recyclerFavoritos);

        if (recyclerView == null || recyclerFav == null) {
            Log.e(TAG, getString(R.string.error_recyclerview_o_recyclerfav_no_est_n_inicializados));
            return;
        }

        cocheAdapter = new CocheAdapter(listaCoches, getContext(), BlankFragment.this, estadoSwitch(view));
        adapterFavoritos = new CocheAdapterFav(listaFav, getContext(), getOnItemClickEliminarListener());
        RecyclerView recyclerView1 = view.findViewById(R.id.recyclerView);
        RecyclerView recyclerView2 = view.findViewById(R.id.recyclerFavoritos);

        recyclerView1.setAdapter(cocheAdapter);
        recyclerView2.setAdapter(adapterFavoritos);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setVerticalScrollBarEnabled(true);

        recyclerFav.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private boolean estadoSwitch(View view) {
        Switch switchMostrarDetalles = view.findViewById(R.id.switchMostrarDetalles);

        return switchMostrarDetalles.isChecked();
    }



    private void setupButtonToggleFavorites(View view) {
        Button btVerFav = view.findViewById(R.id.verFavoritos);
        btVerFav.setOnClickListener(v -> {
            if (recyclerFav.getVisibility() == View.GONE) {
                recyclerFav.setVisibility(View.VISIBLE);
                btVerFav.setText(R.string.ocultar_favoritos);
                Log.i(TAG, getString(R.string.bot_n_pulsado_mostrando_favoritos));

            } else {
                recyclerFav.setVisibility(View.GONE);
                btVerFav.setText(R.string.ver_favoritos);
                Log.i(TAG, getString(R.string.bot_n_pulsado_ocultando_favoritos));

            }
        });

    }

    private void setUpSwitch(View view){

        Switch switch1= view.findViewById(R.id.switchMostrarDetalles);
        switch1.setOnCheckedChangeListener((buttonView, isChecked) -> {

            cocheAdapter.setMostrarDetalles(isChecked);

        });



    }




    @Override
    public void OnItemClickCard(View view, int position) {
        if (position < 0 || position >= listaCoches.size()) {
            Log.e(TAG, getString(R.string.error_posici_n_inv_lida_en_listacoches) + position);
            return;
        }

        Coche coche = listaCoches.get(position);
        String mensaje = "Modelo: " + coche.getModelo() + "\nPrecio: " + coche.getPrecio();
        Log.i(TAG, getString(R.string.cardview_pulsado) + mensaje);
        Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(View view, int position) {
        Coche cocheFav = listaCoches.get(position);

        if (listaFav != null && !listaFav.contains(cocheFav)) {
            listaFav.add(cocheFav);
            Log.i(TAG, getString(R.string.coche_a_adido_a_favoritos) + cocheFav.getModelo());
            Toast.makeText(getContext(), R.string.coche_a_adido_a_favoritos, Toast.LENGTH_SHORT).show();
            adapterFavoritos.notifyDataSetChanged();
        } else {
            Log.w(TAG, getString(R.string.el_coche_ya_est_en_favoritos) + cocheFav.getModelo());
            Toast.makeText(getContext(), R.string.el_coche_ya_esta_en_favoritos, Toast.LENGTH_SHORT).show();
        }




    }

    public CocheAdapterFav.OnItemClickListener getOnItemClickEliminarListener() {
        return (view, position) -> {
            if (position < 0 || position >= listaFav.size()) {
                Log.e(TAG, getString(R.string.error_posici_n_inv_lida_en_listafav) + position);
                return;
            }

            Coche cocheFavEliminar = listaFav.get(position);
            listaFav.remove(cocheFavEliminar);
            Log.w(TAG, getString(R.string.coche_eliminado_de_favoritos) + cocheFavEliminar.getModelo());

            adapterFavoritos.notifyDataSetChanged();
        };
    }

    @Override
    public void onItemClickEliminar(View view, int position) {
        if (position < 0 || position >= listaFav.size()) {
            Log.e(TAG, getString(R.string.error_posici_n_inv_lida_en_listafav) + position);
            return;
        }
        Coche cocheFavEliminar = listaFav.get(position);
        listaFav.remove(cocheFavEliminar);
        Log.w(TAG, getString(R.string.coche_eliminado_de_favoritos) + cocheFavEliminar.getModelo());

        // Asegúrate de que el adaptador se actualice
        adapterFavoritos.notifyDataSetChanged();
    }

}