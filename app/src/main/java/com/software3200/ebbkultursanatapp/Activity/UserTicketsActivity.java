package com.software3200.ebbkultursanatapp.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.software3200.ebbkultursanatapp.Adapter.AdapterSeatSelect;
import com.software3200.ebbkultursanatapp.Adapter.AdapterTicketList;
import com.software3200.ebbkultursanatapp.Adapter.ModelTicketList;
import com.software3200.ebbkultursanatapp.Model.ModelHomeBanner;
import com.software3200.ebbkultursanatapp.R;
import com.software3200.ebbkultursanatapp.databinding.ActivityUserTicketsBinding;

import java.util.ArrayList;
import java.util.Map;

public class UserTicketsActivity extends AppCompatActivity {

    ActivityUserTicketsBinding binding;

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;

    ArrayList<ModelTicketList> modelTicketListArrayList;
    AdapterTicketList adapterTicketList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_tickets);

        binding = ActivityUserTicketsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        modelTicketListArrayList = new ArrayList<>();
        gettickets();

        RecyclerView.LayoutManager linearLayoutHomeBanner = new GridLayoutManager(this, 2);
        binding.ticketlistRecylerview.setLayoutManager(linearLayoutHomeBanner);
        adapterTicketList = new AdapterTicketList(modelTicketListArrayList);
        binding.ticketlistRecylerview.setAdapter(adapterTicketList);



    }

    public void gettickets () {

        firebaseFirestore.collection("Tickets").whereEqualTo("ticketUserEmail",firebaseAuth.getCurrentUser().getEmail()).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {


                if (error != null) {

                    Toast.makeText(UserTicketsActivity.this, "İnternet bağlantınızda bir problem var! Lütfen daha sonra tekrar deneyiniz.", Toast.LENGTH_SHORT).show();

                }

                if (value != null) {

                    modelTicketListArrayList.clear();

                    for (DocumentSnapshot snapshot : value.getDocuments()) {



                        Map<String, Object> data = snapshot.getData();

                        String ticketImgUrl = (String) data.get("ticketImgUrl");
                        String ticketName = (String) data.get("activityName");
                        String ticketDateStr= (String) data.get("activityTime");
                        String ticketDocumentId = snapshot.getId();

                        ModelTicketList modelHomeBanner = new ModelTicketList(ticketImgUrl,ticketName,ticketDocumentId,ticketDateStr);
                        modelTicketListArrayList.add(modelHomeBanner);

                    }

                }

                adapterTicketList.notifyDataSetChanged();

            }
        });

    }


}