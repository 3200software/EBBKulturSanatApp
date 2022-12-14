package com.software3200.ebbkultursanatapp.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.software3200.ebbkultursanatapp.Activity.ActivityDetailActivity;
import com.software3200.ebbkultursanatapp.Model.ModelHomeBanner;
import com.software3200.ebbkultursanatapp.databinding.RecyclerRowMainBannerBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class AdapterHomeBanner extends RecyclerView.Adapter<AdapterHomeBanner.HomeBannerHolder> {

    ArrayList<ModelHomeBanner> modelHomeBannerArrayList;

    public AdapterHomeBanner(ArrayList<ModelHomeBanner> modelHomeBannerArrayList) {
        this.modelHomeBannerArrayList = modelHomeBannerArrayList;
    }

    @NonNull
    @Override
    public HomeBannerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowMainBannerBinding recyclerRowMainBannerBinding = RecyclerRowMainBannerBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new HomeBannerHolder(recyclerRowMainBannerBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeBannerHolder holder, int position) {

        Picasso.get().load(modelHomeBannerArrayList.get(position).activityImgUrl).into(holder.recyclerRowMainBannerBinding.homeNewsRecyclerView);
        String homeBannnerCategoryName = modelHomeBannerArrayList.get(position).activityCategoryName;
        String homeBannerDocumnetId = modelHomeBannerArrayList.get(position).activityDocumentId;


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent homeBannerToActivityDetailIntent = new Intent(holder.itemView.getContext(), ActivityDetailActivity.class);
                homeBannerToActivityDetailIntent.putExtra("ActivityDocumentId", homeBannerDocumnetId);
                holder.itemView.getContext().startActivity(homeBannerToActivityDetailIntent);

            }
        });




    }

    @Override
    public int getItemCount() {
        return modelHomeBannerArrayList.size();
    }

    class HomeBannerHolder extends RecyclerView.ViewHolder {

        RecyclerRowMainBannerBinding recyclerRowMainBannerBinding;

        public HomeBannerHolder(RecyclerRowMainBannerBinding recyclerRowMainBannerBinding) {
            super(recyclerRowMainBannerBinding.getRoot());

            this.recyclerRowMainBannerBinding = recyclerRowMainBannerBinding;

        }
    }

}
