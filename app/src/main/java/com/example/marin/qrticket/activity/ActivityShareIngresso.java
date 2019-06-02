package com.example.marin.qrticket.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.marin.qrticket.R;
import com.example.marin.qrticket.adapter.IngressoUsuarioAdapter;
import com.example.marin.qrticket.model.IngressoUsuario;
import com.example.marin.qrticket.model.Usuario;
import com.example.marin.qrticket.util.RetrofitUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityShareIngresso extends AppCompatActivity {

    private static final int REDIRECT = 200;
    Usuario user = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_ingresso);
    }

    @Override
    protected void onStart() {

        user = (Usuario) getIntent().getSerializableExtra("usuario");
        int id = user.getId();

        final ListView listar = (ListView) findViewById(R.id.listaIngressoShare);
        RetrofitUtil retrofitUtil = RetrofitUtil.retrofit.create(RetrofitUtil.class);
        final Call<List<IngressoUsuario>> call = retrofitUtil.pegarIngressoUsuario(id);
        call.enqueue(new Callback<List<IngressoUsuario>>() {
            @Override

            public void onResponse(Call<List<IngressoUsuario>> call, Response<List<IngressoUsuario>> response) {
                final List<IngressoUsuario> listarIngressos = response.body();
                final IngressoUsuarioAdapter adapter = new IngressoUsuarioAdapter(getBaseContext(), listarIngressos);
                listar.setAdapter(adapter);

                listar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        IngressoUsuario ig = new IngressoUsuario();
                        ig = listarIngressos.get(i);
                        Intent intent = new Intent(ActivityShareIngresso.this, SelecionarUsuarioShareActivity.class);
                        intent.putExtra("ingresso", ig);
                        startActivityForResult(intent, REDIRECT);

                    }
                });
            }

            @Override
            public void onFailure(Call<List<IngressoUsuario>> call, Throwable t) {
                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        super.onStart();
    }
}
