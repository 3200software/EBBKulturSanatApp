package com.software3200.ebbkultursanatapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.software3200.ebbkultursanatapp.Model.ModelUserSelectSeats;
import com.software3200.ebbkultursanatapp.R;
import com.software3200.ebbkultursanatapp.databinding.ActivityPaymentBinding;

import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {


    ActivityPaymentBinding binding;
    ArrayList<ModelUserSelectSeats> modelUserSelectSeatsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        binding = ActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent getSeatInfoIntent = getIntent();




        modelUserSelectSeatsArrayList = new ArrayList<>();

        System.out.println(modelUserSelectSeatsArrayList);





    }
}