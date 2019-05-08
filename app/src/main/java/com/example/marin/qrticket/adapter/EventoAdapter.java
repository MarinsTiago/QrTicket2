package com.example.marin.qrticket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.marin.qrticket.R;
import com.example.marin.qrticket.model.Evento;

import java.util.List;

/**
 * Created by marin on 28/04/2019.
 */

public class EventoAdapter extends ArrayAdapter<Evento> {

    private final Context context;
    private final List<Evento> eventos;

    public EventoAdapter(Context context, List<Evento> eventos) {
        super(context, R.layout.adapter_evento, eventos);
        this.context = context;
        this.eventos = eventos;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = layoutInflater.inflate(R.layout.adapter_evento, parent, false);

        TextView nome = (TextView) rowView.findViewById(R.id.mostrarNomEvento);
        TextView desc = (TextView) rowView.findViewById(R.id.mostrarDescEvento);
        TextView capacidade = (TextView) rowView.findViewById(R.id.mostrarCapacidadEvento);
        TextView data = (TextView) rowView.findViewById(R.id.mostrarDataEvento);
        TextView horaInicio = (TextView) rowView.findViewById(R.id.mostrarHoraInicioEvento);
        TextView horaFim = (TextView) rowView.findViewById(R.id.mostrarHoraFimEvento);
        TextView dataDev = (TextView) rowView.findViewById(R.id.mostrarDataDevEvento);

        nome.setText(eventos.get(position).getNome());
        desc.setText(eventos.get(position).getDescricao());
        capacidade.setText(String.valueOf(eventos.get(position).getCapacidade()));
        data.setText(String.valueOf(eventos.get(position).getData()));
        horaInicio.setText(String.valueOf(eventos.get(position).getHora_inicio()));
        horaFim.setText(String.valueOf(eventos.get(position).getHora_fim()));
        dataDev.setText(String.valueOf(eventos.get(position).getData_devolucao()));

        return rowView;
    }
}
