
package com.example.meuapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adapter para o RecyclerView que exibe a lista de produtos.
 */
public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder> {

    private List<Produto> produtos;

    public ProdutoAdapter(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Cria a view para cada item da lista
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_2, parent, false);
        return new ProdutoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {
        // Popula a view com os dados do produto
        Produto produto = produtos.get(position);
        holder.text1.setText(produto.getNome());
        holder.text2.setText("Preço: " + produto.getPreco());
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    // ViewHolder para cada item da lista
    static class ProdutoViewHolder extends RecyclerView.ViewHolder {
        TextView text1;
        TextView text2;

        public ProdutoViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(android.R.id.text1);
            text2 = itemView.findViewById(android.R.id.text2);
        }
    }
}
