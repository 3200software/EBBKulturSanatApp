package com.software3200.ebbkultursanatapp.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.software3200.ebbkultursanatapp.Model.ModelSeatSelect;
import com.software3200.ebbkultursanatapp.databinding.RecyclerRowSeatSelectBinding;

import java.util.ArrayList;

public class AdapterSeatSelect extends RecyclerView.Adapter<AdapterSeatSelect.SeatSelectHolder> {

    ArrayList<ModelSeatSelect> modelSeatSelectArrayList;

    public AdapterSeatSelect(ArrayList<ModelSeatSelect> modelSeatSelectArrayList) {
        this.modelSeatSelectArrayList = modelSeatSelectArrayList;

    }

    @NonNull
    @Override
    public SeatSelectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerRowSeatSelectBinding recyclerRowSeatSelectBinding = RecyclerRowSeatSelectBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);


        return new SeatSelectHolder(recyclerRowSeatSelectBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SeatSelectHolder holder, int position) {

        String seatStatus = modelSeatSelectArrayList.get(position).seatStatus;

        holder.recyclerRowSeatSelectBinding.seatSelectButton.setText(modelSeatSelectArrayList.get(position).seatName);



        if (seatStatus.equals("1")){

            holder.recyclerRowSeatSelectBinding.seatSelectButton.setBackgroundColor(Color.rgb(190,190,190));
            holder.recyclerRowSeatSelectBinding.seatSelectButton.setText(modelSeatSelectArrayList.get(position).seatName);

        } else if (seatStatus.equals("2")) {

            holder.recyclerRowSeatSelectBinding.seatSelectButton.setEnabled(false);


        } else if (seatStatus.equals("3")) {

            holder.recyclerRowSeatSelectBinding.seatSelectButton.setBackgroundColor(Color.BLUE);

        } else if (seatStatus.equals("4")) {

            holder.recyclerRowSeatSelectBinding.seatSelectButton.setText("");
            holder.recyclerRowSeatSelectBinding.seatSelectButton.setBackgroundColor(Color.WHITE);


        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (seatStatus.equals("1")){

                   holder.recyclerRowSeatSelectBinding.seatSelectButton.setBackgroundColor(Color.rgb(25,50,40));
                   holder.recyclerRowSeatSelectBinding.seatSelectButton.setText(modelSeatSelectArrayList.get(position).seatName);

               } else if (seatStatus.equals("2")) {

                   holder.recyclerRowSeatSelectBinding.seatSelectButton.setEnabled(true);


               } else if  (seatStatus.equals("3")) {

                    holder.recyclerRowSeatSelectBinding.seatSelectButton.setEnabled(false);
                    holder.recyclerRowSeatSelectBinding.seatSelectButton.setBackgroundColor(Color.RED);

                } else if (seatStatus.equals("4")){

                    holder.recyclerRowSeatSelectBinding.seatSelectButton.setEnabled(false);

                }



            }
        });



    }

    @Override
    public int getItemCount() {

        return modelSeatSelectArrayList.size();

    }

    class SeatSelectHolder extends RecyclerView.ViewHolder {


        RecyclerRowSeatSelectBinding recyclerRowSeatSelectBinding;

        public SeatSelectHolder(RecyclerRowSeatSelectBinding recyclerRowSeatSelectBinding) {
            super(recyclerRowSeatSelectBinding.getRoot());

            this.recyclerRowSeatSelectBinding = recyclerRowSeatSelectBinding;

        }
    }


}
