package com.example.marin.qrticket.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.marin.qrticket.R;
import com.example.marin.qrticket.adapter.IngressoAdapter;
import com.example.marin.qrticket.model.Evento;
import com.example.marin.qrticket.model.Ingresso;
import com.example.marin.qrticket.model.Usuario;
import com.example.marin.qrticket.util.RetrofitUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IngressoActivity extends AppCompatActivity {

    private static final int REDIRECT = 200;
    Usuario user = new Usuario();
    Evento evento = new Evento();
    Ingresso ingresso = new Ingresso();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresso);
    }

    @Override
    protected void onStart() {

        user = (Usuario) getIntent().getSerializableExtra("usuario");
        evento = (Evento) getIntent().getSerializableExtra("evento");
        int id = evento.getId();
        final ListView listar = (ListView) findViewById(R.id.listaIngresso);
        RetrofitUtil retrofitUtil = RetrofitUtil.retrofit.create(RetrofitUtil.class);
        final Call<List<Ingresso>> call = retrofitUtil.pegarIngressoEvento(id);
        call.enqueue(new Callback<List<Ingresso>>() {
            @Override
            public void onResponse(Call<List<Ingresso>> call, Response<List<Ingresso>> response) {
                final List<Ingresso> listarIngressos = response.body();
                if (listarIngressos != null){
                    final IngressoAdapter adapter = new IngressoAdapter(getBaseContext(), listarIngressos);
                    listar.setAdapter(adapter);
                    listar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            user = (Usuario) getIntent().getSerializableExtra("usuario");
                            evento = (Evento) getIntent().getSerializableExtra("evento");

                            int idIngresso =  listarIngressos.get(i).getId();
                            Intent intent = new Intent(IngressoActivity.this, VendaActivity.class);
                            intent.putExtra("usuario", user);
                            intent.putExtra("evento", evento);
                            intent.putExtra("idIngresso", idIngresso);
                            startActivityForResult(intent, REDIRECT);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Ingresso>> call, Throwable t) {

            }
        });

        super.onStart();
    }
}
