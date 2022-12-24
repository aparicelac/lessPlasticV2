package com.example.lessplastic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private TextView txtRegistro;
    private EditText edtUsuario;
    private EditText edtPassword;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtRegistro = findViewById(R.id.txtRegistro);
        edtUsuario = findViewById(R.id.edtUsuario);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        txtRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterFragment registro = new RegisterFragment();
                registro.show(getSupportFragmentManager(), "Fragment de Registro");
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = edtUsuario.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if(usuario.isEmpty() || password.isEmpty())
                    Toast.makeText(LoginActivity.this, "Ingresar todos los datos", Toast.LENGTH_SHORT).show();
                else {
                    DatabaseHelper databaseHelper = new DatabaseHelper(LoginActivity.this);
                    int checkUsuario = databaseHelper.checkUsuario(usuario, password);

                    if(checkUsuario != -1) {
                        Toast.makeText(LoginActivity.this, "Datos correctos", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("id_usuario", checkUsuario);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}