package com.example.marin.qrticket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marin.qrticket.R;
import com.example.marin.qrticket.model.Evento;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by marin on 28/04/2019.
 */

public class EventoAdapter extends ArrayAdapter<Evento> {

    private final Context context;
    private final List<Evento> eventos;
    private final DisplayImageOptions options;

    public EventoAdapter(Context context, List<Evento> eventos) {
        super(context, R.layout.adapter_evento, eventos);
        this.context = context;
        this.eventos = eventos;

        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = layoutInflater.inflate(R.layout.adapter_evento, parent, false);

        TextView nome = (TextView) rowView.findViewById(R.id.mostrarNomEvento);
        TextView capacidade = (TextView) rowView.findViewById(R.id.mostrarCapacidadEvento);
        TextView data = (TextView) rowView.findViewById(R.id.mostrarDataEvento);
        //ImageView imgEvento = (ImageView) rowView.findViewById(R.id.imgEvento);

        //https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4SNCMSWQkGIoSwpeaU33gpLe-f1DMEMlp00vweeZy1uilyam3pQ
        //String uri = "https://queconceito.com.br/wp-content/uploads/evento.jpg";

        //ImageLoader.getInstance().displayImage(uri, imgEvento, options);

        nome.setText("Nome do Evento: " + eventos.get(position).getNome());

        capacidade.setText("Capacidade: " + String.valueOf(eventos.get(position).getCapacidade()));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Date dataEntrada = eventos.get(position).getData();

        try {
            dataEntrada = sdf.parse(String.valueOf(eventos.get(position).getData()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String dataFormatada = new SimpleDateFormat("dd-MM-yyyy").format(dataEntrada);
        data.setText("Data de realização: " + dataFormatada);


        return rowView;
    }
}
