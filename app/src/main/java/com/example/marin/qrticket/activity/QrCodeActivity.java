package com.example.marin.qrticket.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.marin.qrticket.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

//Classe para a Empresa, ao entrar com as infos na tela de login, a empresa é redirecionada para essa tela
//que possui apenas um botão para abrir o scanner de QRCODE, o mesmo já está fazendo a leitura;
public class QrCodeActivity extends AppCompatActivity {

    private Button btnScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);
        btnScan = (Button) findViewById(R.id.btnScan);
        final Activity activity = this;
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null){
            if (result.getContents() != null){
                //validar o qrcode
                if (result.getContents().equals("qrticket")){
                        Toast.makeText(getApplicationContext(), "Ingresso válido", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Ingresso inválido", Toast.LENGTH_LONG).show();
                }
            }else{
                alert("Scan cancelado");
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }

    }
    private void alert(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}
