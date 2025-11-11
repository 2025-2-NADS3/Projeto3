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

public class PaymentMethodAdapter extends RecyclerView.Adapter<PaymentMethodAdapter.PaymentMethodViewHolder> {

    public interface OnPaymentMethodClickListener {
        void onPaymentMethodClick(PaymentMethod paymentMethod);
    }

    private List<PaymentMethod> paymentMethodList;
    private OnPaymentMethodClickListener listener;

    public PaymentMethodAdapter(List<PaymentMethod> paymentMethodList, OnPaymentMethodClickListener listener) {
        this.paymentMethodList = paymentMethodList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PaymentMethodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_payment_method, parent, false);
        return new PaymentMethodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentMethodViewHolder holder, int position) {
        PaymentMethod paymentMethod = paymentMethodList.get(position);
        holder.bind(paymentMethod, listener);
    }

    @Override
    public int getItemCount() {
        return paymentMethodList.size();
    }

    static class PaymentMethodViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPaymentIcon;
        TextView tvPaymentName;
        Button btnSelecionar;

        public PaymentMethodViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPaymentIcon = itemView.findViewById(R.id.iv_payment_icon);
            tvPaymentName = itemView.findViewById(R.id.tv_payment_name);
            btnSelecionar = itemView.findViewById(R.id.btn_selecionar_pagamento);
        }

        public void bind(final PaymentMethod paymentMethod, final OnPaymentMethodClickListener listener) {
            // **CORREÇÃO APLICADA AQUI**
            ivPaymentIcon.setImageResource(paymentMethod.getIconResId()); // Usando o nome correto do método
            tvPaymentName.setText(paymentMethod.getName());
            btnSelecionar.setOnClickListener(v -> listener.onPaymentMethodClick(paymentMethod));
        }
    }
}
