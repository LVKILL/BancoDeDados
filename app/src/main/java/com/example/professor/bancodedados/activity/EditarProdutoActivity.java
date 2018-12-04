package com.example.professor.bancodedados.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.professor.bancodedados.R;
import com.example.professor.bancodedados.data.Produto;
import com.example.professor.bancodedados.data.ProdutoDAO;

public class EditarProdutoActivity extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtValor;
    private Produto produto;
    private ProdutoDAO produtoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_produto);

        edtNome = (EditText) findViewById(R.id.edt_nome);
        edtValor = (EditText) findViewById(R.id.edt_valor);

        produtoDAO = ProdutoDAO.getInstance(this);

        produto = (Produto) getIntent().getSerializableExtra("produto");

        if (produto != null){
            edtNome.setText(produto.getNome());
            edtValor.setText(String.valueOf(produto.getValor()));
        }
    }

    public void processar(View view){
        String nome = edtNome.getText().toString();
        double valor = Double.parseDouble(edtValor.getText().toString());
        String msg;

        if (produto == null) {
            Produto produto = new Produto(nome, valor);
            produtoDAO.save(produto);
            msg = "Produto gravado com ID = " + produto.getId();

        } else {
            produto.setNome(nome);
            produto.setValor(valor);
            produtoDAO.update(produto);
            msg = "Produto atualizado com ID = " + produto.getId();
        }

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }

    public void cancelar(View view){
        setResult(RESULT_CANCELED);
        finish();
    }
}