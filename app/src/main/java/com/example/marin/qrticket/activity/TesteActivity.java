package com.example.marin.qrticket.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marin.qrticket.R;
import com.example.marin.qrticket.model.Evento;
import com.example.marin.qrticket.model.Usuario;
import com.example.marin.qrticket.util.RetrofitUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TesteActivity extends AppCompatActivity {

    private TextView test1;
    private TextView test2;
    private TextView test3;
    private TextView test4;
    private TextView test5;
    private TextView test6;
    private TextView test7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);

        test1 = (TextView) findViewById(R.id.teste1);
        test2 = (TextView) findViewById(R.id.teste2);
        test3 = (TextView) findViewById(R.id.teste3);
        test4 = (TextView) findViewById(R.id.teste4);
        test5 = (TextView) findViewById(R.id.teste5);
        test6 = (TextView) findViewById(R.id.teste6);
        test7 = (TextView) findViewById(R.id.teste7);


        int id = (int) getIntent().getSerializableExtra("id");
        final RetrofitUtil retrofitUtil = RetrofitUtil.retrofit.create(RetrofitUtil.class);
        final Call<Evento> call = retrofitUtil.pegarIdEvento(id);
        call.enqueue(new Callback<Evento>() {
            @Override
            public void onResponse(Call<Evento> call, Response<Evento> response) {
                if (response.isSuccessful()){
                    Evento evento = response.body();
                    test1.setText("Nome do Evento: " + evento.getNome() + "\n");
                    test2.setText("Capacidade de público: " + String.valueOf(evento.getCapacidade()) + "\n");
                    test3.setText("Descrição do Evento: " + evento.getDescricao() + "\n");

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                    Date dataEntrada = evento.getData();

                    try {
                        dataEntrada = sdf.parse(String.valueOf(evento.getData()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String dataFormatada = new SimpleDateFormat("dd-MM-yyyy").format(dataEntrada);
                    test4.setText("Data de realização: " + String.valueOf(dataFormatada) + "\n");

                    test5.setText("Horario de inicio do Evento:" + String.valueOf(evento.getHora_inicio()) + "\n");
                    test6.setText("Horario de encerramento do Evento: " + String.valueOf(evento.getHora_fim()) + "\n");

                    Date dataEntrada2 = evento.getData_devolucao();

                    try {
                        dataEntrada = sdf.parse(String.valueOf(evento.getData_devolucao()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String dataFormatada2 = new SimpleDateFormat("dd-MM-yyyy").format(dataEntrada2);
                    test7.setText("Data limite para devolução: " + String.valueOf(dataFormatada2) + "\n");


                }
            }

            @Override
            public void onFailure(Call<Evento> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Erro ao atualizar", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
