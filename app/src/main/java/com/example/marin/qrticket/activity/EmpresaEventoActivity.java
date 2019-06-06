package com.example.marin.qrticket.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.marin.qrticket.R;
import com.example.marin.qrticket.adapter.EventoAdapter;
import com.example.marin.qrticket.model.Empresa;
import com.example.marin.qrticket.model.Evento;
import com.example.marin.qrticket.util.RetrofitUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmpresaEventoActivity extends AppCompatActivity {

    private static final int REDIRECT = 200;
    Empresa empresa = new Empresa();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa_evento);
    }

    @Override
    protected void onStart() {

        empresa = (Empresa) getIntent().getSerializableExtra("empresa");
        int id = empresa.getId();

        final ListView listar = (ListView) findViewById(R.id.listaEventoEmpresa);
        RetrofitUtil retrofitUtil = RetrofitUtil.retrofit.create(RetrofitUtil.class);
        final Call<List<Evento>> call = retrofitUtil.listarEventosEmpresa(id);
        call.enqueue(new Callback<List<Evento>>() {
            @Override
            public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {
                final List<Evento> listarEventos = response.body();
                if (listarEventos != null){
                    EventoAdapter adapter = new EventoAdapter(getBaseContext(), listarEventos);
                    listar.setAdapter(adapter);
                    listar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(EmpresaEventoActivity.this, QrCodeActivity.class);
                            startActivityForResult(intent, REDIRECT);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Erro", Toast.LENGTH_LONG).show();
            }
        });


        super.onStart();
    }
}
