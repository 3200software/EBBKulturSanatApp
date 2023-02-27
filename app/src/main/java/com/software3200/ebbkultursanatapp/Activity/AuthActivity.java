package com.software3200.ebbkultursanatapp.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.software3200.ebbkultursanatapp.R;
import com.software3200.ebbkultursanatapp.databinding.ActivityAuthBinding;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AuthActivity extends AppCompatActivity {

    ActivityAuthBinding binding;

    FirebaseAuth firebaseAuth;

    FirebaseFirestore firebaseFirestore;

    ArrayList<String> usersArrayList;

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

        usersArrayList = new ArrayList<>();

        binding.authSaveTextviewLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String controlText = binding.authTitleTextView.getText().toString();

                if (controlText.equals("Giriş Yap")) {

                    binding.authSaveTextviewLink.setText("<< Giriş Yap");
                    binding.authTitleTextView.setText("Kayıt Ol");
                    binding.authPasswordAgainEditText.setVisibility(View.VISIBLE);
                    binding.authButton.setText("Kayıt Ol");

                } else if (controlText.equals("Kayıt Ol")) {

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
                    System.out.println("lknsalidjasd");


                    if (email.equals("") || password.equals("")) {


                        if (email.equals("") && password.equals("")) {

                            AlertDialog.Builder alert = new AlertDialog.Builder(AuthActivity.this);
                            alert.setTitle("Uyarı");
                            alert.setMessage("Lütfen email ve şifre giriniz.");
                            alert.show();

                        } else if (email.equals("")) {


                            AlertDialog.Builder alert1 = new AlertDialog.Builder(AuthActivity.this);
                            alert1.setTitle("Uyarı");
                            alert1.setMessage("Lütfen email giriniz.");
                            alert1.show();
                        } else if (password.equals("")) {

                            AlertDialog.Builder alert1 = new AlertDialog.Builder(AuthActivity.this);
                            alert1.setTitle("Uyarı");
                            alert1.setMessage("Lütfen şifre giriniz.");
                            alert1.show();


                        }


                    } else {

                        String  userStatus;

                        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(AuthActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                System.out.println(password + email);
                                if (task.isSuccessful()) {

                                    firebaseFirestore.collection("Users").document(firebaseAuth.getCurrentUser().getEmail()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                                            if (task.isSuccessful()) {

                                                DocumentSnapshot document = task.getResult();

                                                if (document.exists()) {

                                                   String userStatus = (String) document.get("userStatus");

                                                   if (userStatus.equals("1")){

                                                       onBackPressed();

                                                   } else {


                                                       firebaseAuth.signOut();
                                                       Intent authIntent = new Intent(AuthActivity.this, MainActivity.class);
                                                       startActivity(authIntent);


                                                   }

                                                } else {



                                                }


                                            } else {



                                            }



                                        }
                                    });




                                } else {

                                    Toast.makeText(AuthActivity.this, "İnternet bağlanızda bir problem var! Lütfen daha sonra tekrar deneyiniz.", Toast.LENGTH_LONG).show();

                                }


                            }
                        });

                    }


                } else if (controlText.equals("Kayıt Ol")) {


                    String email = binding.authEmailEditText.getText().toString();
                    String password = binding.authPasswordEditText.getText().toString();
                    String passwordAgain = binding.authPasswordAgainEditText.getText().toString();


                    if (email.equals("") || password.equals("") || passwordAgain.equals("")) {


                        if (email.equals("") && password.equals("")) {

                            AlertDialog.Builder alert = new AlertDialog.Builder(AuthActivity.this);
                            alert.setTitle("Uyarı");
                            alert.setMessage("Lütfen email ve şifre giriniz.");
                            alert.show();

                        } else if (email.equals("")) {


                            AlertDialog.Builder alert1 = new AlertDialog.Builder(AuthActivity.this);
                            alert1.setTitle("Uyarı");
                            alert1.setMessage("Lütfen email giriniz.");
                            alert1.show();

                        } else if (password.equals("") || passwordAgain.equals("")) {

                            AlertDialog.Builder alert1 = new AlertDialog.Builder(AuthActivity.this);
                            alert1.setTitle("Uyarı");
                            alert1.setMessage("Lütfen şifre giriniz.");
                            alert1.show();


                        }


                    } else if (password.equals(passwordAgain)) {


                        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                            @Override

                            public void onComplete(@NonNull Task<AuthResult> task) {


                                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(AuthActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        System.out.println(password + email);

                                        if (task.isSuccessful()) {

                                            HashMap<String, Object> userCreate = new HashMap<>();
                                            userCreate.put("userEmail",firebaseAuth.getCurrentUser().getEmail());
                                            userCreate.put("userName", "");
                                            userCreate.put("name", "");
                                            userCreate.put("surname", "");
                                            userCreate.put("birthday", FieldValue.serverTimestamp());
                                            userCreate.put("location", "");
                                            userCreate.put("phoneNumber", "");
                                            userCreate.put("signInDate",FieldValue.serverTimestamp());
                                            userCreate.put("userStatus","1");

                                            firebaseFirestore.collection("Users").document(firebaseAuth.getCurrentUser().getEmail()).set(userCreate).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                    if (task.isSuccessful()) {

                                                        binding.authSaveTextviewLink.setText("Kayıt olmak için buraya tıklayın");
                                                        binding.authTitleTextView.setText("Giriş Yap");
                                                        binding.authPasswordAgainEditText.setVisibility(View.INVISIBLE);
                                                        binding.authButton.setText("Giriş Yap");
                                                        firebaseAuth.signOut();

                                                    } else {


                                                        Toast.makeText(AuthActivity.this, "İnternet bağlanızda bir problem var! Lütfen daha sonra tekrar deneyiniz.", Toast.LENGTH_LONG).show();

                                                    }


                                                }
                                            });


                                        }

                                    }

                                });

                            }

                        });





                    } else {

                        AlertDialog.Builder alert1 = new AlertDialog.Builder(AuthActivity.this);
                        alert1.setTitle("Uyarı");
                        alert1.setMessage("Şifreler aynı değil!");
                        alert1.show();


                    }


                }


            }

        });


    }


}











