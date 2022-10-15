package com.software3200.ebbkultursanatapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.software3200.ebbkultursanatapp.Model.ModelActivityInfo;
import com.software3200.ebbkultursanatapp.databinding.RecyclerRowActivityBinding;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AdapterActivityInfo extends RecyclerView.Adapter<AdapterActivityInfo.ActivityInfoHolder> {


    ArrayList<ModelActivityInfo> modelActivityInfoArrayList;

    public AdapterActivityInfo(ArrayList<ModelActivityInfo> modelActivityInfoArrayList) {
        this.modelActivityInfoArrayList = modelActivityInfoArrayList;
    }

    @NonNull
    @Override
    public ActivityInfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowActivityBinding recyclerRowActivityBinding = RecyclerRowActivityBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ActivityInfoHolder(recyclerRowActivityBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityInfoHolder holder, int position) {

        Picasso.get().load(modelActivityInfoArrayList.get(position).activityImgUrl).into(holder.recyclerRowActivityBinding.activityImageView);
        holder.recyclerRowActivityBinding.actvityTitleTextView.setText(modelActivityInfoArrayList.get(position).activityTitle);
        holder.recyclerRowActivityBinding.activityLocationTextView.setText(modelActivityInfoArrayList.get(position).activityLocation);
        holder.recyclerRowActivityBinding.activityCategoryTextView.setText(modelActivityInfoArrayList.get(position).activityCategory);




        if (modelActivityInfoArrayList.get(position).activityEndDate == null) {

            Calendar cal = Calendar.getInstance();
            cal.setTime(modelActivityInfoArrayList.get(position).activityDate);


            int dayInt = cal.get(Calendar.DAY_OF_MONTH);
            String month = new SimpleDateFormat("MMM").format(cal.getTime());
            String day = new SimpleDateFormat("EEEE").format(cal.getTime());
            String time = new SimpleDateFormat("HH:mm").format(cal.getTime());

            holder.recyclerRowActivityBinding.activitDateDayNumberTextView.setText(dayInt);
            holder.recyclerRowActivityBinding.activityDateMonthTextView.setText(month);
            holder.recyclerRowActivityBinding.activiyDateDayTextview.setText(day);


            holder.recyclerRowActivityBinding.activityDateBrace.setVisibility(View.INVISIBLE);
            holder.recyclerRowActivityBinding.activityDateBrace.getLayoutParams().width = 0;
            holder.recyclerRowActivityBinding.activitDateDayNumberTextView2.setVisibility(View.INVISIBLE);
            holder.recyclerRowActivityBinding.activitDateDayNumberTextView2.setWidth(0);
            holder.recyclerRowActivityBinding.activityDateMonthTextView2.setVisibility(View.INVISIBLE);
            holder.recyclerRowActivityBinding.activityDateMonthTextView2.setWidth(0);
            holder.recyclerRowActivityBinding.activiyDateDayTextview2.setVisibility(View.INVISIBLE);
            holder.recyclerRowActivityBinding.activiyDateDayTextview2.setWidth(0);


        } else {


            Calendar beginCal = Calendar.getInstance();
            beginCal.setTime(modelActivityInfoArrayList.get(position).activityBeginDate);

            int dayInt = beginCal.get(Calendar.DAY_OF_MONTH);
            String month = new SimpleDateFormat("MMM").format(beginCal.getTime());
            String day = new SimpleDateFormat("EEEE").format(beginCal.getTime());
            String time = new SimpleDateFormat("HH:mm").format(beginCal.getTime());

            holder.recyclerRowActivityBinding.activitDateDayNumberTextView.setText(dayInt);
            holder.recyclerRowActivityBinding.activityDateMonthTextView.setText(month);
            holder.recyclerRowActivityBinding.activiyDateDayTextview.setText(day);

            Calendar endCal = Calendar.getInstance();
            endCal.setTime(modelActivityInfoArrayList.get(position).activityEndDate);

            int dayIntend = endCal.get(Calendar.DAY_OF_MONTH);
            String monthend = new SimpleDateFormat("MMM").format(endCal.getTime());
            String dayend = new SimpleDateFormat("EEEE").format(endCal.getTime());


            holder.recyclerRowActivityBinding.activitDateDayNumberTextView2.setText(dayIntend);
            holder.recyclerRowActivityBinding.activityDateMonthTextView2.setText(monthend);
            holder.recyclerRowActivityBinding.activiyDateDayTextview2.setText(dayend);




        }










    }

    @Override
    public int getItemCount() {
        return modelActivityInfoArrayList.size();
    }



    class ActivityInfoHolder extends RecyclerView.ViewHolder {

        RecyclerRowActivityBinding recyclerRowActivityBinding;

        public ActivityInfoHolder(RecyclerRowActivityBinding recyclerRowActivityBinding) {
            super(recyclerRowActivityBinding.getRoot());

            this.recyclerRowActivityBinding = recyclerRowActivityBinding;

        }
    }


}
