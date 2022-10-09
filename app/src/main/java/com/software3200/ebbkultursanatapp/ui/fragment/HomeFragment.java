package com.software3200.ebbkultursanatapp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.software3200.ebbkultursanatapp.Adapter.AdapterHomeBanner;
import com.software3200.ebbkultursanatapp.Model.ModelHomeBanner;
import com.software3200.ebbkultursanatapp.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.Map;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    FirebaseFirestore firebaseFirestore;

    ArrayList<ModelHomeBanner> modelHomeBannerArrayList;
    AdapterHomeBanner adapterHomeBanner;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        firebaseFirestore = FirebaseFirestore.getInstance();

        modelHomeBannerArrayList = new ArrayList<>();
        getHomeBanner();
        LinearLayoutManager linearLayoutHomeBanner = new LinearLayoutManager(requireActivity());
        binding.homeActivityRecyclerView.setLayoutManager(linearLayoutHomeBanner);
        adapterHomeBanner = new AdapterHomeBanner(modelHomeBannerArrayList);
        binding.homeActivityRecyclerView.setAdapter(adapterHomeBanner);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void getHomeBanner() {


        firebaseFirestore.collection("HomeBanner").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                    if (error != null) {

                        Toast.makeText(requireActivity(),"İnternet bağlantısında bir problem var. Lütfen bağlantınız kontrol edin.",Toast.LENGTH_LONG).show();

                    }


                for (DocumentSnapshot snapshot : value.getDocuments()) {

                    Map<String, Object> data = snapshot.getData();

                    String activityImgUrl = (String) data.get("activityImgUrl");
                    String activityCategoryName = (String) data.get("activityCategoryName");
                    String activityDocumentId = (String) data.get("activityDocumentId");



                    ModelHomeBanner modelHomeBanner = new ModelHomeBanner(activityImgUrl,activityCategoryName,activityDocumentId);
                    modelHomeBannerArrayList.add(modelHomeBanner);

                }

                adapterHomeBanner.notifyDataSetChanged();
            }
        });




    }

}