package com.tesji.proyectohospital;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorPaciente extends RecyclerView.Adapter<AdaptadorPaciente.ViewHolderPaciente> {

    ArrayList<PacienteVeo> listaPa;

    public AdaptadorPaciente(ArrayList<PacienteVeo> listaPa) {
        this.listaPa = listaPa;
    }

    @NonNull
    @Override
    public ViewHolderPaciente onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_pa,null,false);
        return new ViewHolderPaciente(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPaciente holder, int position) {

        holder.nombre.setText(listaPa.get(position).getNombre());
        holder.ap1.setText(listaPa.get(position).getAp1());
        holder.ap2.setText(listaPa.get(position).getAp2());
        holder.telefono.setText(listaPa.get(position).getTelefono());
        holder.email.setText(listaPa.get(position).getEmail());
        holder.fpaci.setImageResource(listaPa.get(position).getFotop());

    }

    @Override
    public int getItemCount() {
        return listaPa.size();
    }

    public class ViewHolderPaciente extends RecyclerView.ViewHolder {

        TextView nombre, ap1, ap2, telefono, email;
        ImageView fpaci;

        public ViewHolderPaciente(@NonNull View itemView) {
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.pac_N);
            ap1 = (TextView) itemView.findViewById(R.id.ap1_N);
            ap2 = (TextView) itemView.findViewById(R.id.ap2_N);
            telefono = (TextView) itemView.findViewById(R.id.tel_P);
            email = (TextView) itemView.findViewById(R.id.email_P);
            fpaci = (ImageView) itemView.findViewById(R.id.imagen);
        }
    }
}
