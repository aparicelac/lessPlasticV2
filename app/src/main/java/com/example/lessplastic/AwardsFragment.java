package com.example.lessplastic;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AwardsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AwardsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerLogros;
    List<Logros> logrosList;
    TextView txtTotal;
    TextView txtNombre;


    public AwardsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AwardsFragment newInstance(String param1, String param2) {
        AwardsFragment fragment = new AwardsFragment();
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

    public void fillList(){
        logrosList.add(new Logros("#775447", "Primera Semana", "5 dias consecutivos en la app", "En progreso"));
        logrosList.add(new Logros("#607d8b", "Primera Semana", "5 dias consecutivos en la app", "Completado"));
        logrosList.add(new Logros("#03a9f4", "Primera Semana", "5 dias consecutivos en la app", "En Progreso"));
        logrosList.add(new Logros("#775447", "Primera Semana", "5 dias consecutivos en la app", "Completado"));
        logrosList.add(new Logros("#f44336", "Primera Semana", "5 dias consecutivos en la app", "En progreso"));
        logrosList.add(new Logros("#009688", "Primera Semana", "5 dias consecutivos en la app", "En progreso"));

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_awards, container, false);

        logrosList = new ArrayList<>();
        Bundle bundle = getArguments();
        int ID_usuario = bundle.getInt("id_usuario");
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        ArrayList<Plastico> listaPlast = databaseHelper.getListaPlasticos(ID_usuario);
        txtTotal = view.findViewById(R.id.totalGramsRegTextView);
        txtTotal.setText(Float.toString(countPlasticos(listaPlast)));
        txtNombre = view.findViewById(R.id.awardsUsurnameTextView);
        txtNombre.setText(databaseHelper.getNombreUsuario(ID_usuario));
        recyclerLogros = view.findViewById(R.id.logrosRecyclerView);
        recyclerLogros.setLayoutManager(new LinearLayoutManager(getContext()));

        fillList();

        LogrosAdapter adapter = new LogrosAdapter(logrosList);
        recyclerLogros.setAdapter(adapter);

        return view;
    }

    public static float countPlasticos(ArrayList<Plastico> lista) {
        Iterator<Plastico> i = lista.iterator();
        float sum = 0;
        while(i.hasNext()) {
            Plastico p = i.next();
            sum += p.getPeso();
        }
        return sum;
    }
}
