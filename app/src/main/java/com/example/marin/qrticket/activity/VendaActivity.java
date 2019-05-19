package com.example.marin.qrticket.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
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

public class VendaActivity extends AppCompatActivity {

    //Tela para montar a inserir a venda, falta pegar algumas informações necessárias

    private TextView txtNome;
    private TextView txtCapacidade;
    private TextView txtDescricao;
    private TextView txtInicio;
    private TextView txtData;
    private TextView txtDataDev;
    private TextView txtFim;
    Evento evento = new Evento();
    Usuario user = new Usuario();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venda);

        final NumberPicker np = (NumberPicker) findViewById(R.id.npCompra);
        txtNome = (TextView) findViewById(R.id.txtNomeCompra);
        txtCapacidade = (TextView) findViewById(R.id.txtCapacidadeCompra);
        txtDescricao = (TextView) findViewById(R.id.txtDescricaoCompra);
        txtData = (TextView) findViewById(R.id.txtDataCompra);
        txtInicio = (TextView) findViewById(R.id.txtHInicioCompra);
        txtFim = (TextView) findViewById(R.id.txtHFimCompra);
        txtDataDev = (TextView) findViewById(R.id.txtDataDevCompra);

        evento = (Evento) getIntent().getSerializableExtra("evento");
        int id = evento.getId();
        final RetrofitUtil retrofitUtil = RetrofitUtil.retrofit.create(RetrofitUtil.class);
        final Call<Evento> call = retrofitUtil.pegarIdEvento(id);
        call.enqueue(new Callback<Evento>() {
            @Override
            public void onResponse(Call<Evento> call, Response<Evento> response) {
                final Evento evento = response.body();
                txtNome.setText("Nome do Evento: " + evento.getNome() + "\n");
                txtCapacidade.setText("Capacidade de público: " + String.valueOf(evento.getCapacidade()) + "\n");
                txtDescricao.setText("Descrição do Evento: " + evento.getDescricao() + "\n");

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                Date dataEntrada = evento.getData();

                try {
                    dataEntrada = sdf.parse(String.valueOf(evento.getData()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String dataFormatada = new SimpleDateFormat("dd-MM-yyyy").format(dataEntrada);
                txtData.setText("Data de realização: " + String.valueOf(dataFormatada) + "\n");

                txtInicio.setText("Horario de inicio do Evento:" + String.valueOf(evento.getHora_inicio()) + "\n");
                txtFim.setText("Horario de encerramento do Evento: " + String.valueOf(evento.getHora_fim()) + "\n");

                Date dataEntrada2 = evento.getData_devolucao();

                try {
                    dataEntrada = sdf.parse(String.valueOf(evento.getData_devolucao()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String dataFormatada2 = new SimpleDateFormat("dd-MM-yyyy").format(dataEntrada2);
                txtDataDev.setText("Data limite para devolução: " + String.valueOf(dataFormatada2) + "\n");

                //pegando usuario que vem de outra activity
                user = (Usuario) getIntent().getSerializableExtra("usuario");


                //Select para escolher a quantidade de ingressos
                np.setMinValue(0);
                np.setMaxValue(10);
                np.setWrapSelectorWheel(true);
                np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                        //fazer algo com a quantidade escolhida
                    }
                });
            }

            @Override
            public void onFailure(Call<Evento> call, Throwable t) {

            }
        });
        //botão para cancelar e voltar para a tela anterior
        Button btnCancel = (Button) findViewById(R.id.btnCancelarCompra);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
