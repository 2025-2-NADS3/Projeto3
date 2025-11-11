package com.example.meuapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Produto> productList;

    public ProductAdapter(List<Produto> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Produto produto = productList.get(position);

        holder.tvFoodName.setText(produto.getNome());
        holder.tvFoodPrice.setText(produto.getPreco());

        // Define a imagem específica do produto.
        // Se o ID da imagem for 0 ou inválido, ele não vai dar crash, mas não mostrará imagem.
        if (produto.getImagemId() != 0) {
            holder.ivFoodImage.setImageResource(produto.getImagemId());
        } else {
            // Opcional: Define uma imagem de placeholder se nenhuma for encontrada.
            holder.ivFoodImage.setImageResource(android.R.drawable.ic_menu_gallery); 
        }

        holder.itemView.setOnClickListener(v -> {
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            AddToCartDialog dialog = AddToCartDialog.newInstance(
                    produto.getNome(),
                    produto.getDescricao(),
                    produto.getPreco(),
                    produto.getImagemId()
            );
            dialog.show(activity.getSupportFragmentManager(), "add_to_cart_dialog");
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFoodImage;
        TextView tvFoodName;
        TextView tvFoodPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFoodImage = itemView.findViewById(R.id.iv_food_image);
            tvFoodName = itemView.findViewById(R.id.tv_food_name);
            tvFoodPrice = itemView.findViewById(R.id.tv_food_price);
        }
    }
}
