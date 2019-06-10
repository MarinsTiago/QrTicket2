package com.example.marin.qrticket.activity;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.marin.qrticket.R;
import com.example.marin.qrticket.model.IngressoUsuario;
import com.example.marin.qrticket.util.RetrofitUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        Button btnVoltar = (Button) findViewById(R.id.voltar);

        btnQr.setOnClickListener(this);
        btnCancelQr.setOnClickListener(this);
        btnVoltar.setOnClickListener(this);
    }


    @Override
    public void onClick(final View view) {
        if (view.getId() == R.id.btnGerarQr){

            ig = (IngressoUsuario) getIntent().getSerializableExtra("ingresso");
            content = ig.getQrcode().toString();

            //Classe para criar o qr code
            final QRCodeWriter writer = new QRCodeWriter();
            new CountDownTimer(10000, 1000) {

                public void onTick(long millisUntilFinished) {
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
                }

                public void onFinish() {
                    AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                    alert.setMessage("Ingresso escaneado com sucesso");
                    alert.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int whichButton) {
                                    dialog.dismiss();
                                    finish();
                                }
                            });
                    alert.show();
                }

            }.start();
        }else if(view.getId() == R.id.btnCancelarIng){
            AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
            alert.setMessage("Deseja realmente estornar o ingresso?");
            alert.setPositiveButton("Estornar",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int whichButton) {
                            ig = (IngressoUsuario) getIntent().getSerializableExtra("ingresso");
                            int id = ig.getId();
                            RetrofitUtil retrofitUtil = RetrofitUtil.retrofit.create(RetrofitUtil.class);
                            final Call<Void> call = retrofitUtil.estornarIngresso(id);
                            call.enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    int req;
                                    req = response.code();
                                    if (req == 200){
                                        AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                                        alert.setMessage("Data de estorno ultrapassada!");
                                        alert.setPositiveButton("Ok",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog,
                                                                        int whichButton) {
                                                        dialog.dismiss();
                                                        finish();
                                                    }
                                                });
                                        alert.show();
                                    }else if(req == 500){
                                        AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                                        alert.setMessage("Data de estorno ultrapassada!");
                                        alert.setPositiveButton("Ok",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog,
                                                                        int whichButton) {
                                                        dialog.dismiss();
                                                    }
                                                });
                                        alert.show();
                                    }

                                }
                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {
                                    Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                                    Toast.makeText(getBaseContext(), "Erro de sistema", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
            );

            alert.setNegativeButton("Cancelar",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int whichButton) {
                            dialog.dismiss();
                        }
                    });
            alert.show();
        }else if (view.getId() == R.id.voltar){
            finish();
        }
    }
}
