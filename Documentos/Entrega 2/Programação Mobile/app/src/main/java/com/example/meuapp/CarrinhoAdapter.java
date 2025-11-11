package com.example.meuapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CarrinhoAdapter extends RecyclerView.Adapter<CarrinhoAdapter.CarrinhoViewHolder> {

    private List<CarrinhoItem> carrinhoItemList;

    public CarrinhoAdapter(List<CarrinhoItem> carrinhoItemList) {
        this.carrinhoItemList = carrinhoItemList;
    }

    @NonNull
    @Override
    public CarrinhoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carrinho, parent, false);
        return new CarrinhoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarrinhoViewHolder holder, int position) {
        CarrinhoItem carrinhoItem = carrinhoItemList.get(position);
        holder.ivProduto.setImageResource(carrinhoItem.getImagem());
        holder.tvNomeProduto.setText(carrinhoItem.getNome());
        holder.tvPrecoProduto.setText(carrinhoItem.getPreco());
        holder.tvQuantidade.setText(String.valueOf(carrinhoItem.getQuantidade()));

        holder.btnAumentar.setOnClickListener(v -> {
            int currentPosition = holder.getAdapterPosition();
            if (currentPosition != RecyclerView.NO_POSITION) {
                CarrinhoItem item = carrinhoItemList.get(currentPosition);
                item.setQuantidade(item.getQuantidade() + 1);
                notifyItemChanged(currentPosition);
            }
        });

        holder.btnDiminuir.setOnClickListener(v -> {
            int currentPosition = holder.getAdapterPosition();
            if (currentPosition != RecyclerView.NO_POSITION) {
                CarrinhoItem item = carrinhoItemList.get(currentPosition);
                if (item.getQuantidade() > 1) {
                    item.setQuantidade(item.getQuantidade() - 1);
                    notifyItemChanged(currentPosition);
                } else {
                    // Se a quantidade for 1, remove o item
                    carrinhoItemList.remove(currentPosition);
                    notifyItemRemoved(currentPosition);
                    notifyItemRangeChanged(currentPosition, carrinhoItemList.size());
                }
            }
        });

        holder.btnRemover.setOnClickListener(v -> {
            int currentPosition = holder.getAdapterPosition();
            if (currentPosition != RecyclerView.NO_POSITION) {
                carrinhoItemList.remove(currentPosition);
                notifyItemRemoved(currentPosition);
                notifyItemRangeChanged(currentPosition, carrinhoItemList.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return carrinhoItemList.size();
    }

    static class CarrinhoViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProduto;
        TextView tvNomeProduto;
        TextView tvPrecoProduto;
        TextView tvQuantidade;
        Button btnAumentar;
        Button btnDiminuir;
        ImageView btnRemover;

        public CarrinhoViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProduto = itemView.findViewById(R.id.iv_produto);
            tvNomeProduto = itemView.findViewById(R.id.tv_nome_produto);
            tvPrecoProduto = itemView.findViewById(R.id.tv_preco_produto);
            tvQuantidade = itemView.findViewById(R.id.tv_quantidade);
            btnAumentar = itemView.findViewById(R.id.btn_aumentar);
            btnDiminuir = itemView.findViewById(R.id.btn_diminuir);
            btnRemover = itemView.findViewById(R.id.btn_remover);
        }
    }
}
