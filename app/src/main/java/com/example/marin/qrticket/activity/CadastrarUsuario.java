package com.example.marin.qrticket.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.marin.qrticket.R;

public class CadastrarUsuario extends AppCompatActivity implements View.OnClickListener {

    private EditText nome;
    private EditText login;
    private EditText senha;
    private static final int perfil = 1;
    private Button btnInserirUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        nome = (EditText) findViewById(R.id.edtCadUsuarioNome);
        login = (EditText) findViewById(R.id.edtCadUsuarioLogin);
        senha = (EditText) findViewById(R.id.edtCadUsuarioSenha);

        btnInserirUsuario.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
