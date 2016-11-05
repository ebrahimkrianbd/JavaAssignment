/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package address.book;

import java.util.ArrayList;

/**
 *
 * @author rianix
 */
class ContactInfo implements Comparable{
    private String name;
    private String nickname;
    private String mobileNumber;
    private String faxNumber;
    private String phoneNumber;
    private String otherNumber;
    private String email;
    private String website;
    private String country;
    private String city;
    private String address;
    private String birthDate;
    private String notes;
    private String profilePicture;
    private ArrayList<String> group;

    public ContactInfo() {
    }
    

    public ContactInfo(String name, String nickname, String mobileNumber,
            String faxNumber, String phoneNumber, String otherNumber, String email,
            String website, String country, String city, String address,
            String birthDate, String notes, String profilePicture, ArrayList<String> group) {
        
        this.name = name;
        this.nickname = nickname;
        this.mobileNumber = mobileNumber;
        this.faxNumber = faxNumber;
        this.phoneNumber = phoneNumber;
        this.otherNumber = otherNumber;
        this.email = email;
        this.website = website;
        this.country = country;
        this.city = city;
        this.address = address;
        this.birthDate = birthDate;
        this.notes = notes;
        this.profilePicture = profilePicture;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getOtherNumber() {
        return otherNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public ArrayList<String> getGroup() {
        return group;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setOtherNumber(String otherNumber) {
        this.otherNumber = otherNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void setGroup(ArrayList<String> group) {
        this.group = group;
    }

    public ContactInfo(String name, String nickname, String mobileNumber, String faxNumber, String phoneNumber, String otherNumber, String email, String website, String country, String city, String address, String birthDate, String notes) {
        this.name = name;
        this.nickname = nickname;
        this.mobileNumber = mobileNumber;
        this.faxNumber = faxNumber;
        this.phoneNumber = phoneNumber;
        this.otherNumber = otherNumber;
        this.email = email;
        this.website = website;
        this.country = country;
        this.city = city;
        this.address = address;
        this.birthDate = birthDate;
        this.notes = notes;
    }

    @Override
    public int compareTo(Object o) {
        ContactInfo a = this;
        ContactInfo b = (ContactInfo) o;
        
        long result1 = a.name.compareTo(b.name);
        long result2 = b.name.compareTo(a.name);
        
        if(result1 < result2)    
            return -1;
        else if(result1 > result2)
            return 1;
        else return 0;
    }
    
    
    
    
    
    
}
