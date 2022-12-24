package com.example.lessplastic;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterFragment extends DialogFragment {

    private EditText edtCorreo, edtNombre, edtApellido, edtNombreUsuario, edtPassword, edtConfPassword;
    private Button btnRegistro;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_register, container, false);

        edtCorreo = v.findViewById(R.id.edtCorreo);
        edtNombre = v.findViewById(R.id.edtNombre);
        edtApellido = v.findViewById(R.id.edtApellidos);
        edtNombreUsuario = v.findViewById(R.id.edtNombreUsuario);
        edtPassword = v.findViewById(R.id.edtPassword);
        edtConfPassword = v.findViewById(R.id.edtConfirmarPassword);
        btnRegistro = v.findViewById(R.id.btnRegistro);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String correo = edtCorreo.getText().toString().trim();
                String nombre = edtNombre.getText().toString().trim();
                String apellido = edtApellido.getText().toString().trim();
                String nombreUsuario = edtNombreUsuario.getText().toString().trim();
                String contraseña = edtPassword.getText().toString().trim();
                String confContra = edtConfPassword.getText().toString().trim();

                if ( correo.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || nombreUsuario.isEmpty() || contraseña.isEmpty() || confContra.isEmpty()) {
                    Toast.makeText(getContext(), "Ingresar todos los datos", Toast.LENGTH_SHORT).show();
                }
                else if (!contraseña.equals(confContra)) {
                    Toast.makeText(getContext(), "Confirmar Contraseña", Toast.LENGTH_SHORT).show();
                }
                else {
                    Usuario usuario = null;
                    try {
                        usuario = new Usuario(-1, nombreUsuario, nombre, apellido, correo, contraseña);
                    }
                    catch (Exception e) {
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                    DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
                    boolean success = databaseHelper.addUsuario(usuario);
                    Toast.makeText(getContext(), "Registrado", Toast.LENGTH_SHORT).show();

                }
            }
        });

        return v;
    }
}