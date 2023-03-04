package com.software3200.ebbkultursanatapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.protobuf.StringValue;
import com.software3200.ebbkultursanatapp.R;
import com.software3200.ebbkultursanatapp.databinding.ActivityTicketSelectBinding;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class TicketSelectActivity extends AppCompatActivity {

    ActivityTicketSelectBinding binding;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    String selectActivityDocumentID;
    String selectActivityName;
    String selectActivityImgUrl;
    String selectActivitTimeString;

    String ticketSerialnumber;




    String ticketInfo;
    String class1TicketName;
    Double class1TicketPrice;
    String class2TicketName;
    Double class2TicketPrice;
    String class3TicketName;
    Double class3TicketPrice;
    String class4TicketName;
    Double class4TicketPrice;

    Double ticketClass1Piece = 0.0;
    Double ticketClass2Piece = 0.0;
    Double ticketClass3Piece = 0.0;
    Double ticketClass4Piece = 0.0;

    Double totalTicketPrice = 0.0;
    Double totalTicketPiece = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_select);

        binding = ActivityTicketSelectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();


        Intent activityDetailToTicketSelectIntent = getIntent();
        ticketInfo = activityDetailToTicketSelectIntent.getStringExtra("TicketInfo");
        selectActivityDocumentID = activityDetailToTicketSelectIntent.getStringExtra("ActivityDocumentId");
        selectActivityName = activityDetailToTicketSelectIntent.getStringExtra("selectActivityName");
        selectActivityImgUrl = activityDetailToTicketSelectIntent.getStringExtra("selectActivityImageURL");
        selectActivitTimeString = activityDetailToTicketSelectIntent.getStringExtra("selectActivityTimeString");
        ticketSerialnumber =activityDetailToTicketSelectIntent.getStringExtra("ticketSerialNumber");


        System.out.println("heyy" + selectActivityImgUrl + selectActivitTimeString + selectActivityName);



        Picasso.get().load(selectActivityImgUrl).into(binding.activityImageView);
        binding.activityTitleTextview.setText(selectActivityName);
        binding.activityTimeTextview.setText(selectActivitTimeString);






        if (ticketInfo.equals("FreeTicket"))  {

            binding.ticketSelect1TitleTextView.setText("Bilet");

            binding.ticketSelect2Layout.setVisibility(View.INVISIBLE);
            binding.ticketSelect3Layout.setVisibility(View.INVISIBLE);
            binding.ticketSelect4Layout.setVisibility(View.INVISIBLE);
            binding.ticketPriceTextView.setText("Ücretsiz");
            class1TicketPrice = 0.0;
            class2TicketPrice = 0.0;
            class3TicketPrice = 0.0;
            class4TicketPrice = 0.0;


        } else if (ticketInfo.equals("SingleTicket")) {

            binding.ticketSelect1TitleTextView.setText("Bilet");

            binding.ticketSelect2Layout.setVisibility(View.INVISIBLE);
            binding.ticketSelect3Layout.setVisibility(View.INVISIBLE);
            binding.ticketSelect4Layout.setVisibility(View.INVISIBLE);

            class1TicketPrice = activityDetailToTicketSelectIntent.getDoubleExtra("TicketSinglePrice",0.0);

            class2TicketPrice = 0.0;
            class3TicketPrice = 0.0;
            class4TicketPrice = 0.0;


        } else if (ticketInfo.equals("AdultandStudentTicket")) {

            binding.ticketSelect1TitleTextView.setText("Tam");
            binding.ticketSelect2TitleTextView.setText("Öğrenci");

            binding.ticketSelect3Layout.setVisibility(View.INVISIBLE);
            binding.ticketSelect4Layout.setVisibility(View.INVISIBLE);

            class1TicketPrice = activityDetailToTicketSelectIntent.getDoubleExtra("TicketAdultPrice",0.0);


            class2TicketPrice = activityDetailToTicketSelectIntent.getDoubleExtra("TicketStudentPrice",0.0);


            class3TicketPrice = 0.0;
            class4TicketPrice = 0.0;


        } else if (ticketInfo.equals("Class1Ticket")) {

            class1TicketName = activityDetailToTicketSelectIntent.getStringExtra("TicketName1");

            binding.ticketSelect1TitleTextView.setText("Bilet");

            binding.ticketSelect2Layout.setVisibility(View.INVISIBLE);
            binding.ticketSelect3Layout.setVisibility(View.INVISIBLE);
            binding.ticketSelect4Layout.setVisibility(View.INVISIBLE);

            class1TicketPrice = activityDetailToTicketSelectIntent.getDoubleExtra("TicketPrice1",0.0);


            class2TicketPrice = 0.0;
            class3TicketPrice = 0.0;
            class4TicketPrice = 0.0;



        } else if (ticketInfo.equals("Class2Ticket")) {

            class1TicketName = activityDetailToTicketSelectIntent.getStringExtra("TicketName1");
            class2TicketName = activityDetailToTicketSelectIntent.getStringExtra("TicketName2");

            binding.ticketSelect1TitleTextView.setText(class1TicketName);
            binding.ticketSelect2TitleTextView.setText(class2TicketName);

            binding.ticketSelect3Layout.setVisibility(View.INVISIBLE);
            binding.ticketSelect4Layout.setVisibility(View.INVISIBLE);

            class1TicketPrice = activityDetailToTicketSelectIntent.getDoubleExtra("TicketPrice1",0.0);


            class2TicketPrice = activityDetailToTicketSelectIntent.getDoubleExtra("TicketPrice2",0.0);


            class3TicketPrice = 0.0;
            class4TicketPrice = 0.0;




        }  else if (ticketInfo.equals("Class3Ticket")) {

            class1TicketName = activityDetailToTicketSelectIntent.getStringExtra("TicketName1");
            class2TicketName = activityDetailToTicketSelectIntent.getStringExtra("TicketName2");
            class3TicketName = activityDetailToTicketSelectIntent.getStringExtra("TicketName3");

            binding.ticketSelect1TitleTextView.setText(class1TicketName);
            binding.ticketSelect2TitleTextView.setText(class2TicketName);
            binding.ticketSelect3TitleTextView.setText(class3TicketName);

            binding.ticketSelect4Layout.setVisibility(View.INVISIBLE);

            class1TicketPrice = activityDetailToTicketSelectIntent.getDoubleExtra("TicketPrice1",0.0);

            class2TicketPrice = activityDetailToTicketSelectIntent.getDoubleExtra("TicketPrice2",0.0);

            class3TicketPrice = activityDetailToTicketSelectIntent.getDoubleExtra("TicketPrice3",0.0);

            class4TicketPrice = 0.0;



        }   else if (ticketInfo.equals("Class4Ticket")) {

            class1TicketName = activityDetailToTicketSelectIntent.getStringExtra("TicketName1");
            class2TicketName = activityDetailToTicketSelectIntent.getStringExtra("TicketName2");
            class3TicketName = activityDetailToTicketSelectIntent.getStringExtra("TicketName3");
            class4TicketName = activityDetailToTicketSelectIntent.getStringExtra("TicketName4");

            binding.ticketSelect1TitleTextView.setText(class1TicketName);
            binding.ticketSelect2TitleTextView.setText(class2TicketName);
            binding.ticketSelect3TitleTextView.setText(class3TicketName);
            binding.ticketSelect3TitleTextView.setText(class4TicketName);

            class1TicketPrice = activityDetailToTicketSelectIntent.getDoubleExtra("TicketPrice1",0.0);

            class2TicketPrice = activityDetailToTicketSelectIntent.getDoubleExtra("TicketPrice2",0.0);

            class3TicketPrice = activityDetailToTicketSelectIntent.getDoubleExtra("TicketPrice3",0.0);

            class4TicketPrice = activityDetailToTicketSelectIntent.getDoubleExtra("TicketPrice4",0.0);




        }


        binding.ticketselect1AddPositiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (totalTicketPiece >= 9) {


                    Toast.makeText(TicketSelectActivity.this,"En fazla 9 adet bilet alabilirsiniz!!",Toast.LENGTH_LONG).show();

                } else {



                    ticketClass1Piece = ticketClass1Piece + 1.0;
                    totalTicketPiece = ticketClass1Piece + ticketClass2Piece + ticketClass3Piece + ticketClass4Piece;
                    Integer pieceInt = ticketClass1Piece.intValue();
                    binding.ticketSelect1NumberTextview.setText(String.valueOf(pieceInt));
                    System.out.println("heyy" + totalTicketPiece);

                    Double class1Price = ticketClass1Piece * class1TicketPrice;
                    Double class2Price = ticketClass2Piece * class2TicketPrice;
                    Double class3Price = ticketClass3Piece * class3TicketPrice;
                    Double class4Price = ticketClass4Piece * class4TicketPrice;

                    totalTicketPrice = class1Price + class2Price + class3Price + class4Price;

                    binding.ticketPriceTextView.setText(String.valueOf(totalTicketPrice) + " TL");

                }


            }
        });

        binding.ticketSelect1NegativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (totalTicketPiece == 0.0) {



                } else {

                    ticketClass1Piece = ticketClass1Piece - 1.0;
                    totalTicketPiece = ticketClass1Piece + ticketClass2Piece + ticketClass3Piece + ticketClass4Piece;
                    Integer pieceInt = ticketClass1Piece.intValue();
                    binding.ticketSelect1NumberTextview.setText(String.valueOf(pieceInt));
                    System.out.println("heyy" + totalTicketPiece);
                    Double class1Price = ticketClass1Piece * class1TicketPrice;
                    Double class2Price = ticketClass2Piece * class2TicketPrice;
                    Double class3Price = ticketClass3Piece * class3TicketPrice;
                    Double class4Price = ticketClass4Piece * class4TicketPrice;

                    totalTicketPrice = class1Price + class2Price + class3Price + class4Price;

                    binding.ticketPriceTextView.setText(String.valueOf(totalTicketPrice) + " TL");

                }





            }
        });

        binding.ticketselect2AddPositiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (totalTicketPiece >= 9) {


                    Toast.makeText(TicketSelectActivity.this,"En fazla 9 adet bilet alabilirsiniz!!",Toast.LENGTH_LONG).show();

                } else {

                    ticketClass2Piece = ticketClass2Piece + 1.0;
                    totalTicketPiece = ticketClass1Piece + ticketClass2Piece + ticketClass3Piece + ticketClass4Piece;
                    Integer pieceInt = ticketClass2Piece.intValue();
                    binding.ticketSelect2NumberTextview.setText(String.valueOf(pieceInt));

                    Double class1Price = ticketClass1Piece * class1TicketPrice;
                    Double class2Price = ticketClass2Piece * class2TicketPrice;
                    Double class3Price = ticketClass3Piece * class3TicketPrice;
                    Double class4Price = ticketClass4Piece * class4TicketPrice;

                    totalTicketPrice = class1Price + class2Price + class3Price + class4Price;



                    binding.ticketPriceTextView.setText(String.valueOf(totalTicketPrice) + " TL");

                }


            }
        });

        binding.ticketSelect2NegativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (totalTicketPiece == 0) {



                } else {

                    ticketClass2Piece = ticketClass2Piece - 1.0;
                    totalTicketPiece = ticketClass1Piece + ticketClass2Piece + ticketClass3Piece + ticketClass4Piece;
                    Integer pieceInt = ticketClass2Piece.intValue();
                    binding.ticketSelect2NumberTextview.setText(String.valueOf(pieceInt));

                    Double class1Price = ticketClass1Piece * class1TicketPrice;
                    Double class2Price = ticketClass2Piece * class2TicketPrice;
                    Double class3Price = ticketClass3Piece * class3TicketPrice;
                    Double class4Price = ticketClass4Piece * class4TicketPrice;

                    totalTicketPrice = class1Price + class2Price + class3Price + class4Price;

                    binding.ticketPriceTextView.setText(String.valueOf(totalTicketPrice) + " TL");

                }





            }
        });

        binding.ticketselect3AddPositiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (totalTicketPiece >= 9) {


                    Toast.makeText(TicketSelectActivity.this,"En fazla 9 adet bilet alabilirsiniz!!",Toast.LENGTH_LONG).show();

                } else {

                    ticketClass3Piece = ticketClass3Piece + 1.0;
                    totalTicketPiece = ticketClass1Piece + ticketClass2Piece + ticketClass3Piece + ticketClass4Piece;
                    Integer pieceInt = ticketClass3Piece.intValue();
                    binding.ticketSelect3NumberTextview.setText(String.valueOf(pieceInt));

                    Double class1Price = ticketClass1Piece * class1TicketPrice;
                    Double class2Price = ticketClass2Piece * class2TicketPrice;
                    Double class3Price = ticketClass3Piece * class3TicketPrice;
                    Double class4Price = ticketClass4Piece * class4TicketPrice;

                    totalTicketPrice = class1Price + class2Price + class3Price + class4Price;

                    binding.ticketPriceTextView.setText(String.valueOf(totalTicketPrice) + " TL");

                }


            }
        });

        binding.ticketSelect3NegativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (totalTicketPiece == 0) {



                } else {

                    ticketClass3Piece = ticketClass3Piece - 1.0;
                    totalTicketPiece = ticketClass1Piece + ticketClass2Piece + ticketClass3Piece + ticketClass4Piece;
                    Integer pieceInt = ticketClass3Piece.intValue();
                    binding.ticketSelect3NumberTextview.setText(String.valueOf(pieceInt));

                    Double class1Price = ticketClass1Piece * class1TicketPrice;
                    Double class2Price = ticketClass2Piece * class2TicketPrice;
                    Double class3Price = ticketClass3Piece * class3TicketPrice;
                    Double class4Price = ticketClass4Piece * class4TicketPrice;

                    totalTicketPrice = class1Price + class2Price + class3Price + class4Price;

                    binding.ticketPriceTextView.setText(String.valueOf(totalTicketPrice) + " TL");

                }





            }
        });

        binding.ticketselect4AddPositiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (totalTicketPiece >= 9) {


                    Toast.makeText(TicketSelectActivity.this,"En fazla 9 adet bilet alabilirsiniz!!",Toast.LENGTH_LONG).show();

                } else {

                    ticketClass4Piece = ticketClass4Piece + 1.0;
                    totalTicketPiece = ticketClass1Piece + ticketClass2Piece + ticketClass3Piece + ticketClass4Piece;
                    Integer pieceInt = ticketClass4Piece.intValue();
                    binding.ticketSelect4NumberTextview.setText(String.valueOf(pieceInt));

                    Double class1Price = ticketClass1Piece * class1TicketPrice;
                    Double class2Price = ticketClass2Piece * class2TicketPrice;
                    Double class3Price = ticketClass3Piece * class3TicketPrice;
                    Double class4Price = ticketClass4Piece * class4TicketPrice;

                    totalTicketPrice = class1Price + class2Price + class3Price + class4Price;

                    binding.ticketPriceTextView.setText(String.valueOf(totalTicketPrice) + " TL");

                }


            }
        });

        binding.ticketSelect4NegativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (totalTicketPiece == 0) {



                } else {

                    ticketClass4Piece = ticketClass4Piece - 1.0;
                    totalTicketPiece = ticketClass1Piece + ticketClass2Piece + ticketClass3Piece + ticketClass4Piece;
                    Integer pieceInt = ticketClass4Piece.intValue();
                    binding.ticketSelect4NumberTextview.setText(String.valueOf(pieceInt));

                    Double class1Price = ticketClass1Piece * class1TicketPrice;
                    Double class2Price = ticketClass2Piece * class2TicketPrice;
                    Double class3Price = ticketClass3Piece * class3TicketPrice;
                    Double class4Price = ticketClass4Piece * class4TicketPrice;

                    totalTicketPrice = class1Price + class2Price + class3Price + class4Price;

                    binding.ticketPriceTextView.setText(String.valueOf(totalTicketPrice) + " TL");

                }





            }
        });



        binding.selectAndContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (totalTicketPiece == 0) {


                    Toast.makeText(TicketSelectActivity.this,"Lütfen bilet adedi seçiniz.", Toast.LENGTH_SHORT).show();

                } else {

                    HashMap<String, Object> ticketInfo = new HashMap<>();
                    ticketInfo.put("totalTicketPiece", totalTicketPiece);
                    ticketInfo.put("totalTicketPrice", totalTicketPrice);

                    firebaseFirestore.collection("Users").document(firebaseAuth.getCurrentUser().getEmail()).collection("Tickets").document(ticketSerialnumber).update(ticketInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {


                                Intent ticketToSeatIntent = new Intent(TicketSelectActivity.this, SeatSelectActivity.class);
                                ticketToSeatIntent.putExtra("TicketPiece", totalTicketPiece);
                                ticketToSeatIntent.putExtra("TicketTotalPrice", totalTicketPrice);
                                ticketToSeatIntent.putExtra("ActivityDocumentId", selectActivityDocumentID);
                                ticketToSeatIntent.putExtra("selectActivityName", selectActivityName);
                                ticketToSeatIntent.putExtra("selectActivityImageURL", selectActivityImgUrl);
                                ticketToSeatIntent.putExtra("selectActivityTimeString", selectActivitTimeString);
                                ticketToSeatIntent.putExtra("ticketSerialnumber", ticketSerialnumber);


                                startActivity(ticketToSeatIntent);


                            } else {


                                Toast.makeText(TicketSelectActivity.this, "İnternet bağlantınızda bir problem olabilir! Lütfen daha sonra tekrar deneyin.", Toast.LENGTH_LONG).show();


                            }


                        }
                    });


                }







            }
        });






    }



    @Override
    public void onBackPressed(){

        AlertDialog.Builder backAlert = new AlertDialog.Builder(TicketSelectActivity.this);
        backAlert.setTitle("Uyarı");
        backAlert.setMessage("Bilet Alma işleminiz iptel edilecek");
        backAlert.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                firebaseFirestore.collection("Users").document(firebaseAuth.getCurrentUser().getEmail()).collection("Tickets").document(ticketSerialnumber).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {


                            Intent backIntent = new Intent(TicketSelectActivity.this, MainActivity.class);
                            startActivity(backIntent);


                        }


                    }
                });


                    onBackPressed();

            }
        });

        backAlert.setNegativeButton("Vazgeç",null);

        backAlert.show();



    }





}