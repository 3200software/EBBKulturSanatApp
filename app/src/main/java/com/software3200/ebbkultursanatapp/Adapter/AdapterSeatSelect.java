package com.software3200.ebbkultursanatapp.Adapter;

import static com.software3200.ebbkultursanatapp.R.drawable.seat_another_basket;
import static com.software3200.ebbkultursanatapp.R.drawable.seat_border_back_empty;
import static com.software3200.ebbkultursanatapp.R.drawable.seat_disabled_human;
import static com.software3200.ebbkultursanatapp.R.drawable.seat_full_;
import static com.software3200.ebbkultursanatapp.R.drawable.seat_protocol_;
import static com.software3200.ebbkultursanatapp.R.drawable.seat_select_;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.software3200.ebbkultursanatapp.JavaModel.SeatDocumentIdModel;
import com.software3200.ebbkultursanatapp.Model.ModelSeatSelect;
import com.software3200.ebbkultursanatapp.R;
import com.software3200.ebbkultursanatapp.databinding.RecyclerRowSeatSelectBinding;

import java.util.ArrayList;
import java.util.HashMap;

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

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();




        Integer seatStatus = modelSeatSelectArrayList.get(position).seatStatus;
        String seatnamex = modelSeatSelectArrayList.get(position).seatName;


        if (seatnamex.equals("NA")) {

            holder.recyclerRowSeatSelectBinding.seatSelectButton.setBackgroundColor(Color.rgb(255,255,255));
            holder.recyclerRowSeatSelectBinding.seatSelectButton.setText("");

        } else {

            holder.recyclerRowSeatSelectBinding.seatSelectButton.setText(modelSeatSelectArrayList.get(position).seatName);


            if (seatStatus == 0){


                holder.recyclerRowSeatSelectBinding.seatSelectButton.setBackgroundResource(seat_border_back_empty);



            } else if (seatStatus == 1) {



                holder.recyclerRowSeatSelectBinding.seatSelectButton.setBackgroundResource(seat_full_);
                holder.recyclerRowSeatSelectBinding.seatSelectButton.setEnabled(false);


            } else if (seatStatus == 2) {


                holder.recyclerRowSeatSelectBinding.seatSelectButton.setBackgroundResource(seat_select_);
                holder.recyclerRowSeatSelectBinding.seatSelectButton.setEnabled(false);


            } else if (seatStatus == 3) {

                holder.recyclerRowSeatSelectBinding.seatSelectButton.setBackgroundResource(seat_another_basket);
                holder.recyclerRowSeatSelectBinding.seatSelectButton.setEnabled(false);


            } else if (seatStatus == 4) {

                holder.recyclerRowSeatSelectBinding.seatSelectButton.setBackgroundResource(seat_disabled_human);

            } else if (seatStatus == 5) {

                holder.recyclerRowSeatSelectBinding.seatSelectButton.setBackgroundResource(seat_protocol_);
                holder.recyclerRowSeatSelectBinding.seatSelectButton.setEnabled(false);


            }



        }





        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (seatStatus == 0){

                    HashMap<String,Object> updateSeatEmpty = new HashMap<>();
                    updateSeatEmpty.put("seatStatus",2);

                    holder.recyclerRowSeatSelectBinding.seatSelectButton.setBackgroundResource(seat_select_);

                    firebaseFirestore.collection("Events").document("ECQnpWTp9HEDIykhpe5f").collection("Saloon").document(modelSeatSelectArrayList.get(position).documentId).update(updateSeatEmpty);

                    notifyDataSetChanged();


                } else if (seatStatus == 2) {

                    holder.recyclerRowSeatSelectBinding.seatSelectButton.setBackgroundResource(seat_border_back_empty);

                    HashMap<String,Object> updateSeatEmpty = new HashMap<>();
                    updateSeatEmpty.put("seatStatus",0);

                    firebaseFirestore.collection("Events").document("ECQnpWTp9HEDIykhpe5f").collection("Saloon").document(modelSeatSelectArrayList.get(position).documentId).update(updateSeatEmpty);

                    notifyDataSetChanged();






               } else if  (seatStatus == 3) {




                } else if (seatStatus == 4) {

                    holder.recyclerRowSeatSelectBinding.seatSelectButton.setBackgroundResource(seat_select_);
                    holder.recyclerRowSeatSelectBinding.seatSelectButton.setEnabled(false);

                } else if (seatStatus == 5) {

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
