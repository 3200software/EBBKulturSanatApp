package com.software3200.ebbkultursanatapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

        Picasso.get().load(modelHomeBannerArrayList.get(position).activityCategoryName).into(holder.recyclerRowMainBannerBinding.homeNewsRecyclerView);
        String homeBannnerCategoryName = modelHomeBannerArrayList.get(position).activityCategoryName;
        String homeBannerDocumnetId = modelHomeBannerArrayList.get(position).activityDocumentId;





    }

    @Override
    public int getItemCount() {
        return 8;
    }

    class HomeBannerHolder extends RecyclerView.ViewHolder {

        RecyclerRowMainBannerBinding recyclerRowMainBannerBinding;

        public HomeBannerHolder(RecyclerRowMainBannerBinding recyclerRowMainBannerBinding) {
            super(recyclerRowMainBannerBinding.getRoot());

            this.recyclerRowMainBannerBinding = recyclerRowMainBannerBinding;

        }
    }

}
