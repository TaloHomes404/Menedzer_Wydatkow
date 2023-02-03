package com.example.menedzerwydatkow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MojAdapter extends RecyclerView.Adapter<MojAdapter.ViewHolder> {

    Context context;
    ArrayList<InformacjeModel> informacjeModelArrayList;

    public MojAdapter(Context context, ArrayList<InformacjeModel> informacjeModelArrayList) {
        this.context = context;
        this.informacjeModelArrayList = informacjeModelArrayList;
    }

    @NonNull
    @Override
    public MojAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pojedyncza_informacja,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MojAdapter.ViewHolder holder, int position) {

        String informacja = informacjeModelArrayList.get(position).getInformacja();
        String kategoria = informacjeModelArrayList.get(position).getKategoria();
        String kwota = informacjeModelArrayList.get(position).getKwota();
        String rodzaj = informacjeModelArrayList.get(position).getRodzaj();

        holder.txtInformacja.setText(informacja);
        holder.txtKategoria.setText(kategoria);
        holder.txtKwota.setText(kwota);
        holder.txtRodzaj.setText(rodzaj);

    }

    @Override
    public int getItemCount() {
        return informacjeModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtInformacja,txtKategoria,txtKwota,txtRodzaj;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtInformacja=itemView.findViewById(R.id.tekstInformacja);
            txtKategoria=itemView.findViewById(R.id.tekstKategoria);
            txtKwota=itemView.findViewById(R.id.tekstKwota);
            txtRodzaj=itemView.findViewById(R.id.tekstRodzaj);
            cardView = itemView.findViewById(R.id.widokCard);

        }
    }
}
