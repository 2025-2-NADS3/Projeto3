package com.example.meuapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsActivity extends AppCompatActivity {

    public static final String EXTRA_QUERY = "query";
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        dbHelper = new DatabaseHelper(this);

        Toolbar toolbar = findViewById(R.id.toolbar_search);
        setSupportActionBar(toolbar);

        String query = getIntent().getStringExtra(EXTRA_QUERY);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Resultados para: \"" + query + "\"");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        RecyclerView rvSearchResults = findViewById(R.id.rv_search_results);
        rvSearchResults.setLayoutManager(new GridLayoutManager(this, 2));

        // Busca todos os produtos e filtra pelo nome de forma segura
        List<Produto> allProducts = dbHelper.getAllProdutos();
        List<Produto> filteredList = new ArrayList<>();
        if (query != null && !query.trim().isEmpty()) {
            String lowerCaseQuery = query.toLowerCase();
            for (Produto produto : allProducts) {
                // Blindagem: Verifica se o nome do produto não é nulo antes de comparar
                if (produto.getNome() != null && produto.getNome().toLowerCase().contains(lowerCaseQuery)) {
                    filteredList.add(produto);
                }
            }
        }

        ProductAdapter adapter = new ProductAdapter(filteredList);
        rvSearchResults.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
