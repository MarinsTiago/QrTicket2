package com.example.marin.qrticket.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.marin.qrticket.R;
import com.example.marin.qrticket.model.IngressoUsuario;
import com.example.marin.qrticket.util.RetrofitUtil;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by marin on 26/05/2019.
 */

public class IngressoUsuarioAdapter extends ArrayAdapter<IngressoUsuario> {

    private final Context context;
    private final List<IngressoUsuario> ingressos;


    public IngressoUsuarioAdapter(Context context, List<IngressoUsuario> ingressos){
        super(context, R.layout.adapter_ingresso_usuario ,ingressos);
        this.context = context;
        this.ingressos = ingressos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.adapter_ingresso_usuario, parent, false);
        TextView desc = (TextView) rowView.findViewById(R.id.descIngUser);
        TextView preco = (TextView) rowView.findViewById(R.id.precoIngUser);
        TextView evento = (TextView) rowView.findViewById(R.id.eventoIngUser);
        TextView data = (TextView) rowView.findViewById(R.id.dataIngUser);
        TextView hora = (TextView) rowView.findViewById(R.id.horaIngUser);

        desc.setText("Descrição: " + String.valueOf(ingressos.get(position).getDescricao()));

        DecimalFormat df =  new DecimalFormat("0.00");
        float precoFinal = ingressos.get(position).getValor();
        preco.setText("Valor: R$ " + String.valueOf(df.format(precoFinal)));

        evento.setText("Evento: "+String.valueOf(ingressos.get(position).getNome()));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataEntrada = ingressos.get(position).getData();

        try {
            dataEntrada = sdf.parse(String.valueOf(ingressos.get(position).getData()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String dataFormatada = new SimpleDateFormat("dd-MM-yyyy").format(dataEntrada);
        data.setText("Data de realização: " + dataFormatada);

        hora.setText("Horario: " + String.valueOf(ingressos.get(position).getHora_inicio()));

        return rowView;

    }
}
