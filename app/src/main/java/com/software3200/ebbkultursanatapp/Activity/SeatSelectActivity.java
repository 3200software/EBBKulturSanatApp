package com.software3200.ebbkultursanatapp.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.software3200.ebbkultursanatapp.Adapter.AdapterHomeBanner;
import com.software3200.ebbkultursanatapp.Adapter.AdapterSeatSelect;
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


    private ScaleGestureDetector mScaleGestureDetector;
    private float mscaleFactor = 1.0f;

    float xDown = 150 , yDown = 150;



    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

       @Override

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {


           mscaleFactor *= scaleGestureDetector.getScaleFactor();
           mscaleFactor = Math.max(1.0f, Math.min(mscaleFactor,4.0f));


           return true;

       }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_select);

        binding = ActivitySeatSelectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseFirestore = FirebaseFirestore.getInstance();

        modelSeatSelectArrayList = new ArrayList<>();
        getSeatInfo();

        RecyclerView.LayoutManager linearLayoutHomeBanner = new GridLayoutManager(this, 24);
        binding.seatSelectRecyclerview.setLayoutManager(linearLayoutHomeBanner);
        adapterSeatSelect = new AdapterSeatSelect(modelSeatSelectArrayList);
        binding.seatSelectRecyclerview.setAdapter(adapterSeatSelect);


        mScaleGestureDetector = new ScaleGestureDetector(this,new ScaleListener());




        binding.seatSelectRecyclerview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {



                switch (motionEvent.getActionMasked()) {

                    case MotionEvent.ACTION_DOWN:

                        xDown = motionEvent.getX();
                        yDown = motionEvent.getY();

                        break;


                    case MotionEvent.ACTION_MOVE:

                        float movedX, movedY;

                        movedX = motionEvent.getX();
                        movedY = motionEvent.getY();

                        float distanceX = movedX - xDown;
                        float distanceY = movedY - yDown;

                        binding.seatSelectRecyclerview.setX(binding.seatSelectRecyclerview.getX() + distanceX);
                        binding.seatSelectRecyclerview.setY(binding.seatSelectRecyclerview.getY() + distanceY);

                        xDown = movedX;
                        yDown = movedY;


                        break;



                }

                return true;
            }
        });


    }

    public boolean onTouchEvent(MotionEvent motionEvent) {

        mScaleGestureDetector.onTouchEvent(motionEvent);




        return true;

    }


    public void getSeatInfo() {


        firebaseFirestore.collection("Events").document("AMrY2Kus3303A2hs8C6p").collection("Saloon").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {


                    Toast.makeText(SeatSelectActivity.this,"??nternet ba??lant??s??nda bir problem var. L??tfen ba??lant??n??z kontrol edin.",Toast.LENGTH_LONG).show();

                }


                if (value != null) {


                    for (DocumentSnapshot snapshot : value.getDocuments()) {

                        System.out.println(snapshot.getData());

                        Map<String, Object> data = snapshot.getData();

                        String seatName = (String) data.get("seatName");
                        String seatStatus = (String) data.get("seatStatus");
                        String seatOwnerTcNumber = (String) data.get("seatOwnerTcNumber");



                        ModelSeatSelect modelSeatSelect = new ModelSeatSelect(seatName,seatStatus,seatOwnerTcNumber);
                        modelSeatSelectArrayList.add(modelSeatSelect);

                    }

                    adapterSeatSelect.notifyDataSetChanged();

                }




            }
        });




    }



}