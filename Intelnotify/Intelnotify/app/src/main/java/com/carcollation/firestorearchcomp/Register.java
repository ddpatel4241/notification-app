package com.carcollation.firestorearchcomp;


public class Register {

    // variables for storing our data.
    private String name, emailid, password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Register() {
        // empty constructor
        // required for Firebase.
    }

    // Constructor for all variables.
    public Register(String name, String emailid, String password) {
        this.name = name;
        this.emailid = emailid;
        this.password = password;
    }


}
