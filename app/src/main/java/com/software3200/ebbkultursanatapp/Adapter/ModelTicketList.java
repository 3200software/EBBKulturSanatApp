package com.software3200.ebbkultursanatapp.Adapter;

public class ModelTicketList {

    public String ticketImgUrl;
    public String ticketActivityName;
    public String ticketDocumentId;
    public String ticketDateString;

    public ModelTicketList(String ticketImgUrl, String ticketActivityName, String ticketDocumentId, String ticketDateString) {
        this.ticketImgUrl = ticketImgUrl;
        this.ticketActivityName = ticketActivityName;
        this.ticketDocumentId = ticketDocumentId;
        this.ticketDateString = ticketDateString;
    }
}
