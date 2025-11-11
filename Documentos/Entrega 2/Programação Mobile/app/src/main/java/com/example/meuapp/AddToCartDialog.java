package com.example.meuapp;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddToCartDialog extends DialogFragment {

    private static final String ARG_NOME = "nome";
    private static final String ARG_DESCRICAO = "descricao";
    private static final String ARG_PRECO = "preco";
    private static final String ARG_IMAGEM = "imagem";

    public static AddToCartDialog newInstance(String nome, String descricao, String preco, int imagem) {
        AddToCartDialog fragment = new AddToCartDialog();
        Bundle args = new Bundle();
        args.putString(ARG_NOME, nome);
        args.putString(ARG_DESCRICAO, "Refrigerante " + nome + " original 600 ml."); // Custom description for dialog
        args.putString(ARG_PRECO, preco);
        args.putInt(ARG_IMAGEM, imagem);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_add_to_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView ivClose = view.findViewById(R.id.iv_close);
        ImageView ivBebidaDialog = view.findViewById(R.id.iv_bebida_dialog);
        TextView tvNomeBebidaDialog = view.findViewById(R.id.tv_nome_bebida_dialog);
        TextView tvDescricaoBebidaDialog = view.findViewById(R.id.tv_descricao_bebida_dialog);
        Button btnAdicionar = view.findViewById(R.id.btn_adicionar);

        if (getArguments() != null) {
            tvNomeBebidaDialog.setText(getArguments().getString(ARG_NOME));
            tvDescricaoBebidaDialog.setText(getArguments().getString(ARG_DESCRICAO));
            ivBebidaDialog.setImageResource(getArguments().getInt(ARG_IMAGEM));
        }

        ivClose.setOnClickListener(v -> dismiss());
        btnAdicionar.setOnClickListener(v -> {
            if (getArguments() != null) {
                String nome = getArguments().getString(ARG_NOME);
                String preco = getArguments().getString(ARG_PRECO);
                int imagem = getArguments().getInt(ARG_IMAGEM);
                
                CarrinhoItem item = new CarrinhoItem(nome, preco, imagem);
                CarrinhoManager.getInstance().addItem(item);
            }
            
            dismiss(); // Fecha o diálogo atual

            // Mostra o diálogo de sucesso
            SuccessDialog successDialog = SuccessDialog.newInstance();
            successDialog.show(getParentFragmentManager(), "success_dialog");
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }
    }
}
