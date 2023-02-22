package com.software3200.ebbkultursanatapp.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.software3200.ebbkultursanatapp.Adapter.AdapterHomeBanner;
import com.software3200.ebbkultursanatapp.Adapter.AdapterSeatSelect;
import com.software3200.ebbkultursanatapp.JavaModel.SeatDocumentIdModel;
import com.software3200.ebbkultursanatapp.Model.ModelHomeBanner;
import com.software3200.ebbkultursanatapp.Model.ModelSeatSelect;
import com.software3200.ebbkultursanatapp.R;
import com.software3200.ebbkultursanatapp.databinding.ActivitySeatSelectBinding;

import java.util.ArrayList;
import java.util.Map;

public class SeatSelectActivity extends AppCompatActivity {

    ActivitySeatSelectBinding binding;

    ArrayList<ModelSeatSelect> modelSeatSelectArrayList;
    AdapterSeatSelect adapterSeatSelect;

    FirebaseFirestore firebaseFirestore;

    String activityDocumentID;


    private ScaleGestureDetector mScaleGestureDetector;
    private float mscaleFactor = 1.0f;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_select);

        binding = ActivitySeatSelectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseFirestore = FirebaseFirestore.getInstance();

        Intent activityTicketSelectToSeatSelectIntent = getIntent();
        activityDocumentID = activityTicketSelectToSeatSelectIntent.getStringExtra("ActivityDocumentId");

        modelSeatSelectArrayList = new ArrayList<>();
        getSeatInfo();

        RecyclerView.LayoutManager linearLayoutHomeBanner = new GridLayoutManager(this, 29);
        binding.seatSelectRecyclerview.setLayoutManager(linearLayoutHomeBanner);
        adapterSeatSelect = new AdapterSeatSelect(modelSeatSelectArrayList);
        binding.seatSelectRecyclerview.setAdapter(adapterSeatSelect);


        mScaleGestureDetector = new ScaleGestureDetector(this,new ScaleListener());




      /* binding.seatSelectRecyclerview.setOnTouchListener(new View.OnTouchListener() {

           float x,y;
           float dx, dy;
            @Override
            public boolean onTouch(View view, MotionEvent event) {


                if (event.getAction() == MotionEvent.ACTION_DOWN) {


                    x = event.getX();
                    y = event.getY();

                }

                if(event.getAction() == MotionEvent.ACTION_MOVE) {

                    dx = event.getX() - x;
                    dy = event.getY() - y;

                    binding.seatSelectRecyclerview.setX(binding.seatSelectRecyclerview.getX() + dx);
                    binding.seatSelectRecyclerview.setY(binding.seatSelectRecyclerview.getY() + dy);

                    x = event.getX();
                    y = event.getY();

                }


                mScaleGestureDetector.onTouchEvent(event);

                return false;
            }
        });



       */


    }



    float x,y;
    float dx, dy;


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {


            x = event.getX();
            y = event.getY();

        }

        if(event.getAction() == MotionEvent.ACTION_MOVE) {

            dx = event.getX() - x;
            dy = event.getY() - y;

            binding.seatSelectRecyclerview.setX(binding.seatSelectRecyclerview.getX() + dx);
            binding.seatSelectRecyclerview.setY(binding.seatSelectRecyclerview.getY() + dy);

            x = event.getX();
            y = event.getY();

        }

        mScaleGestureDetector.onTouchEvent(event);



        return super.onTouchEvent(event);
    }

    class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {


            mscaleFactor *= scaleGestureDetector.getScaleFactor();
            mscaleFactor = Math.max(1.0f, Math.min(mscaleFactor,10.f));
            binding.seatSelectRecyclerview.setScaleX(mscaleFactor);
            binding.seatSelectRecyclerview.setScaleY(mscaleFactor);


            return true;

        }

    }




    public void getSeatInfo() {

        SeatDocumentIdModel seatDocumentIdModel = new SeatDocumentIdModel(activityDocumentID);

        firebaseFirestore.collection("Events").document(activityDocumentID).collection("Saloon").orderBy("seatBox", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {


                    Toast.makeText(SeatSelectActivity.this,"İnternet bağlantısında bir problem var. Lütfen bağlantınız kontrol edin.",Toast.LENGTH_LONG).show();

                }


                if (value != null) {

                    modelSeatSelectArrayList.clear();

                    for (DocumentSnapshot snapshot : value.getDocuments()) {

                        System.out.println(snapshot.getData());

                        Map<String, Object> data = snapshot.getData();

                         Long seatBoxLong = (Long) data.get("seatBox");
                         String seatName = (String) data.get("seatName");
                         Long seatStatusLong = (Long) data.get("seatStatus");
                         String userName = (String) data.get("userName");
                         String userEmail = (String) data.get("userEmail");
                         String userTcNo = (String) data.get("userTcNo");
                         String reservationUser = (String) data.get("reservationUser");
                         Timestamp reservationTimeStamp = (Timestamp) data.get("reservationTimeStamp");
                         String documentId = (String) snapshot.getId();

                         Integer seatBox = seatBoxLong.intValue();
                         Integer seatStatus = seatStatusLong.intValue();



                        ModelSeatSelect modelSeatSelect = new ModelSeatSelect(seatBox,seatName,seatStatus,userName,userEmail,userTcNo,reservationUser,reservationTimeStamp,documentId);
                        modelSeatSelectArrayList.add(modelSeatSelect);

                    }

                    adapterSeatSelect.notifyDataSetChanged();

                }




            }
        });




    }



}