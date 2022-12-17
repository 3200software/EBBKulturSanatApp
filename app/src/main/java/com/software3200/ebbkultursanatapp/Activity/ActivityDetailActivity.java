package com.software3200.ebbkultursanatapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.software3200.ebbkultursanatapp.R;
import com.software3200.ebbkultursanatapp.databinding.ActivityDetailBinding;
import com.software3200.ebbkultursanatapp.databinding.ActivityMainBinding;
import com.squareup.picasso.Picasso;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ActivityDetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;

    FirebaseFirestore firebaseFirestore;

    String activityDocumentID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseFirestore = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        activityDocumentID = intent.getStringExtra("ActivityDocumentId");

        binding.activityButton.setText("Bilet Al");


         getActivityDetail();



    }


    public void getActivityDetail() {

        System.out.println("did" + activityDocumentID);

        firebaseFirestore.collection("Events").document("AMrY2Kus3303A2hs8C6p").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        String activityImgUrl = (String) document.get("activityImgUrl");
                        String activitytitle = (String) document.get("activityTitle");
                        String activityDetail = (String) document.get("activityDetail");
                        String activityDetailTitle = (String) document.get("activityDetailTitle");
                        String activityCategory = (String) document.get("activityCategory");
                        String activityOrganization = (String) document.get("activityOrganization");
                        Timestamp activityDateTimestamp = (Timestamp) document.get("activityDate");
                        Timestamp activityBeginDateTimeStamp = (Timestamp) document.get("activityBeginDate");
                        Timestamp activityEndDateTimestamp = (Timestamp) document.get("activityEndDate");

                        String activityLocation = (String) document.get("activityLocation");
                        String activityLocationAdress = (String) document.get("activityLocationAdress");
                        Double activityLocationLatitudeLong = (Double) document.get("activityLocationLatitude");
                        Double activityLocationLongitudeLong = (Double) document.get("activityLocationLongitude");
                        String activityTelephoneNumber = (String) document.get("activiactivityTelephoneNumberTitle");

                        Boolean activityTicketFreeInfo = (Boolean)  document.get("activityTicketFreeInfo");
                        Double activityTicketStudentPrice = (Double)  document.get("activityTicketStudentPrice");
                        Double activityTicketAdultPrice = (Double)  document.get("activityTicketAdultPrice");

                        Long activityTicketClass1Price = (Long)  document.get("activityTicketClass1Price");
                        String activityTicketClass1Name = (String)  document.get("activityTicketClass1Name");
                        Long activityTicketClass2Price = (Long)  document.get("activityTicketClass2Price");
                        String activityTicketClass2Name = (String)  document.get("activityTicketClass2Name");
                        Long activityTicketClass3Price = (Long)  document.get("activityTicketClass3Price");
                        String activityTicketClass3Name = (String)  document.get("activityTicketClass3Name");
                        Long activityTicketClass4Price = (Long)  document.get("activityTicketClass4Price");
                        String activityTicketClass4Name = (String)  document.get("activityTicketClass4Name");







                        if (activityTicketFreeInfo == true) {

                            binding.activityPriceInfoDetailTextView.setText("Ücretsiz");


                        } else {


                            if (activityTicketStudentPrice == 0 && activityTicketAdultPrice != 0) {

                                binding.activityPriceInfoDetailTextView.setText(activityTicketAdultPrice + " TL");


                            } else if (activityTicketStudentPrice != 0 && activityTicketAdultPrice != 0) {

                                binding.activityPriceInfoDetailTextView.setText("Tam : " + activityTicketAdultPrice + " TL\n" );
                                binding.activityPriceInfoDetailTextView.setText("Öğrenci : " + activityTicketStudentPrice + " TL");

                            } else if (activityTicketStudentPrice == 0 && activityTicketAdultPrice == 0) {


                                if (activityTicketClass1Price != 0 && activityTicketClass2Price == 0  && activityTicketClass3Price == 0  && activityTicketClass4Price == 0) {


                                    binding.activityPriceInfoDetailTextView.setText(activityTicketClass1Name + " : " + activityTicketClass1Price + " TL\n" );


                                } else if  (activityTicketClass1Price != 0 && activityTicketClass2Price != 0  && activityTicketClass3Price == 0  && activityTicketClass4Price == 0) {

                                    binding.activityPriceInfoDetailTextView.setText(activityTicketClass1Name + " : " + activityTicketClass1Price + " TL\n" );
                                    binding.activityPriceInfoDetailTextView.setText(activityTicketClass2Name + " : " + activityTicketClass2Price + " TL\n" );

                                } else if  (activityTicketClass1Price != 0 && activityTicketClass2Price != 0  && activityTicketClass3Price != 0  && activityTicketClass4Price == 0) {

                                    binding.activityPriceInfoDetailTextView.setText(activityTicketClass1Name + " : " + activityTicketClass1Price + " TL\n" );
                                    binding.activityPriceInfoDetailTextView.setText(activityTicketClass2Name + " : " + activityTicketClass2Price + " TL\n" );
                                    binding.activityPriceInfoDetailTextView.setText(activityTicketClass3Name + " : " + activityTicketClass3Price + " TL\n" );

                                } else if  (activityTicketClass1Price != 0 && activityTicketClass2Price != 0  && activityTicketClass3Price != 0  && activityTicketClass4Price != 0) {

                                    binding.activityPriceInfoDetailTextView.setText(activityTicketClass1Name + " : " + activityTicketClass1Price + " TL\n" );
                                    binding.activityPriceInfoDetailTextView.setText(activityTicketClass2Name + " : " + activityTicketClass2Price + " TL\n" );
                                    binding.activityPriceInfoDetailTextView.setText(activityTicketClass3Name + " : " + activityTicketClass3Price + " TL\n" );
                                    binding.activityPriceInfoDetailTextView.setText(activityTicketClass4Name + " : " + activityTicketClass4Price + " TL\n" );

                                }

                            }

                        }



                        Picasso.get().load(activityImgUrl).into(binding.activityImageView);
                        binding.activityTitleTextView.setText(activitytitle);
                        binding.activityLocationTextView.setText(activityLocation);
                        binding.activityCategoryTextView.setText(activityCategory);
                        binding.activityDetailTitleTextView.setText(activityDetailTitle);
                        binding.activityDetailTextView.setText(activityDetail);
                        binding.activityOrganizationCompanyDetailTextView.setText(activityOrganization);
                        binding.activityAdressDescriptionDetailTextView.setText(activityLocationAdress);
                        binding.activityDetailPhoneNumberTextView.setText(activityTelephoneNumber);






                        if (activityEndDateTimestamp.getSeconds() < 1262296800) {

                            Date activityDate = activityDateTimestamp.toDate();

                            System.out.println("date" + activityDate);

                            Calendar cal = Calendar.getInstance();
                            cal.setTime(activityDate);


                            int dayInt = cal.get(Calendar.DAY_OF_MONTH);
                            String month = new SimpleDateFormat("MMM").format(cal.getTime());
                            String day = new SimpleDateFormat("EEEE").format(cal.getTime());
                            String time = new SimpleDateFormat("HH:mm").format(cal.getTime());

                            binding.activitDateDayNumberTextView.setText(String.valueOf(dayInt));
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

                            Date activityBeginDate = activityBeginDateTimeStamp.toDate();

                            Calendar beginCal = Calendar.getInstance();
                            beginCal.setTime(activityBeginDate);

                            int dayInt = beginCal.get(Calendar.DAY_OF_MONTH);
                            String month = new SimpleDateFormat("MMM").format(beginCal.getTime());
                            String day = new SimpleDateFormat("EEEE").format(beginCal.getTime());
                            String time = new SimpleDateFormat("HH:mm").format(beginCal.getTime());

                            binding.activitDateDayNumberTextView.setText(dayInt);
                            binding.activityDateMonthTextView.setText(month);
                            binding.activiyDateDayTextview.setText(day);

                            Date activityEndDate = activityEndDateTimestamp.toDate();

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