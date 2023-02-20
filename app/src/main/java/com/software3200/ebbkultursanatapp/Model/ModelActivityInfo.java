package com.software3200.ebbkultursanatapp.Model;

import com.google.firebase.Timestamp;

import java.sql.Time;
import java.util.Date;

public class ModelActivityInfo {

    public String activityName;
    public String activityCategory;
    public String activityLocation;
    public Timestamp activityDate;
    public Timestamp activityBeginDate;
    public Timestamp activityEndDate;
    public String activityDescription;

    public String activityImgUrl1;
    public String activityImgUrl2;

    public String activityLocationAdressDetail;
    public Double activityLocationLatitude;
    public Double activityLocationLongitude;

    public String activityOrganization;
    public String activityTelephoneNumberTitle;

    public Boolean activityTicketInfo;
    public String activityTicketClass1Name;
    public Double activityTicketClass1Price;
    public String activityTicketClass2name;
    public Double activityTicketClass2Price;
    public String activityTicketClass3Name;
    public Double activityTicketClass3Price;
    public String activityTicketClass4Name;
    public Double activityTicketClass4Price;

    public String activityAddUser;

    public ModelActivityInfo(String activityName, String activityCategory, String activityLocation, Timestamp activityDate, Timestamp activityBeginDate, Timestamp activityEndDate, String activityDescription, String activityImgUrl1, String activityImgUrl2, String activityLocationAdressDetail, Double activityLocationLatitude, Double activityLocationLongitude, String activityOrganization, String activityTelephoneNumberTitle, Boolean activityTicketInfo, String activityTicketClass1Name, Double activityTicketClass1Price, String activityTicketClass2name, Double activityTicketClass2Price, String activityTicketClass3Name, Double activityTicketClass3Price, String activityTicketClass4Name, Double activityTicketClass4Price, String activityAddUser) {
        this.activityName = activityName;
        this.activityCategory = activityCategory;
        this.activityLocation = activityLocation;
        this.activityDate = activityDate;
        this.activityBeginDate = activityBeginDate;
        this.activityEndDate = activityEndDate;
        this.activityDescription = activityDescription;
        this.activityImgUrl1 = activityImgUrl1;
        this.activityImgUrl2 = activityImgUrl2;
        this.activityLocationAdressDetail = activityLocationAdressDetail;
        this.activityLocationLatitude = activityLocationLatitude;
        this.activityLocationLongitude = activityLocationLongitude;
        this.activityOrganization = activityOrganization;
        this.activityTelephoneNumberTitle = activityTelephoneNumberTitle;
        this.activityTicketInfo = activityTicketInfo;
        this.activityTicketClass1Name = activityTicketClass1Name;
        this.activityTicketClass1Price = activityTicketClass1Price;
        this.activityTicketClass2name = activityTicketClass2name;
        this.activityTicketClass2Price = activityTicketClass2Price;
        this.activityTicketClass3Name = activityTicketClass3Name;
        this.activityTicketClass3Price = activityTicketClass3Price;
        this.activityTicketClass4Name = activityTicketClass4Name;
        this.activityTicketClass4Price = activityTicketClass4Price;
        this.activityAddUser = activityAddUser;
    }
}
