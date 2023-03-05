package com.software3200.ebbkultursanatapp.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.software3200.ebbkultursanatapp.Activity.TicketActivity;
import com.software3200.ebbkultursanatapp.databinding.RecyclerRowTicketListBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterTicketList extends RecyclerView.Adapter<AdapterTicketList.TicketListViewHolder> {

    ArrayList<ModelTicketList> modelTicketListArrayList;

    public AdapterTicketList(ArrayList<ModelTicketList> modelTicketListArrayList) {
        this.modelTicketListArrayList = modelTicketListArrayList;
    }

    @NonNull
    @Override
    public TicketListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerRowTicketListBinding recyclerRowTicketListBinding = RecyclerRowTicketListBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);


        return new TicketListViewHolder(recyclerRowTicketListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketListViewHolder holder, int position) {

        holder.recyclerRowTicketListBinding.tickettitleTextView.setText(modelTicketListArrayList.get(position).ticketActivityName);
        holder.recyclerRowTicketListBinding.ticketdateTextView.setText(modelTicketListArrayList.get(position).ticketDateString);

        Picasso.get().load(modelTicketListArrayList.get(position).ticketImgUrl).into(holder.recyclerRowTicketListBinding.ticketListImageview);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String documentId = modelTicketListArrayList.get(position).ticketDocumentId;

                Intent gototicketıntent = new Intent(holder.itemView.getContext(), TicketActivity.class);
                gototicketıntent.putExtra("ticketSerialnumber",documentId);
                gototicketıntent.putExtra("ticketGoToPage","UserTicketListPage");
                holder.itemView.getContext().startActivity(gototicketıntent);





            }
        });



    }

    @Override
    public int getItemCount() {
        return modelTicketListArrayList.size();
    }

    class TicketListViewHolder extends RecyclerView.ViewHolder {

        RecyclerRowTicketListBinding recyclerRowTicketListBinding;

        public TicketListViewHolder(RecyclerRowTicketListBinding recyclerRowTicketListBinding) {
            super(recyclerRowTicketListBinding.getRoot() );

            this.recyclerRowTicketListBinding = recyclerRowTicketListBinding;

        }
    }

}
