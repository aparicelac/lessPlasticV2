package com.example.lessplastic;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lessplastic.Views.CircleGraphView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class StatsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    RecyclerView recyclerCategory;
    List<Category> categories;
    CircleGraphView circleGraphView;

    public StatsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static StatsFragment newInstance(String param1, String param2) {
        StatsFragment fragment = new StatsFragment();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public void fillList(float[] valores){
        for (int i = 0; i < valores.length; i++){
            categories.add(new Category("#003f5c", "Bolsas", "Mas de lo usual", Float.toString(valores[0]), "1"));
            categories.add(new Category("#444e86", "Botellas", "Mas de lo usual", Float.toString(valores[1]), "1"));
            categories.add(new Category("#955196", "Tecnopor", "Mas de lo usual",Float.toString(valores[2]), "1"));
            categories.add(new Category("#dd5182", "Empaques", "Mas de lo usual", Float.toString(valores[3]), "1"));
            categories.add(new Category("#ff6e54", "PVC", "Mas de lo usual", Float.toString(valores[4]), "1"));
            categories.add(new Category("#ffa600", "Envases", "Mas de lo usual", Float.toString(valores[5]), "1"));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);
        Bundle bundle = getArguments();
        int ID_usuario = bundle.getInt("id_usuario");
        categories = new ArrayList<>();
        circleGraphView = view.findViewById(R.id.circleGraphView);

        recyclerCategory = view.findViewById(R.id.catRecyclerView);
        recyclerCategory.setLayoutManager(new LinearLayoutManager(getContext()));

        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        System.out.println(databaseHelper.getListaPlasticos(ID_usuario).toString());
        float valores[] = arregloValores(databaseHelper.getListaPlasticos(ID_usuario));
        System.out.println(Arrays.toString(valores));
        fillList(valores);
        circleGraphView.setValues(valores);

        CategoryAdapter adapter = new CategoryAdapter(categories);
        recyclerCategory.setAdapter(adapter);
        return view;
    }

    public static float[] arregloValores(ArrayList<Plastico> lista) {
        Iterator<Plastico> i = lista.iterator();
        float valores[] = new float[6];
        while(i.hasNext()) {
            Plastico p = i.next();
            switch (p.getTipo()) {
                case "Bolsas":
                    valores[0] += p.getPeso();
                    break;
                case "Botellas":
                    valores[1] += p.getPeso();
                    break;
                case "Tecnopor":
                    valores[2] += p.getPeso();
                    break;
                case "Empaques":
                    valores[3] += p.getPeso();
                    break;
                case "PVC":
                    valores[4] += p.getPeso();
                    break;
                case "Envase":
                    valores[5] += p.getPeso();
                    break;
            }
        }
        return valores;
    }

}