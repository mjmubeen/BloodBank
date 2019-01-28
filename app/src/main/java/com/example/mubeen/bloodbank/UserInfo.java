package com.example.mubeen.bloodbank;

public class UserInfo {
    private String Id, Name, Mobile, Location, Blood, Gender, Birth;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public UserInfo() {
    }

    public UserInfo (String id, String name, String mobile, String location, String blood, String gender, String birth) {
        this.Id = id;
        this.Name = name;
        this.Mobile = mobile;
        this.Location = location;
        this.Blood = blood;
        this.Gender = gender;
        this.Birth = birth;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getBlood() {
        return Blood;
    }

    public void setBlood(String blood) {
        Blood = blood;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getBirth() {
        return Birth;
    }

    public void setBirth(String birth) {
        Birth = birth;
    }
}
