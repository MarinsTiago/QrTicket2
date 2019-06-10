package com.example.marin.qrticket.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marin.qrticket.R;
import com.example.marin.qrticket.model.IngressoUsuario;
import com.example.marin.qrticket.model.UsuarioShare;
import com.example.marin.qrticket.util.RetrofitUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelecionarUsuarioShareActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEmail;
    private Button btnShare;
    private Button btnShareBack;
    IngressoUsuario i = new IngressoUsuario();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_usuario_share);

        edtEmail = (EditText) findViewById(R.id.edtEmailShare);
        btnShare = (Button) findViewById(R.id.btnShare);
        btnShareBack = (Button) findViewById(R.id.btnShareBack);

        btnShare.setOnClickListener(this);
        btnShareBack.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {


        if (view.getId() == R.id.btnShare){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            final AlertDialog.Builder alert2 = new AlertDialog.Builder(this);

            alert.setMessage("Deseja realmente compartilhar este ingresso?");
            alert.setPositiveButton("Compartilhar",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int whichButton) {

                            i = (IngressoUsuario) getIntent().getSerializableExtra("ingresso");
                            int idEvento = i.getId();
                            String email = edtEmail.getText().toString();
                            UsuarioShare us = new UsuarioShare();
                            us.setId(idEvento);
                            us.setEmail(email);
                            RetrofitUtil retrofitUtil = RetrofitUtil.retrofit.create(RetrofitUtil.class);
                            final Call<Void> call = retrofitUtil.trasnserir(us);
                            call.enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                   if (response.code() == 200){
                                       alert2.setMessage("Ingresso Compartilhado com sucesso!");
                                       alert2.setPositiveButton("Ok",
                                               new DialogInterface.OnClickListener() {
                                                   public void onClick(DialogInterface dialog,
                                                                       int whichButton) {
                                                       dialog.dismiss();
                                                       finish();
                                                   }
                                               });
                                       alert2.show();

                                   }else if (response.code() == 500){
                                       alert2.setMessage("Email inválido, tente novamente");
                                       alert2.setPositiveButton("Ok",
                                               new DialogInterface.OnClickListener() {
                                                   public void onClick(DialogInterface dialog,
                                                                       int whichButton) {
                                                       dialog.dismiss();
                                                   }
                                               });
                                       alert2.show();
                                   }
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {
                                    alert2.setMessage("Erro de sistema");
                                    alert2.setPositiveButton("Ok",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog,
                                                                    int whichButton) {
                                                    dialog.dismiss();
                                                    finish();
                                                }
                                            });
                                    alert2.show();
                                }
                            });

                        }
                    }
            );

            alert.setNegativeButton("Cancelar",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int whichButton) {
                            dialog.dismiss();
                        }
                    });
            alert.show();
        }else if (view.getId() == R.id.btnShareBack){
            finish();
        }

    }
}
