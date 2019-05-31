package com.example.marin.qrticket.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.marin.qrticket.R;
import com.example.marin.qrticket.model.IngressoUsuario;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QrCreator extends AppCompatActivity implements View.OnClickListener{

    //conteudo do qrcode
    IngressoUsuario ig = new IngressoUsuario();
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_creator);

        Button btnQr = (Button) findViewById(R.id.btnGerarQr);
        Button btnCancelQr = (Button) findViewById(R.id.btnCancelarIng);

        btnQr.setOnClickListener(this);
        btnCancelQr.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnGerarQr){
            ig = (IngressoUsuario) getIntent().getSerializableExtra("ingresso");
            content = ig.getQrcode().toString();

            //Classe para criar o qr code
            QRCodeWriter writer = new QRCodeWriter();
            try {
                BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, 512, 512);
                int width = bitMatrix.getWidth();
                int height = bitMatrix.getHeight();
                Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                    }
                }
                ((ImageView) findViewById(R.id.qrGenerator)).setImageBitmap(bmp);

            } catch (WriterException e) {
                e.printStackTrace();
            }
        }else if(view.getId() == R.id.btnCancelarIng){
            //terminar a função de cancelar ingresso
            finish();
        }
    }
}
