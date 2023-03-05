package com.software3200.ebbkultursanatapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.software3200.ebbkultursanatapp.R;
import com.software3200.ebbkultursanatapp.databinding.ActivityTicketBinding;
import com.squareup.picasso.Picasso;

public class TicketActivity extends AppCompatActivity {

    ActivityTicketBinding binding;

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;

    String ticketSerialnumber;
    String getPageString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        binding = ActivityTicketBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        Intent ticketPageIntent = getIntent();
        ticketSerialnumber = ticketPageIntent.getStringExtra("ticketSerialnumber");
        getPageString = ticketPageIntent.getStringExtra("ticketGoToPage");

        getTicketDetail();

    }


    public void getTicketDetail () {


            firebaseFirestore.collection("Tickets").document(ticketSerialnumber).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                    if (task.isSuccessful()) {

                        DocumentSnapshot document = task.getResult();

                        if (document.exists()) {


                            String activityName = (String) document.get("activityName");
                            String activitySaloon = (String) document.get("activitySaloon");
                            String userSeat = (String) document.get("ticketSeatName");
                            String activityTime = (String) document.get("activityTime");
                            String ticketPrice = (String) document.get("ticketPriceString");
                            String userNameSurname = (String) document.get("nameSurname");
                            String activityImgUrl = (String) document.get("ticketImgUrl");
                            String qrImgUrl = (String) document.get("ticketQrImage");

                            Picasso.get().load(activityImgUrl).into(binding.activityImageView);
                            Picasso.get().load(qrImgUrl).into(binding.activityTicketQrCodeImg);

                            binding.activityTitleTextview.setText(activityName);
                            binding.selectSaloonTextView.setText(activitySaloon);
                            binding.selectSeatTextview.setText(userSeat);
                            binding.activitDateTextview.setText(activityTime);
                            binding.priceTextview.setText(ticketPrice);
                            binding.userNameSurnameTextview.setText(userNameSurname);




                        }

                    }

                }
            });





    }



}