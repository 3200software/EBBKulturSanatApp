package com.software3200.ebbkultursanatapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.software3200.ebbkultursanatapp.R;
import com.software3200.ebbkultursanatapp.databinding.ActivityTicketSelectBinding;

public class TicketSelectActivity extends AppCompatActivity {

    ActivityTicketSelectBinding binding;

    String ticketInfo;
    Double adultTicketPrice;
    Double studentTicketPrice;
    String class1TicketName;
    Double class1TicketPrice;
    String class2TicketName;
    Double class2TicketPrice;
    String class3TicketName;
    Double class3TicketPrice;
    String class4TicketName;
    Double class4TicketPrice;

    Double ticketFree;
    Double ticketSingle;
    Double ticketAdult;
    Double ticketStudent;
    Double ticketClass1;
    Double ticketClass2;
    Double ticketClass3;
    Double ticketClass4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_select);

        binding = ActivityTicketSelectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Intent activityDetailToTicketSelectIntent = getIntent();
        ticketInfo = activityDetailToTicketSelectIntent.getStringExtra("TicketInfo");

        if (ticketInfo.equals("FreeTicket"))  {

            binding.ticketSelect1TitleTextView.setText("Bilet");

            binding.ticketSelect2Layout.setVisibility(View.INVISIBLE);
            binding.ticketSelect3Layout.setVisibility(View.INVISIBLE);
            binding.ticketSelect4Layout.setVisibility(View.INVISIBLE);

        } else if (ticketInfo.equals("SingleTicket")) {

            binding.ticketSelect1TitleTextView.setText("Bilet");

            binding.ticketSelect2Layout.setVisibility(View.INVISIBLE);
            binding.ticketSelect3Layout.setVisibility(View.INVISIBLE);
            binding.ticketSelect4Layout.setVisibility(View.INVISIBLE);


        } else if (ticketInfo.equals("AdultandStudentTicket")) {

            binding.ticketSelect1TitleTextView.setText("Tem");
            binding.ticketSelect2TitleTextView.setText("Öğrenci");

            binding.ticketSelect3Layout.setVisibility(View.INVISIBLE);
            binding.ticketSelect4Layout.setVisibility(View.INVISIBLE);

        } else if (ticketInfo.equals("Class1Ticket")) {

            class1TicketName = activityDetailToTicketSelectIntent.getStringExtra("TicketName1");

            binding.ticketSelect1TitleTextView.setText("Bilet");

            binding.ticketSelect2Layout.setVisibility(View.INVISIBLE);
            binding.ticketSelect3Layout.setVisibility(View.INVISIBLE);
            binding.ticketSelect4Layout.setVisibility(View.INVISIBLE);


        } else if (ticketInfo.equals("Class2Ticket")) {

            class1TicketName = activityDetailToTicketSelectIntent.getStringExtra("TicketName1");
            class2TicketName = activityDetailToTicketSelectIntent.getStringExtra("TicketName2");

            binding.ticketSelect1TitleTextView.setText(class1TicketName);
            binding.ticketSelect2TitleTextView.setText(class2TicketName);

            binding.ticketSelect3Layout.setVisibility(View.INVISIBLE);
            binding.ticketSelect4Layout.setVisibility(View.INVISIBLE);



        }  else if (ticketInfo.equals("Class3Ticket")) {

            class1TicketName = activityDetailToTicketSelectIntent.getStringExtra("TicketName1");
            class2TicketName = activityDetailToTicketSelectIntent.getStringExtra("TicketName2");
            class3TicketName = activityDetailToTicketSelectIntent.getStringExtra("TicketName3");

            binding.ticketSelect1TitleTextView.setText(class1TicketName);
            binding.ticketSelect2TitleTextView.setText(class2TicketName);
            binding.ticketSelect3TitleTextView.setText(class3TicketName);

            binding.ticketSelect4Layout.setVisibility(View.INVISIBLE);



        }   else if (ticketInfo.equals("Class4Ticket")) {

            class1TicketName = activityDetailToTicketSelectIntent.getStringExtra("TicketName1");
            class2TicketName = activityDetailToTicketSelectIntent.getStringExtra("TicketName2");
            class3TicketName = activityDetailToTicketSelectIntent.getStringExtra("TicketName3");
            class4TicketName = activityDetailToTicketSelectIntent.getStringExtra("TicketName4");

            binding.ticketSelect1TitleTextView.setText(class1TicketName);
            binding.ticketSelect2TitleTextView.setText(class2TicketName);
            binding.ticketSelect3TitleTextView.setText(class3TicketName);
            binding.ticketSelect3TitleTextView.setText(class4TicketName);


        }

        binding.selectAndContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent ticketToSeatIntent = new Intent(TicketSelectActivity.this, SeatSelectActivity.class);
                startActivity(ticketToSeatIntent);

            }
        });


        binding.ticketselect1AddPositiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ticketInfo.equals("FreeTicket"))  {




                } else if (ticketInfo.equals("SingleTicket")) {



                } else if (ticketInfo.equals("AdultandStudentTicket")) {



                } else if (ticketInfo.equals("Class1Ticket")) {



                } else if (ticketInfo.equals("Class2Ticket")) {



                } else if (ticketInfo.equals("Class3Ticket")) {



                } else if (ticketInfo.equals("Class4Ticket")) {


                }


            }
        });

    }






}