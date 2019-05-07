package com.example.marin.qrticket.activity.cadastrar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marin.qrticket.R;
import com.example.marin.qrticket.activity.LoginActivity;
import com.example.marin.qrticket.activity.UsuarioActivity;
import com.example.marin.qrticket.model.Usuario;
import com.example.marin.qrticket.util.RetrofitUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroUsuario extends AppCompatActivity implements View.OnClickListener {

    private EditText nome;
    private EditText login;
    private EditText senha;
    private static final int perfil = 1;
    private Button btnInserirUsuario;
    private Button btnCancelar;
    private static final int REDIRECT = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        nome = (EditText) findViewById(R.id.edtCadUsuarioNome);
        login = (EditText) findViewById(R.id.edtCadUsuarioLogin);
        senha = (EditText) findViewById(R.id.edtCadUsuarioSenha);
        btnInserirUsuario = (Button) findViewById(R.id.btnInserirUsuario);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);

        btnInserirUsuario.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view.getId() ==  R.id.btnInserirUsuario){
            Usuario u = new Usuario();
            u.setNome(nome.getText().toString());
            u.setLogin(login.getText().toString());
            u.setSenha(senha.getText().toString());
            u.setPerfil(perfil);
            u.setFlagAtivo(DEFAULT_KEYS_DIALER);

            RetrofitUtil retrofitUtil = RetrofitUtil.retrofit.create(RetrofitUtil.class);
            final Call<Void> call = retrofitUtil.inserirUsuario(u);
            call.enqueue(new Callback<Void>() {
                @Override

                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()){

                        Intent intent = new Intent(CadastroUsuario.this, LoginActivity.class);

                        //Abre a activity
                        startActivityForResult(intent, REDIRECT);
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            });
        }else if (view.getId() == R.id.btnCancelar){
            Intent intent = new Intent(CadastroUsuario.this, LoginActivity.class);
            startActivityForResult(intent, REDIRECT);
        }
    }
}
