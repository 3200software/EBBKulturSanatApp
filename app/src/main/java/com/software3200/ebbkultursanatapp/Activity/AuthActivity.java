package com.software3200.ebbkultursanatapp.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.software3200.ebbkultursanatapp.R;
import com.software3200.ebbkultursanatapp.databinding.ActivityAuthBinding;

public class AuthActivity extends AppCompatActivity {

    ActivityAuthBinding binding;

    FirebaseAuth firebaseAuth;

    FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        binding.authPasswordAgainEditText.setVisibility(View.INVISIBLE);
        binding.alertTextview.setVisibility(View.GONE);

        binding.authSaveTextviewLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String controlText = binding.authTitleTextView.getText().toString();

                if (controlText.equals("Giriş Yap")) {

                    binding.authSaveTextviewLink.setText("<< Giriş Yap");
                    binding.authTitleTextView.setText("Kayıt Ol");
                    binding.authPasswordAgainEditText.setVisibility(View.VISIBLE);
                    binding.authButton.setText("Kayıt Ol");

                } else if(controlText.equals("Kayıt Ol")) {

                    binding.authSaveTextviewLink.setText("Kayıt olmak için buraya tıklayın");
                    binding.authTitleTextView.setText("Giriş Yap");
                    binding.authPasswordAgainEditText.setVisibility(View.INVISIBLE);
                    binding.authButton.setText("Giriş Yap");


                }



            }
        });

        binding.authButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String controlText = binding.authTitleTextView.getText().toString();


                if (controlText.equals("Giriş Yap")) {

                    String email = binding.authEmailEditText.getText().toString();
                    String password = binding.authPasswordEditText.getText().toString();

                    firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            Intent authIntent = new Intent(AuthActivity.this, MainActivity.class);
                            startActivity(authIntent);

                        }
                    });



                } else if (controlText.equals("Kayıt Ol")) {

                    String email = binding.authEmailEditText.getText().toString();
                    String password = binding.authPasswordEditText.getText().toString();

                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            firebaseFirestore.collection("Users").addSnapshotListener(new EventListener<QuerySnapshot>() {
                                @Override
                                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                                    if




                                }
                            });



                            binding.authSaveTextviewLink.setText("Kayıt olmak için buraya tıklayın");
                            binding.authTitleTextView.setText("Giriş Yap");
                            binding.authPasswordAgainEditText.setVisibility(View.INVISIBLE);
                            binding.authButton.setText("Giriş Yap");




                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {



                        }
                    });



                }





            }
        });


    }
}