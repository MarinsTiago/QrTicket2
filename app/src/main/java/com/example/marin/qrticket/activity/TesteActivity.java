package com.example.marin.qrticket.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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


//activity que mostra as informações do evento em detalhes e que possui um botão para redirecionar para
//a tela de compra do ingresso
public class TesteActivity extends AppCompatActivity{

    private TextView test1;
    private TextView test2;
    private TextView test3;
    private TextView test4;
    private TextView test5;
    private TextView test6;
    private TextView test7;
    private static final int REDIRECT = 200;
    Usuario user = new Usuario();
    private TextView teste;

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
        teste = (TextView) findViewById(R.id.teste);

        //pegando o id que vem do clique do evento escolhido e fazendo a busca
        int id = (int) getIntent().getSerializableExtra("id");
        final RetrofitUtil retrofitUtil = RetrofitUtil.retrofit.create(RetrofitUtil.class);
        final Call<Evento> call = retrofitUtil.pegarIdEvento(id);
        call.enqueue(new Callback<Evento>() {
            @Override
            public void onResponse(Call<Evento> call, Response<Evento> response) {
                if (response.isSuccessful()){
                    final Evento evento = response.body();
                    //Formatando dados que aparecerão na tela
                    test1.setText("Nome do Evento: " + evento.getNome() + "\n");
                    test2.setText("Capacidade de público: " + String.valueOf(evento.getCapacidade()) + "\n");
                    test3.setText("Descrição do Evento: " + evento.getDescricao() + "\n");

                    //formatando data
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

                    //pegando o objeto usuario que veio de outra activity e armazenando no objeto USER
                    user = (Usuario) getIntent().getSerializableExtra("usuario");
                   // teste.setText(user.getNome());

                    Button btnCompra = (Button) findViewById(R.id.btnCompra);
                    btnCompra.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(TesteActivity.this, VendaActivity.class);

                            //intent.putExtra("evento", evento);
                            //Passa o objeto usuario para outra activity(tela), como se fosse uma espécie de sessão
                            intent.putExtra("usuario", user);

                            //Abre a activity
                            startActivityForResult(intent, REDIRECT);
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<Evento> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Erro ao atualizar", Toast.LENGTH_SHORT).show();
            }
        });
        //Botão para cancelar a operação e voltar para a tela anterior
        Button btnCancel = (Button) findViewById(R.id.btnCancelarCompraI);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
