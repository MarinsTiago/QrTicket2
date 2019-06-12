package com.example.marin.qrticket.activity.cadastrar;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marin.qrticket.R;
import com.example.marin.qrticket.activity.LoginActivity;
import com.example.marin.qrticket.activity.UsuarioActivity;
import com.example.marin.qrticket.activity.editar.EditarUsuario;
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
    private EditText email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        nome = (EditText) findViewById(R.id.edtCadUsuarioNome);
        email = (EditText) findViewById(R.id.edtCadUsuarioEmail);
        login = (EditText) findViewById(R.id.edtCadUsuarioLogin);
        senha = (EditText) findViewById(R.id.edtCadUsuarioSenha);
        btnInserirUsuario = (Button) findViewById(R.id.btnInserirUsuario);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);

        btnInserirUsuario.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);
    }

    @Override
    public void onClick(final View view) {

        if(view.getId() ==  R.id.btnInserirUsuario){
            Usuario u = new Usuario();
            u.setNome(nome.getText().toString());
            u.setEmail(email.getText().toString());
            u.setLogin(login.getText().toString());
            u.setSenha(senha.getText().toString());

            RetrofitUtil retrofitUtil = RetrofitUtil.retrofit.create(RetrofitUtil.class);
            final Call<Void> call = retrofitUtil.inserirUsuario(u);
            call.enqueue(new Callback<Void>() {
                @Override

                public void onResponse(Call<Void> call, Response<Void> response) {
                   if (response.isSuccessful()){
                        if (response.code() == 201){

                            AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                            alert.setMessage("Por favor, faça login novamente");
                            alert.setPositiveButton("Ok",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,
                                                            int whichButton) {
                                            dialog.dismiss();
                                            Intent intent = new Intent(CadastroUsuario.this, LoginActivity.class);
                                            //Abre a activity
                                            startActivityForResult(intent, REDIRECT);
                                        }
                                    });
                            alert.show();
                        }else if (response.code() == 500){
                            AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                            alert.setMessage("Não foi possível realizar o cadastro, confira suas informações e tente novamente");
                            alert.setPositiveButton("Ok",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,
                                                            int whichButton) {
                                            dialog.dismiss();
                                        }
                                    });
                            alert.show();
                        }
                    }
                    Toast.makeText(getBaseContext(), String.valueOf(response.message()), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                    alert.setMessage(String.valueOf(t.getMessage()));
                    alert.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int whichButton) {
                                    dialog.dismiss();
                                }
                            });
                    alert.show();
                }
            });
        }else if (view.getId() == R.id.btnCancelar){
            Intent intent = new Intent(CadastroUsuario.this, LoginActivity.class);
            startActivityForResult(intent, REDIRECT);
        }
    }
}
