package com.software3200.ebbkultursanatapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.software3200.ebbkultursanatapp.R;
import com.software3200.ebbkultursanatapp.databinding.ActivityDetailBinding;
import com.software3200.ebbkultursanatapp.databinding.ActivityMainBinding;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ActivityDetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;

    FirebaseFirestore firebaseFirestore;

    String activityDetailDocumntID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());





    }


    public void getActivityDetail() {



        firebaseFirestore.collection("Activity").document(activityDetailDocumntID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {

                    DocumentSnapshot documemnt = task.getResult();
                    if (documemnt.exists()) {

                        String activityImgUrl = (String) documemnt.get("activityImgUrl");
                        String activitytitle = (String) documemnt.get("activityTitle");
                        String activityDetail = (String) documemnt.get("activityDetail");
                        String activityDetailTitle = (String) documemnt.get("activityDetailTitle");
                        String activityCategory = (String) documemnt.get("activityCategory");
                        String activityOrganization = (String) documemnt.get("activityOrganization");
                        Date activityDate = (Date) documemnt.get("activityDate");
                        Date activityBeginDate = (Date) documemnt.get("activityBeginDate");
                        Date activityEndDate = (Date) documemnt.get("activityEndDate");
                        Long activityPriceInfo = (Long) documemnt.get("activityPriceInfo");
                        String activityLocation = (String) documemnt.get("activityLocation");
                        String activityLocationAdress = (String) documemnt.get("activityLocationAdress");
                        Long activityLocationLatitude = (Long) documemnt.get("activityLocationLatitude");
                        Long activityLocationLongitude = (Long) documemnt.get("activityLocationLongitude");
                        String activityTelephoneNumber = (String) documemnt.get("activiactivityTelephoneNumbertyTitle");



                        Picasso.get().load(activityImgUrl).into(binding.activityImageView);
                        binding.activityTitleTextView.setText(activitytitle);
                        binding.activityLocationTextView.setText(activityLocation);
                        binding.activityCategoryTextView.setText(activityCategory);




                        if (activityEndDate == null) {

                            Calendar cal = Calendar.getInstance();
                            cal.setTime(activityDate);


                            int dayInt = cal.get(Calendar.DAY_OF_MONTH);
                            String month = new SimpleDateFormat("MMM").format(cal.getTime());
                            String day = new SimpleDateFormat("EEEE").format(cal.getTime());
                            String time = new SimpleDateFormat("HH:mm").format(cal.getTime());

                            binding.activitDateDayNumberTextView.setText(dayInt);
                            binding.activityDateMonthTextView.setText(month);
                            binding.activiyDateDayTextview.setText(day);


                            binding.activityDateBrace.setVisibility(View.INVISIBLE);
                            binding.activityDateBrace.getLayoutParams().width = 0;
                            binding.activitDateDayNumberTextView2.setVisibility(View.INVISIBLE);
                            binding.activitDateDayNumberTextView2.setWidth(0);
                            binding.activityDateMonthTextView2.setVisibility(View.INVISIBLE);
                            binding.activityDateMonthTextView2.setWidth(0);
                            binding.activiyDateDayTextview2.setVisibility(View.INVISIBLE);
                            binding.activiyDateDayTextview2.setWidth(0);


                        } else {


                            Calendar beginCal = Calendar.getInstance();
                            beginCal.setTime(activityBeginDate);

                            int dayInt = beginCal.get(Calendar.DAY_OF_MONTH);
                            String month = new SimpleDateFormat("MMM").format(beginCal.getTime());
                            String day = new SimpleDateFormat("EEEE").format(beginCal.getTime());
                            String time = new SimpleDateFormat("HH:mm").format(beginCal.getTime());

                            binding.activitDateDayNumberTextView.setText(dayInt);
                            binding.activityDateMonthTextView.setText(month);
                            binding.activiyDateDayTextview.setText(day);

                            Calendar endCal = Calendar.getInstance();
                            endCal.setTime(activityEndDate);

                            int dayIntend = endCal.get(Calendar.DAY_OF_MONTH);
                            String monthend = new SimpleDateFormat("MMM").format(endCal.getTime());
                            String dayend = new SimpleDateFormat("EEEE").format(endCal.getTime());


                            binding.activitDateDayNumberTextView2.setText(dayIntend);
                            binding.activityDateMonthTextView2.setText(monthend);
                            binding.activiyDateDayTextview2.setText(dayend);

                        }

                    }

                }

            }
        });






    }

}