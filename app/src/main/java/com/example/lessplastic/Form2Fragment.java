package com.example.lessplastic;

import static java.lang.Integer.parseInt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Form2Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button cancelar;
    Button registrar;

    private String strTipo, strTamaño;
    private EditText edtPeso;
    private Spinner spnCantidad;

    public Form2Fragment(String tipo) {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Form2Fragment newInstance(String param1, String param2) {
        Form2Fragment fragment = new Form2Fragment("");
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_form1, container, false);
        Bundle bundle = getArguments();
        Plastico plastico = (Plastico)bundle.getSerializable("plastico");
        cancelar = vista.findViewById(R.id.btnFormCancelar);
        registrar = vista.findViewById(R.id.btnFormRegistrar);

        edtPeso = vista.findViewById(R.id.edtFormWeight);
        spnCantidad = vista.findViewById(R.id.formSpinner);
        strTamaño = null;

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new FormTipoFragment());
            }
        });



        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //tipo ya está definido al inicio
                String cantidad = spnCantidad.getSelectedItem().toString().trim();
                String peso = edtPeso.getText().toString().trim();

                if ( cantidad.isEmpty() || peso.isEmpty()) {
                    Toast.makeText(getContext(), "Ingresar todos los datos en el formulario", Toast.LENGTH_SHORT).show();
                }
                else {
                    int intCantidad = parseInt(cantidad);
                    System.out.println(intCantidad);
                    float floatPeso = Float.parseFloat(peso);
                    System.out.println(floatPeso);
                    plastico.setCantidad(intCantidad);
                    plastico.setPeso(floatPeso);
                    System.out.println(plastico.toString());


                    DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
                    int id_plastico = databaseHelper.addPlastic(plastico);
                    boolean successRegistro = databaseHelper.addRegistro(plastico.getId(), id_plastico);
                    Toast.makeText(getContext(), "Registrado" + id_plastico, Toast.LENGTH_SHORT).show();

                }


            }
        });

        return vista;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_0:
                strTamaño = "500";
                break;
            case R.id.radio_500:
                strTamaño = "1000";
                break;
            case R.id.radio_1000:
                strTamaño = "1500";
                break;
            case R.id.radio_2000:
                strTamaño = "2000";
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}