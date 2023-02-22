package com.software3200.ebbkultursanatapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.protobuf.StringValue;
import com.software3200.ebbkultursanatapp.R;
import com.software3200.ebbkultursanatapp.databinding.ActivityTicketSelectBinding;

public class TicketSelectActivity extends AppCompatActivity {

    ActivityTicketSelectBinding binding;

    String activityDocumentID;

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


        Intent activityDetailToTicketSelectIntent = getIntent();
        ticketInfo = activityDetailToTicketSelectIntent.getStringExtra("TicketInfo");
        activityDocumentID = activityDetailToTicketSelectIntent.getStringExtra("ActivityDocumentId");


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

                totalTicketPiece = ticketClass1Piece + ticketClass2Piece + ticketClass3Piece + ticketClass4Piece;

                if (totalTicketPiece >= 9) {


                    Toast.makeText(TicketSelectActivity.this,"En fazla 9 adet bilet alabilirsiniz!!",Toast.LENGTH_LONG).show();

                } else {

                    ticketClass1Piece = ticketClass1Piece + 1.0;
                    Integer pieceInt = ticketClass1Piece.intValue();
                    binding.ticketSelect1NumberTextview.setText(String.valueOf(pieceInt));

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

                totalTicketPiece = ticketClass1Piece + ticketClass2Piece + ticketClass3Piece + ticketClass4Piece;

                if (totalTicketPiece == 0) {



                } else {

                    ticketClass1Piece = ticketClass1Piece - 1.0;
                    Integer pieceInt = ticketClass1Piece.intValue();
                    binding.ticketSelect1NumberTextview.setText(String.valueOf(pieceInt));

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

                totalTicketPiece = ticketClass1Piece + ticketClass2Piece + ticketClass3Piece + ticketClass4Piece;

                if (totalTicketPiece >= 9) {


                    Toast.makeText(TicketSelectActivity.this,"En fazla 9 adet bilet alabilirsiniz!!",Toast.LENGTH_LONG).show();

                } else {

                    ticketClass2Piece = ticketClass2Piece + 1.0;
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

                totalTicketPiece = ticketClass1Piece + ticketClass2Piece + ticketClass3Piece + ticketClass4Piece;

                if (totalTicketPiece == 0) {



                } else {

                    ticketClass2Piece = ticketClass2Piece - 1.0;
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

                totalTicketPiece = ticketClass1Piece + ticketClass2Piece + ticketClass3Piece + ticketClass4Piece;

                if (totalTicketPiece >= 9) {


                    Toast.makeText(TicketSelectActivity.this,"En fazla 9 adet bilet alabilirsiniz!!",Toast.LENGTH_LONG).show();

                } else {

                    ticketClass3Piece = ticketClass3Piece + 1.0;
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

                totalTicketPiece = ticketClass1Piece + ticketClass2Piece + ticketClass3Piece + ticketClass4Piece;

                if (totalTicketPiece == 0) {



                } else {

                    ticketClass3Piece = ticketClass3Piece - 1.0;
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

                totalTicketPiece = ticketClass1Piece + ticketClass2Piece + ticketClass3Piece + ticketClass4Piece;

                if (totalTicketPiece >= 9) {


                    Toast.makeText(TicketSelectActivity.this,"En fazla 9 adet bilet alabilirsiniz!!",Toast.LENGTH_LONG).show();

                } else {

                    ticketClass4Piece = ticketClass4Piece + 1.0;
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

                totalTicketPiece = ticketClass1Piece + ticketClass2Piece + ticketClass3Piece + ticketClass4Piece;

                if (totalTicketPiece == 0) {



                } else {

                    ticketClass4Piece = ticketClass4Piece - 1.0;
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


                Intent ticketToSeatIntent = new Intent(TicketSelectActivity.this, SeatSelectActivity.class);
                ticketToSeatIntent.putExtra("TicketPiece",totalTicketPiece);
                ticketToSeatIntent.putExtra("TicketTotalPrice",totalTicketPrice);
                ticketToSeatIntent.putExtra("ActivityDocumentId",activityDocumentID);

                startActivity(ticketToSeatIntent);

            }
        });




    }






}