package com.example.lessplastic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.lessplastic.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int ID_usuario = getIntent().getIntExtra("id_usuario", 0);
        Toast.makeText(MainActivity.this, String.valueOf(ID_usuario), Toast.LENGTH_SHORT).show();

        replaceFragment(new StatsFragment(), ID_usuario);
        binding.bottomNavigationView.setBackground(null);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.estadisticas:
                    replaceFragment(new StatsFragment(), ID_usuario);
                    break;

                case R.id.calendario:
                    replaceFragment(new CalendarFragment(), ID_usuario);
                    break;

                case R.id.logros:
                    replaceFragment(new AwardsFragment(), ID_usuario);
                    break;

                case R.id.perfil:
                    replaceFragment(new ProfileFragment(), ID_usuario);
                    break;
            }
            return true;
        });

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new FormTipoFragment(), ID_usuario);
            }
        });

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
    private void replaceFragment(Fragment fragment, int id) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putInt("id_usuario", id);
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

}

