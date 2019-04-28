package com.example.marin.qrticket.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.marin.qrticket.R;
import com.example.marin.qrticket.model.Usuario;

public class TesteActivity extends AppCompatActivity {

    private EditText test1;
    private EditText test2;
    private EditText test3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);

        test1 = (EditText) findViewById(R.id.teste1);
        test2 = (EditText) findViewById(R.id.teste2);
        test3 = (EditText) findViewById(R.id.teste3);

        Usuario u = (Usuario) getIntent().getSerializableExtra("usuario");
        test1.setText(u.getNome());
        test2.setText(u.getLogin());
        test3.setText(u.getSenha());


    }
}
