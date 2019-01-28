package com.example.mubeen.bloodbank;

public class UserInfo {
    String id, name, mobile, location, blood;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public UserInfo() {
    }

    public UserInfo (String id, String name, String mobile, String location, String blood) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.location = location;
        this.blood = blood;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }
}
