package com.software3200.ebbkultursanatapp.Model;

import java.util.Date;

public class ModelActivityInfo {

    public String activityImgUrl;
    public String activityTitle;
    public String activityDetail;
    public String activityCategory;
    public String activityOrganization;
    public Date activityDate;
    public Date activityBeginDate;
    public Date activityEndDate;
    public Boolean activityPriceInfo;
    public String activityLocation;
    public String activityLocationAdress;
    public Double activityLocationLatitude;
    public Double activityLocationLongitude;
    public String activityTelephoneNumber;

    public ModelActivityInfo(String activityImgUrl, String activityTitle, String activityDetail, String activityCategory, String activityOrganization, Date activityDate, Date activityBeginDate, Date activityEndDate, Boolean activityPriceInfo, String activityLocation, String activityLocationAdress, Double activityLocationLatitude, Double activityLocationLongitude, String activityTelephoneNumber) {
        this.activityImgUrl = activityImgUrl;
        this.activityTitle = activityTitle;
        this.activityDetail = activityDetail;
        this.activityCategory = activityCategory;
        this.activityOrganization = activityOrganization;
        this.activityDate = activityDate;
        this.activityBeginDate = activityBeginDate;
        this.activityEndDate = activityEndDate;
        this.activityPriceInfo = activityPriceInfo;
        this.activityLocation = activityLocation;
        this.activityLocationAdress = activityLocationAdress;
        this.activityLocationLatitude = activityLocationLatitude;
        this.activityLocationLongitude = activityLocationLongitude;
        this.activityTelephoneNumber = activityTelephoneNumber;
    }
}
