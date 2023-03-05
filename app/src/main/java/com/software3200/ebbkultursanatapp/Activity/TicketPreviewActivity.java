package com.software3200.ebbkultursanatapp.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.software3200.ebbkultursanatapp.R;
import com.software3200.ebbkultursanatapp.databinding.ActivityTicketPreviewBinding;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import io.grpc.Context;

public class TicketPreviewActivity extends AppCompatActivity {

    ActivityTicketPreviewBinding binding;

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    String ticketSerialnumber;
    String activityDocumentId;

    ArrayList<String> userSeatArrayList;
    String qrCodeText;
    Bitmap qrCodeBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_preview);

        binding = ActivityTicketPreviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        Intent getSeatSelectIntent = getIntent();
        ticketSerialnumber =getSeatSelectIntent.getStringExtra("ticketSerialnumber");
        activityDocumentId = getSeatSelectIntent.getStringExtra("ActivityDocumentId");

        userSeatArrayList = new ArrayList<>();

        getTicketPrewiew();
        getuserSeat();

        binding.ticketCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UUID ticketUUID = UUID.randomUUID();

                qrCodeText = "EBB-"  + ticketSerialnumber + "-" + activityDocumentId + ticketUUID;
                MultiFormatWriter writer = new MultiFormatWriter();

                try {

                    BitMatrix matrix = writer.encode(qrCodeText, BarcodeFormat.QR_CODE,400,400);
                    BarcodeEncoder encoder = new BarcodeEncoder();
                    qrCodeBitmap = encoder.createBitmap(matrix);
                    qrCodeBitmap = URI.create(encoder.createBitmap(matrix));
                    Uri imageData = Uri.EMPTY;



                    String qrStorageName = "TicketsQrImage/" + qrCodeText + ".jpg";


                    storageReference.child(qrStorageName).putFile(imageData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            StorageReference newReference = firebaseStorage.getReference(qrStorageName);
                            newReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    String qrDownloadUrl = uri.toString();

                                    HashMap<String, Object> ticketcreateUpdate = new HashMap<>();
                                    ticketcreateUpdate.put("ticketQrCode",qrCodeText);
                                    ticketcreateUpdate.put("ticketQrImage",qrDownloadUrl);
                                    ticketcreateUpdate.put("ticketSeatName",userSeatArrayList.toString());
                                    ticketcreateUpdate.put("ticketSuccess",true);

                                    firebaseFirestore.collection("Users").document(firebaseAuth.getCurrentUser().getEmail()).collection("Tickets").document(ticketSerialnumber).update(ticketcreateUpdate).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {

                                            Intent successIntent = new Intent(TicketPreviewActivity.this, MainActivity.class);
                                            startActivity(successIntent);



                                        }
                                    });

                                }
                            });


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {



                        }
                    });




                } catch (WriterException e) {

                    e.printStackTrace();

                }








            }
        });


    }

    public void getTicketPrewiew () {

        firebaseFirestore.collection("Users").document(firebaseAuth.getCurrentUser().getEmail()).collection("Tickets").document(ticketSerialnumber).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {

                        String activityName = (String) document.get("activityName");
                        String dateString = (String) document.get("activityTime");
                        String priceString = (String) document.get("ticketPriceString");
                        String nameSurname = (String) document.get("nameSurname");
                        String ticketImgUrl = (String) document.get("ticketImgUrl");

                        Picasso.get().load(ticketImgUrl).into(binding.activityImageView);
                        binding.activityTitleTextview.setText(activityName);
                        binding.activityDateTextView.setText(dateString);
                        binding.userNameSurnameTextview.setText(nameSurname);
                        binding.priceTextview.setText(priceString);

                    }


                }



            }
        });


    }


    public void getuserSeat () {


        firebaseFirestore.collection("Events").document(activityDocumentId).collection("Saloon").whereEqualTo("reservationUser",firebaseAuth.getCurrentUser().getEmail()).whereEqualTo("seatStatus",2).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {

                    Toast.makeText(TicketPreviewActivity.this, "İnternet bağlanınızda bir problem var! Lütfen daha sonra tekrar deneyiniz.", Toast.LENGTH_SHORT).show();

                }

                if (value != null) {

                    userSeatArrayList.clear();

                    for (DocumentSnapshot snapshot : value.getDocuments() ) {


                        Map<String, Object> document = snapshot.getData();

                        String seatName = (String) document.get("seatName");

                        userSeatArrayList.add(seatName);



                    }

                    binding.selectSeatTextView.setText(userSeatArrayList.toString());


                }




            }
        });



    }



}