package com.software3200.ebbkultursanatapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.software3200.ebbkultursanatapp.Activity.AuthActivity;
import com.software3200.ebbkultursanatapp.Activity.MainActivity;
import com.software3200.ebbkultursanatapp.Activity.UserTicketsActivity;
import com.software3200.ebbkultursanatapp.databinding.FragmentAccountBinding;


public class AccountFragment extends Fragment {

    private FragmentAccountBinding binding;

    FirebaseAuth firebaseAuth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {

            Intent authControlIntent = new Intent(requireActivity(), AuthActivity.class);
            startActivity(authControlIntent);

        }

        binding.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                firebaseAuth.signOut();
                Intent signOutIntent = new Intent(requireActivity(), MainActivity.class);
                startActivity(signOutIntent);



            }
        });

        binding.ticketsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signOutIntent = new Intent(requireActivity(), UserTicketsActivity.class);
                startActivity(signOutIntent);

            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}