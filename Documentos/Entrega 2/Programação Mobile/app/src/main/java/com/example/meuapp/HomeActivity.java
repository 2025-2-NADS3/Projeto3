
package com.example.meuapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Activity que exibe os produtos do banco de dados local.
 */
public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private DatabaseHelper dbHelper;
    private ProdutoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.rv_produtos);
        progressBar = findViewById(R.id.progress_bar);
        dbHelper = new DatabaseHelper(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadProductsFromDatabase();
    }

    /**
     * Carrega todos os produtos diretamente do banco de dados e atualiza a UI.
     */
    private void loadProductsFromDatabase() {
        progressBar.setVisibility(View.VISIBLE);
        
        // Busca os dados do banco de dados.
        List<Produto> produtos = dbHelper.getAllProdutos();
        
        // Atualiza a UI com os dados.
        adapter = new ProdutoAdapter(produtos);
        recyclerView.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);

        if (produtos.isEmpty()) {
            Toast.makeText(this, "Nenhum produto encontrado no banco de dados.", Toast.LENGTH_LONG).show();
        }
    }
}
