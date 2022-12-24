package com.example.lessplastic;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class FormTipoFragment extends DialogFragment {

    ImageButton btnBag, btnBottle, btnTechnopor, btnWrap, btnPVC, btnContainer;
    public static FormTipoFragment newInstance(String param1, String param2) {
        FormTipoFragment fragment = new FormTipoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_form_tipo, container, false);
        Bundle bundle = getArguments();
        final int ID_usuario = bundle.getInt("id_usuario");

        btnBag = (ImageButton) v.findViewById(R.id.btnBag);
        btnBottle = (ImageButton) v.findViewById(R.id.btnBottle);
        btnTechnopor = (ImageButton) v.findViewById(R.id.btnTechno);
        btnWrap = (ImageButton) v.findViewById(R.id.btnWrap);
        btnPVC = (ImageButton) v.findViewById(R.id.btnPVC);
        btnContainer = (ImageButton) v.findViewById(R.id.btnContainers);

        btnBag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new Form1Fragment("bag"), ID_usuario, "Bolsas");
            }
        });
        btnBottle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new Form2Fragment("bottle"), ID_usuario, "Botellas");
            }
        });
        btnTechnopor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new Form1Fragment("technopor"), ID_usuario, "Tecnopor");
            }
        });
        btnWrap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new Form2Fragment("wrap"), ID_usuario, "Empaques");
            }
        });
        btnPVC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new Form2Fragment("PVC"), ID_usuario, "PVC");
            }
        });
        btnContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new Form1Fragment("container"), ID_usuario, "Envases");
            }
        });
        return v;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
    private void replaceFragment(Fragment fragment, int id, String tipo) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        Plastico plastico = new Plastico(id);
        plastico.setTipo(tipo);
        bundle.putSerializable("plastico", plastico);
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}