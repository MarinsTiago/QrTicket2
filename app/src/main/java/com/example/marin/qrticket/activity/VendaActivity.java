package com.example.marin.qrticket.activity;

import android.content.Intent;
import android.provider.Settings;
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
import com.example.marin.qrticket.model.Venda;
import com.example.marin.qrticket.util.RetrofitUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VendaActivity extends AppCompatActivity {


    private TextView txtNome;
    private TextView txtInicio;
    private TextView txtData;
    private TextView txtDataDev;
    private TextView txtFim;
    Evento evento = new Evento();
    Usuario user = new Usuario();
    int idIngresso;
    private static final int REDIRECT = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venda);

        final NumberPicker np = (NumberPicker) findViewById(R.id.npCompra);
        txtNome = (TextView) findViewById(R.id.txtNomeCompra);
        txtData = (TextView) findViewById(R.id.txtDataCompra);
        txtInicio = (TextView) findViewById(R.id.txtHInicioCompra);
        txtFim = (TextView) findViewById(R.id.txtHFimCompra);
        txtDataDev = (TextView) findViewById(R.id.txtDataDevCompra);

        evento = (Evento) getIntent().getSerializableExtra("evento");
        idIngresso = (int) getIntent().getSerializableExtra("idIngresso");
        int id = evento.getId();
        final RetrofitUtil retrofitUtil = RetrofitUtil.retrofit.create(RetrofitUtil.class);
        final Call<Evento> call = retrofitUtil.pegarIdEvento(id);
        call.enqueue(new Callback<Evento>() {
            @Override
            public void onResponse(Call<Evento> call, Response<Evento> response) {
                final Evento evento = response.body();
                txtNome.setText("Nome do Evento: " + evento.getNome() + "\n");

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
                    public void onValueChange(NumberPicker picker, int oldVal, final int newVal){
                        Button btnComprar = (Button) findViewById(R.id.btnFinalizarCompra);
                        btnComprar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                    Venda venda = new Venda();
                                    venda.setId_usuario(user.getId());
                                    venda.setId_ingresso(idIngresso);
                                    venda.setQuantidade(newVal);

                                    RetrofitUtil retrofitUtil = RetrofitUtil.retrofit.create(RetrofitUtil.class);
                                    final Call<Void> call = retrofitUtil.inserirVenda(venda);
                                    call.enqueue(new Callback<Void>() {
                                        @Override
                                        public void onResponse(Call<Void> call, Response<Void> response) {
                                            if (response.isSuccessful()){
                                                Intent intent = new Intent(VendaActivity.this, LoginActivity.class);
                                                startActivityForResult(intent, REDIRECT);
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Void> call, Throwable t) {
                                                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                                                System.out.println(t.getMessage());
                                        }
                                    });
                            }
                        });
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
