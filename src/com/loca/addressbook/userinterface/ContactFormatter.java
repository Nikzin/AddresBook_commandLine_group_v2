package com.loca.addressbook.userinterface;

import com.loca.addressbook.registry.Contact;

public class ContactFormatter {

    public static String format(Contact contact) {

        return String.format("ID: %s%nFirstname: %s%nLastname: %s%nE-mail: %s%n%n",
                contact.getId(), contact.getFirstName(), contact.getLastName(), contact.getEmailAddress());
    }
}
