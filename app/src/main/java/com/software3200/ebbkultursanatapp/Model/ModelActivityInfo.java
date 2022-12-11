package com.software3200.ebbkultursanatapp.Model;

import java.util.Date;

public class ModelActivityInfo {

    public String activityImgUrl;
    public String activityTitle;
    public String activityDetail;
    public String activityDetailTitle;
    public String activityCategory;
    public String activityOrganization;
    public Date activityDate;
    public Date activityBeginDate;
    public Date activityEndDate;
    public Boolean activityTicketFreeInfo;
    public Double activityTicketStudentPrice;
    public Double activityTicketAdultPrice;
    public Double activityTicketClass1Price;
    public String activityTicketClass1Name;
    public Double activityTicketClass2Price;
    public String activityTicketClass2Name;
    public Double activityTicketClass3Price;
    public String activityTicketClass3Name;
    public Double activityTicketClass4Price;
    public String activityTicketClass4Name;
    public String activityLocation;
    public String activityLocationAdress;
    public Double activityLocationLatitude;
    public Double activityLocationLongitude;
    public String activityTelephoneNumber;

    public ModelActivityInfo(String activityImgUrl, String activityTitle, String activityDetail, String activityDetailTitle, String activityCategory, String activityOrganization, Date activityDate, Date activityBeginDate, Date activityEndDate, Boolean activityTicketFreeInfo, Double activityTicketStudentPrice, Double activityTicketAdultPrice, Double activityTicketClass1Price, String activityTicketClass1Name, Double activityTicketClass2Price, String activityTicketClass2Name, Double activityTicketClass3Price, String activityTicketClass3Name, Double activityTicketClass4Price, String activityTicketClass4Name, String activityLocation, String activityLocationAdress, Double activityLocationLatitude, Double activityLocationLongitude, String activityTelephoneNumber) {
        this.activityImgUrl = activityImgUrl;
        this.activityTitle = activityTitle;
        this.activityDetail = activityDetail;
        this.activityDetailTitle = activityDetailTitle;
        this.activityCategory = activityCategory;
        this.activityOrganization = activityOrganization;
        this.activityDate = activityDate;
        this.activityBeginDate = activityBeginDate;
        this.activityEndDate = activityEndDate;
        this.activityTicketFreeInfo = activityTicketFreeInfo;
        this.activityTicketStudentPrice = activityTicketStudentPrice;
        this.activityTicketAdultPrice = activityTicketAdultPrice;
        this.activityTicketClass1Price = activityTicketClass1Price;
        this.activityTicketClass1Name = activityTicketClass1Name;
        this.activityTicketClass2Price = activityTicketClass2Price;
        this.activityTicketClass2Name = activityTicketClass2Name;
        this.activityTicketClass3Price = activityTicketClass3Price;
        this.activityTicketClass3Name = activityTicketClass3Name;
        this.activityTicketClass4Price = activityTicketClass4Price;
        this.activityTicketClass4Name = activityTicketClass4Name;
        this.activityLocation = activityLocation;
        this.activityLocationAdress = activityLocationAdress;
        this.activityLocationLatitude = activityLocationLatitude;
        this.activityLocationLongitude = activityLocationLongitude;
        this.activityTelephoneNumber = activityTelephoneNumber;
    }
}
