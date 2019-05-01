package com.example.marin.qrticket.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marin.qrticket.R;
import com.example.marin.qrticket.model.Evento;
import com.example.marin.qrticket.model.Usuario;
import com.example.marin.qrticket.util.RetrofitUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TesteActivity extends AppCompatActivity {

    private EditText test1;
    private EditText test2;
    private EditText test3;
    private EditText test4;
    private EditText test5;
    private EditText test6;
    private EditText test7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);

        test1 = (EditText) findViewById(R.id.teste1);
        test2 = (EditText) findViewById(R.id.teste2);
        test3 = (EditText) findViewById(R.id.teste3);
        test4 = (EditText) findViewById(R.id.teste4);
        test5 = (EditText) findViewById(R.id.teste5);
        test6 = (EditText) findViewById(R.id.teste6);
        test7 = (EditText) findViewById(R.id.teste7);


        int id = (int) getIntent().getSerializableExtra("id");
        final RetrofitUtil retrofitUtil = RetrofitUtil.retrofit.create(RetrofitUtil.class);
        final Call<Evento> call = retrofitUtil.pegarIdEvento(id);
        call.enqueue(new Callback<Evento>() {
            @Override
            public void onResponse(Call<Evento> call, Response<Evento> response) {
                if (response.isSuccessful()){
                    Evento evento = response.body();
                    test1.setText(evento.getNome());
                    test2.setText(String.valueOf(evento.getCapacidade()));
                    test3.setText(evento.getDescricao());
                    test4.setText(String.valueOf(evento.getData()));
                    test5.setText(String.valueOf(evento.getHoraInicio()));
                    test6.setText(String.valueOf(evento.getHoraFim()));
                    test7.setText(String.valueOf(evento.getDataDevolucao()));

                }
            }

            @Override
            public void onFailure(Call<Evento> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Erro ao atualizar", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
