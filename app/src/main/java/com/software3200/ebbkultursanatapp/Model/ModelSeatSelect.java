package com.software3200.ebbkultursanatapp.Model;

import com.google.firebase.Timestamp;

public class ModelSeatSelect {

    public Integer seatBox;
    public String seatName;
    public Integer seatStatus;
    public String userName;
    public String userEmail;
    public String userTcNo;
    public String reservationUser;
    public Timestamp reservationTimeStamp;
    public String documentId;

    public String parrentDocumentId;



    public ModelSeatSelect(Integer seatBox, String seatName, Integer seatStatus, String userName, String userEmail, String userTcNo, String reservationUser, Timestamp reservationTimeStamp, String documentId, String parrentDocumentId) {
        this.seatBox = seatBox;
        this.seatName = seatName;
        this.seatStatus = seatStatus;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userTcNo = userTcNo;
        this.reservationUser = reservationUser;
        this.reservationTimeStamp = reservationTimeStamp;
        this.documentId = documentId;
        this.parrentDocumentId = parrentDocumentId;

    }
}
