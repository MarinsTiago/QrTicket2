package com.example.marin.qrticket.activity;

import android.content.DialogInterface;
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
    IngressoUsuario i = new IngressoUsuario();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_usuario_share);

        edtEmail = (EditText) findViewById(R.id.edtEmailShare);
        btnShare = (Button) findViewById(R.id.btnShare);

        btnShare.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);

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
                                Toast.makeText(getBaseContext(), "Ingresso compartilhado", Toast.LENGTH_LONG).show();
                                finish();
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();
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
    }
}
