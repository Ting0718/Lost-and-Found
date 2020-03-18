package com.example.zot_and_found.Models;

import org.parceler.Parcel;

@Parcel
public class Replier {
    private String userEmail;
    private String reply;

    public Replier() {

    }

    public Replier(String userEmail,String reply) {
        this.userEmail= userEmail;
        this.reply = reply;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getReply() {
        return reply;
    }
}
