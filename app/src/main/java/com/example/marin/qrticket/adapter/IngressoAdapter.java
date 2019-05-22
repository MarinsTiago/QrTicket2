package com.example.marin.qrticket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.marin.qrticket.R;
import com.example.marin.qrticket.model.Ingresso;

import java.util.List;

/**
 * Created by marin on 20/05/2019.
 */


public class IngressoAdapter extends ArrayAdapter<Ingresso> {


    private final Context context;
    private final List<Ingresso> ingressos;

    public IngressoAdapter(Context context,  List<Ingresso> ingressos) {
        super(context, R.layout.adapter_ingresso ,ingressos);
        this.context = context;
        this.ingressos = ingressos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.adapter_ingresso, parent, false);
        TextView desc = (TextView) rowView.findViewById(R.id.descIng);
        TextView preco = (TextView) rowView.findViewById(R.id.precoIng);

        desc.setText("Categoria: " + String.valueOf(ingressos.get(position).getDescricao()));
        preco.setText("Valor: " + String.valueOf(ingressos.get(position).getValor()) + " R$");

        return rowView;
    }
}
