package com.software3200.ebbkultursanatapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.software3200.ebbkultursanatapp.R;

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

                        String activitytitle = (String) documemnt.get("activityTitle");


                    }

                }

            }
        });






    }

}