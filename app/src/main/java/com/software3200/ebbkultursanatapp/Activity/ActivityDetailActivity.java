package com.software3200.ebbkultursanatapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
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
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class ActivityDetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;

    String selectActivityDocumentID;
    String selectActivityName;
    String selectActivityImgUrl;
    String selectActivitTimeString;
    String ticketSerialnumber;

    String activityLocation;

    Timestamp activityDateTimestampPublic;



    Integer infoLayoutHeight;
    Integer descriptionLayoutHeight;
    Integer locationLayoutHeihgt;

    String activityTicketInfo;
    Long activityTicketClass1PriceLong;
    Double activityTicketClass1Price;
    String activityTicketClass1Name;
    Long activityTicketClass2PriceLong;
    Double activityTicketClass2Price;
    String activityTicketClass2Name;
    Long activityTicketClass3PriceLong;
    Double activityTicketClass3Price;
    String activityTicketClass3Name;
    Long activityTicketClass4PriceLong;
    Double activityTicketClass4Price;
    String activityTicketClass4Name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        selectActivityDocumentID = intent.getStringExtra("ActivityDocumentId");

        binding.activityButton.setText("Bilet Al");

        binding.activityInfoLinearLayout.setVisibility(View.VISIBLE);
        binding.descriptionLinearLayout.setVisibility(View.INVISIBLE);
        binding.locationInfoLinearLayout.setVisibility(View.INVISIBLE);




        getActivityDetail();

        System.out.println(activityTicketClass1Price);

        binding.activityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Random TicketSerial = new Random();
                ticketSerialnumber = "EBB-" + TicketSerial.nextInt(100000000);

                HashMap<String, Object> ticketInfo = new HashMap<>();

                ticketInfo.put("ticketSerialNumber",ticketSerialnumber);
                ticketInfo.put("totalTicketPiece",0);
                ticketInfo.put("totalTicketPrice", 0.0);
                ticketInfo.put("activityName",selectActivityName);
                ticketInfo.put("activityTime",selectActivitTimeString);
                ticketInfo.put("activitySaloon",activityLocation);
                ticketInfo.put("ticketQrCode","");
                ticketInfo.put("ticketQrImage","");
                ticketInfo.put("ticketSeatName","");
                ticketInfo.put("ticketImgUrl",selectActivityImgUrl);
                ticketInfo.put("ticketSuccess",false);
                ticketInfo.put("ticketDate", activityDateTimestampPublic);


                firebaseFirestore.collection("Users").document(firebaseAuth.getCurrentUser().getEmail()).collection("Tickets").document(ticketSerialnumber).set(ticketInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {

                            if (activityTicketInfo.equals("1")) {

                                System.out.println(activityTicketInfo);
                                Intent activityDetailToticketSelectIntent = new Intent(ActivityDetailActivity.this, TicketSelectActivity.class);
                                activityDetailToticketSelectIntent.putExtra("TicketInfo", "FreeTicket");
                                activityDetailToticketSelectIntent.putExtra("ActivityDocumentId", selectActivityDocumentID);
                                activityDetailToticketSelectIntent.putExtra("selectActivityName", selectActivityName);
                                activityDetailToticketSelectIntent.putExtra("selectActivityImageURL",selectActivityImgUrl);
                                activityDetailToticketSelectIntent.putExtra("selectActivityTimeString",selectActivitTimeString);
                                activityDetailToticketSelectIntent.putExtra("ticketSerialNumber",ticketSerialnumber);
                                startActivity(activityDetailToticketSelectIntent);


                            } else if (activityTicketInfo.equals("2")) {

                                Intent activityDetailToticketSelectIntent = new Intent(ActivityDetailActivity.this, TicketSelectActivity.class);
                                activityDetailToticketSelectIntent.putExtra("TicketInfo", "SingleTicket");
                                activityDetailToticketSelectIntent.putExtra("TicketSinglePrice", activityTicketClass1Price );
                                activityDetailToticketSelectIntent.putExtra("ActivityDocumentId", selectActivityDocumentID);
                                activityDetailToticketSelectIntent.putExtra("selectActivityName", selectActivityName);
                                activityDetailToticketSelectIntent.putExtra("selectActivityImageURL",selectActivityImgUrl);
                                activityDetailToticketSelectIntent.putExtra("selectActivityTimeString",selectActivitTimeString);
                                activityDetailToticketSelectIntent.putExtra("ticketSerialNumber",ticketSerialnumber);
                                startActivity(activityDetailToticketSelectIntent);

                            } else if (activityTicketInfo.equals("3")) {

                                Intent activityDetailToticketSelectIntent = new Intent(ActivityDetailActivity.this, TicketSelectActivity.class);
                                activityDetailToticketSelectIntent.putExtra("TicketInfo", "AdultandStudentTicket");
                                activityDetailToticketSelectIntent.putExtra("TicketAdultPrice", activityTicketClass1Price);
                                activityDetailToticketSelectIntent.putExtra("TicketStudentPrice",activityTicketClass2Price);
                                activityDetailToticketSelectIntent.putExtra("ActivityDocumentId", selectActivityDocumentID);
                                activityDetailToticketSelectIntent.putExtra("selectActivityName", selectActivityName);
                                activityDetailToticketSelectIntent.putExtra("selectActivityImageURL",selectActivityImgUrl);
                                activityDetailToticketSelectIntent.putExtra("selectActivityTimeString",selectActivitTimeString);
                                activityDetailToticketSelectIntent.putExtra("ticketSerialNumber",ticketSerialnumber);
                                startActivity(activityDetailToticketSelectIntent);


                            } else if (activityTicketInfo.equals("4")) {

                                Intent activityDetailToticketSelectIntent = new Intent(ActivityDetailActivity.this, TicketSelectActivity.class);
                                activityDetailToticketSelectIntent.putExtra("TicketInfo", "Class1Ticket");
                                activityDetailToticketSelectIntent.putExtra("TicketName1", activityTicketClass1Name);
                                activityDetailToticketSelectIntent.putExtra("TicketPrice1",activityTicketClass1Price);
                                activityDetailToticketSelectIntent.putExtra("ActivityDocumentId", selectActivityDocumentID);
                                activityDetailToticketSelectIntent.putExtra("selectActivityName", selectActivityName);
                                activityDetailToticketSelectIntent.putExtra("selectActivityImageURL",selectActivityImgUrl);
                                activityDetailToticketSelectIntent.putExtra("selectActivityTimeString",selectActivitTimeString);
                                activityDetailToticketSelectIntent.putExtra("ticketSerialNumber",ticketSerialnumber);
                                startActivity(activityDetailToticketSelectIntent);


                            } else if  (activityTicketInfo.equals("5")) {

                                Intent activityDetailToticketSelectIntent = new Intent(ActivityDetailActivity.this, TicketSelectActivity.class);
                                activityDetailToticketSelectIntent.putExtra("TicketInfo", "Class2Ticket");
                                activityDetailToticketSelectIntent.putExtra("TicketName1", activityTicketClass1Name);
                                activityDetailToticketSelectIntent.putExtra("TicketPrice1",activityTicketClass1Price);
                                activityDetailToticketSelectIntent.putExtra("TicketName2", activityTicketClass2Name);
                                activityDetailToticketSelectIntent.putExtra("TicketPrice2",activityTicketClass2Price);
                                activityDetailToticketSelectIntent.putExtra("ActivityDocumentId", selectActivityDocumentID);
                                activityDetailToticketSelectIntent.putExtra("selectActivityName", selectActivityName);
                                activityDetailToticketSelectIntent.putExtra("selectActivityImageURL",selectActivityImgUrl);
                                activityDetailToticketSelectIntent.putExtra("selectActivityTimeString",selectActivitTimeString);
                                activityDetailToticketSelectIntent.putExtra("ticketSerialNumber",ticketSerialnumber);
                                startActivity(activityDetailToticketSelectIntent);


                            } else if  (activityTicketInfo.equals("6")) {

                                Intent activityDetailToticketSelectIntent = new Intent(ActivityDetailActivity.this, TicketSelectActivity.class);
                                activityDetailToticketSelectIntent.putExtra("TicketInfo", "Class3Ticket");
                                activityDetailToticketSelectIntent.putExtra("TicketName1", activityTicketClass1Name);
                                activityDetailToticketSelectIntent.putExtra("TicketPrice1",activityTicketClass1Price);
                                activityDetailToticketSelectIntent.putExtra("TicketName2", activityTicketClass2Name);
                                activityDetailToticketSelectIntent.putExtra("TicketPrice2",activityTicketClass2Price);
                                activityDetailToticketSelectIntent.putExtra("TicketName3", activityTicketClass3Name);
                                activityDetailToticketSelectIntent.putExtra("TicketPrice3",activityTicketClass3Price);
                                activityDetailToticketSelectIntent.putExtra("ActivityDocumentId", selectActivityDocumentID);
                                activityDetailToticketSelectIntent.putExtra("selectActivityName", selectActivityName);
                                activityDetailToticketSelectIntent.putExtra("selectActivityImageURL",selectActivityImgUrl);
                                activityDetailToticketSelectIntent.putExtra("selectActivityTimeString",selectActivitTimeString);
                                activityDetailToticketSelectIntent.putExtra("ticketSerialNumber",ticketSerialnumber);
                                startActivity(activityDetailToticketSelectIntent);


                            } else if  (activityTicketInfo.equals("7")) {

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
                                activityDetailToticketSelectIntent.putExtra("ActivityDocumentId", selectActivityDocumentID);
                                activityDetailToticketSelectIntent.putExtra("selectActivityName", selectActivityName);
                                activityDetailToticketSelectIntent.putExtra("selectActivityImageURL",selectActivityImgUrl);
                                activityDetailToticketSelectIntent.putExtra("selectActivityTimeString",selectActivitTimeString);
                                activityDetailToticketSelectIntent.putExtra("ticketSerialNumber",ticketSerialnumber);
                                startActivity(activityDetailToticketSelectIntent);

                            }



                        } else {


                            Toast.makeText(ActivityDetailActivity.this, "İnternet bağlantınızda bir problem olabilir! Lütfen daha sonra tekrar deneyin.", Toast.LENGTH_LONG).show();


                        }




                    }
                });



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

        firebaseFirestore.collection("Events").document(selectActivityDocumentID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        selectActivityName = (String) document.get("activityName");
                        String activityCategory = (String) document.get("activityCategory");
                        activityLocation = (String) document.get("activityLocation");

                        Timestamp activityDateTimestamp = (Timestamp) document.get("activityDate");
                        Timestamp activityBeginDateTimeStamp = (Timestamp) document.get("activityBeginDate");
                        Timestamp activityEndDateTimestamp = (Timestamp) document.get("activityEndDate");
                        String activityDescription = (String) document.get("activityDescription");

                        String activityImgUrl1 = (String) document.get("activityImgUrl1");
                        selectActivityImgUrl= (String) document.get("activityImgUrl2");

                        String activityLocationAdressDetail = (String) document.get("activityLocationAdressDetail");
                        Double activityLocationLatitude = (Double) document.get("activityLocationLatitude");
                        Double activityLocationLongitude = (Double) document.get("activityLocationLongitude");

                        String activityOrganization = (String) document.get("activityOrganization");
                        String activityTelephoneNumber = (String) document.get("activityTelephoneNumber");




                        activityTicketInfo = (String)  document.get("activityTicketInfo");
                        activityTicketClass1PriceLong = (Long)  document.get("activityTicketClass1Price");
                        activityTicketClass1Name = (String)  document.get("activityTicketClass1Name");
                        activityTicketClass2PriceLong = (Long)  document.get("activityTicketClass2Price");
                        activityTicketClass2Name = (String)  document.get("activityTicketClass2Name");
                        activityTicketClass3PriceLong = (Long)  document.get("activityTicketClass3Price");
                        activityTicketClass3Name = (String)  document.get("activityTicketClass3Name");
                        activityTicketClass4PriceLong = (Long)  document.get("activityTicketClass4Price");
                        activityTicketClass4Name = (String)  document.get("activityTicketClass4Name");

                        activityTicketClass1Price = activityTicketClass1PriceLong.doubleValue();
                        activityTicketClass2Price = activityTicketClass2PriceLong.doubleValue();
                        activityTicketClass3Price = activityTicketClass3PriceLong.doubleValue();
                        activityTicketClass4Price = activityTicketClass4PriceLong.doubleValue();

                        if (activityTicketInfo.equals("1")) {

                            binding.activityPriceInfoDetailTextView.setText("Ücretsiz");


                        } else if (activityTicketInfo.equals("2")) {

                            binding.activityPriceInfoDetailTextView.setText(activityTicketClass1Price + " TL");


                        } else if (activityTicketInfo.equals("3"))    {


                            binding.activityPriceInfoDetailTextView.setText(activityTicketClass1Name + " : " + activityTicketClass1Price + " TL\n" + activityTicketClass2Name + " : " + activityTicketClass2Price + " TL" );

                            } else if (activityTicketInfo.equals("4")) {


                            binding.activityPriceInfoDetailTextView.setText(activityTicketClass1Name + " : " + activityTicketClass1Price + " TL\n" );


                        } else if (activityTicketInfo.equals("5")) {

                            binding.activityPriceInfoDetailTextView.setText(activityTicketClass1Name + " : " + activityTicketClass1Price + " TL\n" + activityTicketClass2Name + " : " + activityTicketClass2Price + " TL\n");


                        } else if (activityTicketInfo.equals("6")) {


                            binding.activityPriceInfoDetailTextView.setText(activityTicketClass1Name + " : " + activityTicketClass1Price + " TL\n" + activityTicketClass2Name + " : " + activityTicketClass2Price + " TL\n" + activityTicketClass3Name + " : " + activityTicketClass3Price + " TL\n" );


                        } else if (activityTicketInfo.equals("7")) {


                            binding.activityPriceInfoDetailTextView.setText(activityTicketClass1Name + " : " + activityTicketClass1Price + " TL\n" + activityTicketClass2Name + " : " + activityTicketClass2Price + " TL\n" + activityTicketClass3Name + " : " + activityTicketClass3Price + " TL\n"  + activityTicketClass4Name + " : " + activityTicketClass4Price + " TL\n" );

                        }


                        Picasso.get().load(selectActivityImgUrl).into(binding.activityImageView);
                        binding.activityTitleTextView.setText(selectActivityName);
                        binding.activityLocationTextView.setText(activityLocation);
                        binding.activityCategoryTextView.setText(activityCategory);

                        binding.activityDetailTextView.setText(activityDescription);
                        binding.activityOrganizationCompanyDetailTextView.setText(activityOrganization);
                        binding.activityAdressDescriptionDetailTextView.setText(activityLocationAdressDetail);
                        binding.activityDetailPhoneNumberTextView.setText(activityTelephoneNumber);


                        if (activityEndDateTimestamp.getSeconds() > 1262296800) {

                            Date activityDate = activityDateTimestamp.toDate();

                            Calendar cal = Calendar.getInstance();
                            cal.setTime(activityDate);


                            int dayInt = cal.get(Calendar.DAY_OF_MONTH);
                            String month = new SimpleDateFormat("MMMM").format(cal.getTime());
                            String day = new SimpleDateFormat("EEEE").format(cal.getTime());
                            String time = new SimpleDateFormat("HH:mm").format(cal.getTime());


                            binding.activitDateDayNumberTextView.setText(String.valueOf(dayInt));
                            binding.activityDateMonthTextView.setText(month);
                            binding.activiyDateDayTextview.setText(day);
                            binding.activityTimeTextView.setText(time);

                            selectActivitTimeString = (String.valueOf(dayInt)) + " " + month + " \n" + day + " " + time;
                            activityDateTimestampPublic = activityDateTimestamp;

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

                            int dayIntBegin = beginCal.get(Calendar.DAY_OF_MONTH);
                            String month = new SimpleDateFormat("MMM").format(beginCal.getTime());
                            String day = new SimpleDateFormat("EEEE").format(beginCal.getTime());
                            String time = new SimpleDateFormat("HH:mm").format(beginCal.getTime());

                            binding.activitDateDayNumberTextView.setText(dayIntBegin);
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