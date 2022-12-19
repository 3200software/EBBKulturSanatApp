package com.software3200.ebbkultursanatapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
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

    Integer infoLayoutHeight;
    Integer descriptionLayoutHeight;
    Integer locationLayoutHeihgt;

    Boolean activityTicketFreeInfo;
    Double activityTicketStudentPrice;
    Double activityTicketAdultPrice;

    Long activityTicketClass1Price;
    String activityTicketClass1Name;
    Long activityTicketClass2Price;
    String activityTicketClass2Name;
    Long activityTicketClass3Price;
    String activityTicketClass3Name;
    Long activityTicketClass4Price ;
    String activityTicketClass4Name;


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

        binding.activityInfoLinearLayout.setVisibility(View.VISIBLE);
        binding.descriptionLinearLayout.setVisibility(View.INVISIBLE);
        binding.locationInfoLinearLayout.setVisibility(View.INVISIBLE);




        getActivityDetail();

        binding.activityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (activityTicketFreeInfo == true) {

                    Intent activityDetailToticketSelectIntent = new Intent(ActivityDetailActivity.this, TicketSelectActivity.class);
                    activityDetailToticketSelectIntent.putExtra("TicketInfo", "FreeTicket");
                    startActivity(activityDetailToticketSelectIntent);


                } else {


                    if (activityTicketStudentPrice == 0 && activityTicketAdultPrice != 0) {

                        Intent activityDetailToticketSelectIntent = new Intent(ActivityDetailActivity.this, TicketSelectActivity.class);
                        activityDetailToticketSelectIntent.putExtra("TicketInfo", "SingleTicket");
                        startActivity(activityDetailToticketSelectIntent);


                    } else if (activityTicketStudentPrice != 0 && activityTicketAdultPrice != 0) {

                        Intent activityDetailToticketSelectIntent = new Intent(ActivityDetailActivity.this, TicketSelectActivity.class);
                        activityDetailToticketSelectIntent.putExtra("TicketInfo", "AdultandStudentTicket");
                        activityDetailToticketSelectIntent.putExtra("TicketAdultPrice", activityTicketAdultPrice);
                        activityDetailToticketSelectIntent.putExtra("TicketStudentPrice",activityTicketStudentPrice);
                        startActivity(activityDetailToticketSelectIntent);


                    } else if (activityTicketStudentPrice == 0 && activityTicketAdultPrice == 0) {


                        if (activityTicketClass1Price != 0 && activityTicketClass2Price == 0  && activityTicketClass3Price == 0  && activityTicketClass4Price == 0) {


                            Intent activityDetailToticketSelectIntent = new Intent(ActivityDetailActivity.this, TicketSelectActivity.class);
                            activityDetailToticketSelectIntent.putExtra("TicketInfo", "Class1Ticket");
                            activityDetailToticketSelectIntent.putExtra("TicketName1", activityTicketClass1Name);
                            activityDetailToticketSelectIntent.putExtra("TicketPrice1",activityTicketClass1Price);
                            startActivity(activityDetailToticketSelectIntent);


                        } else if  (activityTicketClass1Price != 0 && activityTicketClass2Price != 0  && activityTicketClass3Price == 0  && activityTicketClass4Price == 0) {

                            Intent activityDetailToticketSelectIntent = new Intent(ActivityDetailActivity.this, TicketSelectActivity.class);
                            activityDetailToticketSelectIntent.putExtra("TicketInfo", "Class2Ticket");
                            activityDetailToticketSelectIntent.putExtra("TicketName1", activityTicketClass1Name);
                            activityDetailToticketSelectIntent.putExtra("TicketPrice1",activityTicketClass1Price);
                            activityDetailToticketSelectIntent.putExtra("TicketName2", activityTicketClass2Name);
                            activityDetailToticketSelectIntent.putExtra("TicketPrice2",activityTicketClass2Price);
                            startActivity(activityDetailToticketSelectIntent);


                        } else if  (activityTicketClass1Price != 0 && activityTicketClass2Price != 0  && activityTicketClass3Price != 0  && activityTicketClass4Price == 0) {

                            Intent activityDetailToticketSelectIntent = new Intent(ActivityDetailActivity.this, TicketSelectActivity.class);
                            activityDetailToticketSelectIntent.putExtra("TicketInfo", "Class3Ticket");
                            activityDetailToticketSelectIntent.putExtra("TicketName1", activityTicketClass1Name);
                            activityDetailToticketSelectIntent.putExtra("TicketPrice1",activityTicketClass1Price);
                            activityDetailToticketSelectIntent.putExtra("TicketName2", activityTicketClass2Name);
                            activityDetailToticketSelectIntent.putExtra("TicketPrice2",activityTicketClass2Price);
                            activityDetailToticketSelectIntent.putExtra("TicketName3", activityTicketClass3Name);
                            activityDetailToticketSelectIntent.putExtra("TicketPrice3",activityTicketClass3Price);
                            startActivity(activityDetailToticketSelectIntent);


                        } else if  (activityTicketClass1Price != 0 && activityTicketClass2Price != 0  && activityTicketClass3Price != 0  && activityTicketClass4Price != 0) {

                            Intent activityDetailToticketSelectIntent = new Intent(ActivityDetailActivity.this, TicketSelectActivity.class);
                            activityDetailToticketSelectIntent.putExtra("TicketInfo", "Class4Ticket");
                            activityDetailToticketSelectIntent.putExtra("TicketName1", activityTicketClass1Name);
                            activityDetailToticketSelectIntent.putExtra("TicketPrice1",activityTicketClass1Price);
                            activityDetailToticketSelectIntent.putExtra("TicketName2", activityTicketClass2Name);
                            activityDetailToticketSelectIntent.putExtra("TicketPrice2",activityTicketClass2Price);
                            activityDetailToticketSelectIntent.putExtra("TicketName3", activityTicketClass3Name);
                            activityDetailToticketSelectIntent.putExtra("TicketPrice3",activityTicketClass3Price);
                            activityDetailToticketSelectIntent.putExtra("TicketName4", activityTicketClass4Name);
                            activityDetailToticketSelectIntent.putExtra("TicketPrice4",activityTicketClass4Price);
                            startActivity(activityDetailToticketSelectIntent);

                        }

                    }

                }




            }
        });



         binding.activityInfoButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

             binding.activityInfoLinearLayout.setVisibility(View.VISIBLE);
             binding.descriptionLinearLayout.setVisibility(View.INVISIBLE);
             binding.locationInfoLinearLayout.setVisibility(View.INVISIBLE);



             }
         });

         binding.activiyDescriptionButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 binding.activityInfoLinearLayout.setVisibility(View.INVISIBLE);
                 binding.descriptionLinearLayout.setVisibility(View.VISIBLE);
                 binding.locationInfoLinearLayout.setVisibility(View.INVISIBLE);





             }
         });

         binding.activityLocationButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 binding.activityInfoLinearLayout.setVisibility(View.INVISIBLE);
                 binding.descriptionLinearLayout.setVisibility(View.INVISIBLE);
                 binding.locationInfoLinearLayout.setVisibility(View.VISIBLE);



             }
         });


    }


    public void getActivityDetail() {

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
                        String activityLocationAdress = (String) document.get("activityLocationAdressDetail");
                        Double activityLocationLatitudeLong = (Double) document.get("activityLocationLatitude");
                        Double activityLocationLongitudeLong = (Double) document.get("activityLocationLongitude");
                        String activityTelephoneNumber = (String) document.get("activiactivityTelephoneNumberTitle");

                        activityTicketFreeInfo = (Boolean)  document.get("activityTicketFreeInfo");
                        activityTicketStudentPrice = (Double)  document.get("activityTicketStudentPrice");
                        activityTicketAdultPrice = (Double)  document.get("activityTicketAdultPrice");

                        activityTicketClass1Price = (Long)  document.get("activityTicketClass1Price");
                        activityTicketClass1Name = (String)  document.get("activityTicketClass1Name");
                        activityTicketClass2Price = (Long)  document.get("activityTicketClass2Price");
                        activityTicketClass2Name = (String)  document.get("activityTicketClass2Name");
                        activityTicketClass3Price = (Long)  document.get("activityTicketClass3Price");
                        activityTicketClass3Name = (String)  document.get("activityTicketClass3Name");
                        activityTicketClass4Price = (Long)  document.get("activityTicketClass4Price");
                        activityTicketClass4Name = (String)  document.get("activityTicketClass4Name");


                        if (activityTicketFreeInfo == true) {

                            binding.activityPriceInfoDetailTextView.setText("Ücretsiz");


                        } else {


                            if (activityTicketStudentPrice == 0 && activityTicketAdultPrice != 0) {

                                binding.activityPriceInfoDetailTextView.setText(activityTicketAdultPrice + " TL");


                            } else if (activityTicketStudentPrice != 0 && activityTicketAdultPrice != 0) {

                                binding.activityPriceInfoDetailTextView.setText("Tam : " + activityTicketAdultPrice + " TL\n" + "Öğrenci : " + activityTicketStudentPrice + " TL" );


                            } else if (activityTicketStudentPrice == 0 && activityTicketAdultPrice == 0) {


                                if (activityTicketClass1Price != 0 && activityTicketClass2Price == 0  && activityTicketClass3Price == 0  && activityTicketClass4Price == 0) {


                                    binding.activityPriceInfoDetailTextView.setText(activityTicketClass1Name + " : " + activityTicketClass1Price + " TL\n" );


                                } else if  (activityTicketClass1Price != 0 && activityTicketClass2Price != 0  && activityTicketClass3Price == 0  && activityTicketClass4Price == 0) {

                                    binding.activityPriceInfoDetailTextView.setText(activityTicketClass1Name + " : " + activityTicketClass1Price + " TL\n" + activityTicketClass2Name + " : " + activityTicketClass2Price + " TL\n"  );


                                } else if  (activityTicketClass1Price != 0 && activityTicketClass2Price != 0  && activityTicketClass3Price != 0  && activityTicketClass4Price == 0) {

                                    binding.activityPriceInfoDetailTextView.setText(activityTicketClass1Name + " : " + activityTicketClass1Price + " TL\n" + activityTicketClass2Name + " : " + activityTicketClass2Price + " TL\n" + activityTicketClass3Name + " : " + activityTicketClass3Price + " TL\n" );


                                } else if  (activityTicketClass1Price != 0 && activityTicketClass2Price != 0  && activityTicketClass3Price != 0  && activityTicketClass4Price != 0) {

                                    binding.activityPriceInfoDetailTextView.setText(activityTicketClass1Name + " : " + activityTicketClass1Price + " TL\n" + activityTicketClass2Name + " : " + activityTicketClass2Price + " TL\n" + activityTicketClass3Name + " : " + activityTicketClass3Price + " TL\n"  + activityTicketClass4Name + " : " + activityTicketClass4Price + " TL\n" );


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
                        binding.activityAdressDescriptionDetailTextView.setText(activityLocationAdress);








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



                            binding.secondMonthAndDayLinear.setVisibility(View.INVISIBLE);
                            binding.secondMonthAndDayLinear.getLayoutParams().width = 0;
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