package com.loca.addressbook.registry;

import java.io.Serializable;

public class ContactDetails implements Serializable {

    private String firstName;
    private String lastName;
    private String email;

    public ContactDetails(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
