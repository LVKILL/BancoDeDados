package com.example.professor.bancodedados.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.professor.bancodedados.R;
import com.example.professor.bancodedados.data.Produto;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProdutoAdapter extends BaseAdapter {
    private Context context;
    private static final NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("PT","BR"));
    private List<Produto> produtos = new ArrayList<>();

    public ProdutoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return produtos.size();
    }

    @Override
    public Produto getItem(int position) {
        return produtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return produtos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_listprodutos, parent, false);

        TextView txtNome = view.findViewById(R.id.txt_nome);
        TextView txtValor = view.findViewById(R.id.txt_valor);

        Produto produto = produtos.get(position);
        txtNome.setText(produto.getNome());
        txtValor.setText(nf.format(produto.getValor()));

        return view;
    }

    public void setItems(List<Produto> produtos) {
        this.produtos = produtos;
        notifyDataSetChanged();
    }

}