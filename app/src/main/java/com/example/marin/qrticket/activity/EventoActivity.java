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
import com.example.marin.qrticket.model.Evento;
import com.example.marin.qrticket.model.Usuario;
import com.example.marin.qrticket.util.RetrofitUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//Tela que lista todos os eventos
public class EventoActivity extends AppCompatActivity {

    private static final int REDIRECT = 200;
    Usuario user = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);
    }

    @Override
    protected void onStart() {
        super.onStart();
        final ListView listar = (ListView) findViewById(R.id.listaEventos);
        RetrofitUtil retrofitUtil = RetrofitUtil.retrofit.create(RetrofitUtil.class);
        final Call<List<Evento>> call = retrofitUtil.listarEventos();
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
                            Intent intent = new Intent(EventoActivity.this, TesteActivity.class);
                            int e = listarEventos.get(i).getId();
                            //Passando o id do evento escolhido para a tela "TesteActivity'
                            intent.putExtra("id", e);
                            //pegando o objeto usuario que vem de outra activity
                            user = (Usuario) getIntent().getSerializableExtra("usuario");
                            //enviando o objeto usuario para a activity 'TesteActivity'
                            intent.putExtra("usuario", user);
                            startActivityForResult(intent, REDIRECT);

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Erro", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
