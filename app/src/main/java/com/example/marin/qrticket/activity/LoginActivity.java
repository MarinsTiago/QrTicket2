package com.example.marin.qrticket.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marin.qrticket.R;
import com.example.marin.qrticket.activity.cadastrar.CadastroUsuario;
import com.example.marin.qrticket.model.Usuario;
import com.example.marin.qrticket.util.RetrofitUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnLogar;
    private Button btnNC;
    private EditText edtLogin;
    private EditText edtSenha;
    private static final int REDIRECT = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogar = (Button) findViewById(R.id.btnLogar);
        edtLogin = (EditText) findViewById(R.id.edtLog);
        edtSenha = (EditText) findViewById(R.id.edtSen);
        btnNC = (Button) findViewById(R.id.btnNovaConta);

        btnLogar.setOnClickListener(this);
        btnNC.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnLogar){

            String login = edtLogin.getText().toString();
            String senha = edtSenha.getText().toString();

            Usuario u = new Usuario();
            u.setLogin(login);
            u.setSenha(senha);

            //cria instancia da classe necessária para a comunicação android X webService
            final RetrofitUtil retrofitUtil = RetrofitUtil.retrofit.create(RetrofitUtil.class);

            //Pega as informações dos editTexts e manda pro web service fazer a consulta através da biblioteca retrofit
            final Call<Usuario> call = retrofitUtil.logar(u);

            //coloca na 'fila' para executar o processo
            call.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    //response contem a resposta da execução do processo e o objeto tbm está contido nele, no seu body
                    if (response.isSuccessful()){
                        if (response.body().getLogin() != null){
                        Usuario ui = new Usuario();

                        //pegando o objeto que retorna  da consulta
                        ui = response.body();
                        int x = ui.getPerfil();

                        //condicional para saber o tipo de usuario e redirecionar para a tela de cada usuario
                        if (x == 1){
                            //Mostra o caminho que acontecerá o fluxo de informações, primeira tela é a que
                            //estamos(LoginActivity.class) e a segunda
                            //é onde será levada as informações (UsuarioActivity.class) e posteriormente renderizada a tela
                            Intent intent = new Intent(LoginActivity.this, UsuarioActivity.class);

                            //Passa o objeto usuario para outra activity(tela), como se fosse uma espécie de sessão
                            intent.putExtra("usuario", ui);

                            //Abre a activity
                            startActivityForResult(intent, REDIRECT);
                        }else if (x == 2){
                            Intent intent = new Intent(LoginActivity.this, QrCodeActivity.class);

                            //Passa o objeto usuario para outra activity(tela), como se fosse uma espécie de sessão
                            intent.putExtra("usuario", ui);

                            //Abre a activity
                            startActivityForResult(intent, REDIRECT);
                            }else{
                            Toast.makeText(getBaseContext(), "Usuario inválido", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                        t.printStackTrace();
                }
            });
        }else if (view.getId() == R.id.btnNovaConta){
            Intent intent = new Intent(LoginActivity.this, CadastroUsuario.class);
            //teste para leitura do QrCode
            //Intent intent = new Intent(LoginActivity.this, QrCodeActivity.class);

            startActivityForResult(intent, REDIRECT);
        }
    }
}
