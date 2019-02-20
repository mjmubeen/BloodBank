package com.example.mubeen.bloodbank;

public class bank
{
    private String name, mobile, location;

    public bank()
    {

    }

    public bank(String name, String mobile, String location) {
        this.name = name;
        this.mobile = mobile;
        this.location = location;
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
}
