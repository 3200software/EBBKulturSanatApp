package com.software3200.ebbkultursanatapp.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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

import java.io.ByteArrayOutputStream;
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
    ArrayList<String> userSeatDocumentIdArrayList;
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
        userSeatDocumentIdArrayList = new ArrayList<>();




        getTicketPrewiew();
        getuserSeat();

        binding.ticketCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UUID ticketUUID = UUID.randomUUID();

                qrCodeText =  ticketSerialnumber + "-" + activityDocumentId + ticketUUID;
                MultiFormatWriter writer = new MultiFormatWriter();

                try {

                    BitMatrix matrix = writer.encode(qrCodeText, BarcodeFormat.QR_CODE,400,400);
                    BarcodeEncoder encoder = new BarcodeEncoder();
                    qrCodeBitmap = encoder.createBitmap(matrix);
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    qrCodeBitmap.compress(Bitmap.CompressFormat.JPEG,100,bytes);

                    String uriPath = MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(),qrCodeBitmap,"val",null);
                    Uri qrUri = Uri.parse(uriPath);

                    String qrStorageName = "TicketsQrImage/" + qrCodeText + ".jpg";


                    storageReference.child(qrStorageName).putFile(qrUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
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

                                    firebaseFirestore.collection("Tickets").document(ticketSerialnumber).update(ticketcreateUpdate).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {


                                            for (String documentIds : userSeatDocumentIdArrayList) {

                                                HashMap<String, Object> seatStatusUpdate = new HashMap<>();
                                                seatStatusUpdate.put("seatStatus",1);

                                                firebaseFirestore.collection("Events").document(activityDocumentId).collection("Saloon").document(documentIds).update(seatStatusUpdate).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {


                                                        if (task.isSuccessful()) {



                                                        } else {


                                                            AlertDialog.Builder seatAlert = new AlertDialog.Builder(TicketPreviewActivity.this);
                                                            seatAlert.setTitle("Uyarı");
                                                            seatAlert.setTitle("Bilet oluşturuken bir sorun oluştu. Lütfen tekrar deneyin");
                                                            seatAlert.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {

                                                                    HashMap<String, Object> ticketcreateUpdate = new HashMap<>();
                                                                    ticketcreateUpdate.put("ticketQrCode","");
                                                                    ticketcreateUpdate.put("ticketQrImage","");
                                                                    ticketcreateUpdate.put("ticketSeatName","");
                                                                    ticketcreateUpdate.put("ticketSuccess",false);

                                                                    firebaseFirestore.collection("Tickets").document(ticketSerialnumber).update(ticketcreateUpdate).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                        @Override
                                                                        public void onSuccess(Void unused) {

                                                                            onBackPressed();

                                                                        }
                                                                    });



                                                                }
                                                            });

                                                            seatAlert.show();


                                                        }


                                                    }
                                                });


                                            }



                                            Intent successIntent = new Intent(TicketPreviewActivity.this, TicketActivity.class);
                                            successIntent.putExtra("ticketSerialnumber",ticketSerialnumber);
                                            successIntent.putExtra("ticketGoToPage","TicketPreviewPage");
                                            startActivity(successIntent);



                                        }


                                });



                                }





                            }).addOnFailureListener(new OnFailureListener() {

                                @Override

                                public void onFailure(@NonNull Exception e) {



                                }



                            });

                        };



                    });

                } catch (WriterException e) {

                    e.printStackTrace();

                }

            }
        });


    }

    public void getTicketPrewiew () {

        firebaseFirestore.collection("Tickets").document(ticketSerialnumber).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
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

                        String documentId = snapshot.getId();

                        userSeatArrayList.add(seatName);

                        userSeatDocumentIdArrayList.add(documentId);


                    }

                    binding.selectSeatTextView.setText(userSeatArrayList.toString());


                }




            }
        });


        /*

        for (String item: userSeatArrayList) {

            firebaseFirestore.collection("Events").document(activityDocumentId).collection("Saloon").whereEqualTo("seatName",item).addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {


                    if (error != null) {

                        Toast.makeText(TicketPreviewActivity.this, "İnternet bağlanınızda bir problem var! Lütfen daha sonra tekrar deneyiniz.", Toast.LENGTH_SHORT).show();

                    }

                    if (value != null) {


                        for (DocumentSnapshot snapshot : value.getDocuments() ) {


                            Map<String, Object> document = snapshot.getData();

                            String documentId = snapshot.getId();



                        }


                    }

                }


            });

        }

        */

    }




}