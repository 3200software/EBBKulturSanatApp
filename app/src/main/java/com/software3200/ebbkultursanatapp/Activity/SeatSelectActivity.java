package com.software3200.ebbkultursanatapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.software3200.ebbkultursanatapp.R;
import com.software3200.ebbkultursanatapp.databinding.ActivitySeatSelectBinding;

public class SeatSelectActivity extends AppCompatActivity {

    ActivitySeatSelectBinding binding;

    private ScaleGestureDetector mScaleGestureDetector;
    private float mscaleFactor = 1.0f;

    float xDown = 150 , yDown = 150;



    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

       @Override

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {


           mscaleFactor *= scaleGestureDetector.getScaleFactor();
           mscaleFactor = Math.max(1.0f, Math.min(mscaleFactor,4.0f));
           binding.seatLayout.setScaleX(mscaleFactor);
           binding.seatLayout.setScaleY(mscaleFactor);

           return true;

       }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_select);

        binding = ActivitySeatSelectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        mScaleGestureDetector = new ScaleGestureDetector(this,new ScaleListener());




        binding.seatLayout.setOnTouchListener(new View.OnTouchListener() {
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

                        binding.seatLayout.setX(binding.seatLayout.getX() + distanceX);
                        binding.seatLayout.setY(binding.seatLayout.getY() + distanceY);

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

}