package com.software3200.ebbkultursanatapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.software3200.ebbkultursanatapp.R;
import com.squareup.picasso.Picasso;

import java.util.Date;

public class ActivityDetailActivity extends AppCompatActivity {


    FirebaseFirestore firebaseFirestore;

    String activityDetailDocumntID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);







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




                    }

                }

            }
        });






    }

}