package com.loca.addressbook.userinterface;

import com.loca.addressbook.registry.Contact;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactListSorter {

    public static List<Contact> sort (List<Contact> contacts) {
        List<Contact> sortedContactList = new ArrayList<>(contacts);
        Comparator<Contact> comparator = Comparator.comparing(contact -> contact.getFirstName().toLowerCase());
        sortedContactList.sort(comparator);
        return sortedContactList;
    }
}