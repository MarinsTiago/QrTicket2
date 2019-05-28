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

        desc.setText("Id Ingresso: " + String.valueOf(ingressos.get(position).getId_ingresso()));
        preco.setText("Id Evento: " + String.valueOf(ingressos.get(position).getId_evento()));

        return rowView;

    }
}
