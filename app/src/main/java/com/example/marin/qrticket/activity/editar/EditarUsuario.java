package com.example.marin.qrticket.activity.editar;

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
import com.example.marin.qrticket.activity.cadastrar.CadastroUsuario;
import com.example.marin.qrticket.model.Usuario;
import com.example.marin.qrticket.util.RetrofitUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditarUsuario extends AppCompatActivity {

    private EditText edtAtNome;
    private EditText edtAtlogin;
    private EditText edtAtSenha;
    private static final int perfil = 1;
    private static final int flag = 1;
    private static final int REDIRECT = 200;
    private EditText edtAtEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);

        edtAtNome = (EditText) findViewById(R.id.edtAtUsuarioNome);
        edtAtlogin = (EditText) findViewById(R.id.edtAtUsuarioLogin);
        edtAtSenha = (EditText) findViewById(R.id.edtAtUsuarioSenha);
        edtAtEmail = (EditText) findViewById(R.id.edtAtUsuarioEmail);

        //Recebendo o objeto usuario que vem de outra activity e armazenando em um novo objeto usuario
        Usuario u = (Usuario) getIntent().getSerializableExtra("usuario");
        final int id = u.getId();

        final RetrofitUtil retrofitUtil = RetrofitUtil.retrofit.create(RetrofitUtil.class);
        final Call<Usuario> call = retrofitUtil.pegarIdUsuario(id);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()){
                    Usuario u = response.body();
                    edtAtNome.setText(u.getNome());
                    edtAtEmail.setText(u.getEmail());
                    edtAtlogin.setText(u.getLogin());
                    edtAtSenha.setText(u.getSenha());
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Erro", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnAtUsuario = (Button) findViewById(R.id.btnAtUsuario);
        btnAtUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Usuario usuario = new Usuario();
                usuario.setId(id);
                usuario.setNome(edtAtNome.getText().toString());
                usuario.setEmail(edtAtEmail.getText().toString());
                usuario.setLogin(edtAtlogin.getText().toString());
                usuario.setSenha(edtAtSenha.getText().toString());
                usuario.setPerfil(perfil);
                usuario.setFlagAtivo(flag);

                Call<Void> call = retrofitUtil.editarUsuario(usuario);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful()){
                            if (response.code() == 201){
                                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                                alert.setMessage("Informações alteradas com sucesso. Por favor, faça login novamente");
                                alert.setPositiveButton("Ok",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,
                                                                int whichButton) {
                                                dialog.dismiss();
                                                Intent intent = new Intent(EditarUsuario.this, LoginActivity.class);
                                                //Abre a activity
                                                startActivityForResult(intent, REDIRECT);
                                            }
                                        });
                                alert.show();
                            }else if (response.code() == 500){
                                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                                alert.setMessage("Erro ao atualizar, verifique as informações dos campos e tente novamente");
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
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                        alert.setMessage("Erro de sistema, verifique a sua conexão e tente novamente.");
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
            }
        });


        Button btnCancel = (Button) findViewById(R.id.btnCancelar);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
