package com.example.marin.qrticket.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.marin.qrticket.R;
import com.example.marin.qrticket.model.Usuario;
import com.example.marin.qrticket.util.RetrofitUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnLogin;
    private EditText edtLogin;
    private EditText edtSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btnLogar);
        edtLogin = (EditText) findViewById(R.id.edtLog);
        edtSenha = (EditText) findViewById(R.id.edtSen);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnLogar){
            String login = edtLogin.getText().toString();
            String senha = edtSenha.getText().toString();

            final RetrofitUtil retrofitUtil = RetrofitUtil.retrofit.create(RetrofitUtil.class);
            final Call<Usuario> call = retrofitUtil.logar(login, senha);

            call.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {

                }
            });
        }
    }
}
