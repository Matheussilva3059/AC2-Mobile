package com.example.ac2_mobile_finanas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GastoAdapter adapter;
    private ArrayList<Gasto> listaGastos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerGastos);
        Button btnNovoGasto = findViewById(R.id.btnNovoGasto);
        Button btnResumo = findViewById(R.id.btnResumo);

        listaGastos = DatabaseHelper.getInstance(this).listarGastos();
        adapter = new GastoAdapter(listaGastos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnNovoGasto.setOnClickListener(v -> startActivity(new Intent(this, CadastroGastoActivity.class)));
        btnResumo.setOnClickListener(v -> startActivity(new Intent(this, ResumoActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaGastos.clear();
        listaGastos.addAll(DatabaseHelper.getInstance(this).listarGastos());
        adapter.notifyDataSetChanged();
    }
}
