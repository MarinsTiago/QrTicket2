package com.example.marin.qrticket.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.example.marin.qrticket.R;
import com.example.marin.qrticket.model.Evento;
import com.example.marin.qrticket.model.Qrcode;
import com.example.marin.qrticket.util.RetrofitUtil;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//Classe para a Empresa, ao entrar com as infos na tela de login, a empresa é redirecionada para essa tela
//que possui apenas um botão para abrir o scanner de QRCODE, o mesmo já está fazendo a leitura;
public class QrCodeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);
        final Activity activity = this;


       // btnLogout.setOnClickListener(this);

        IntentIntegrator integrator = new IntentIntegrator(activity);
        //setando o tipo de scaner, que no nosso caso é o de qrcode
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        //texto que aparece na tela de leitura do QRCODE
        integrator.setPrompt("Escaneando QrCode");
        //0 para selecionar camera traseira
        integrator.setCameraId(0);
        //inicia o escaner
        integrator.initiateScan();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                Qrcode qr = new Qrcode();
                int e;
                e = (int) getIntent().getSerializableExtra("idEvento");
                String qrcode = result.getContents();
                qr.setId_evento(e);
                qr.setQrcode(qrcode);

                Toast.makeText(getBaseContext(), String.valueOf(e) + qrcode, Toast.LENGTH_LONG).show();

                RetrofitUtil retrofitUtil = RetrofitUtil.retrofit.create(RetrofitUtil.class);
                final Call<Qrcode> call = retrofitUtil.validarQr(qr);
                call.enqueue(new Callback<Qrcode>() {
                    @Override
                    public void onResponse(Call<Qrcode> call, Response<Qrcode> response) {
                       if (response.code() == 200){
                           Toast.makeText(getBaseContext(), "Ingresso válido", Toast.LENGTH_LONG).show();
                           finish();
                       }else if(response.code() == 500){
                           Toast.makeText(getBaseContext(), "QRCODE INVÁLIDO", Toast.LENGTH_LONG).show();
                           finish();
                       }


                    }

                    @Override
                    public void onFailure(Call<Qrcode> call, Throwable t) {
                        Toast.makeText(getBaseContext(), "Erro", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });

                //validar o qrcode
                finish();
                super.onActivityResult(requestCode, resultCode, data);
            }

        }
    }
    private void alert(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}
