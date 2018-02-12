package com.loca.addressbook.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Registry {

    private List<Contact> localContactList = new ArrayList<>();


    public void addContact(String firstName, String lastName, String email) {

        localContactList.add(new LocalContact(firstName, lastName, email, UUID.randomUUID()));
    }

    public List<Contact> getContacts() {
        return this.localContactList;
    }

    public boolean deleteContact(String uuid)  {

        int i = 0;
        for (Contact contact : localContactList) {
            if (contact.getId().equals(uuid)) {
                localContactList.remove(i);
                return true;
            }
            i++;
        }
       return false;
    }

    public List<Contact> search(String search) {
        List<Contact> tempRegistry = new ArrayList<>();
        for (Contact contact : localContactList) {
            if (contact.getFirstName().toLowerCase().startsWith(search.toLowerCase()) ||
                    contact.getLastName().toLowerCase().startsWith(search.toLowerCase())) {
                tempRegistry.add(contact);
            }
        }
        return tempRegistry;

    }

    //TODO change name to reflect what it do
    public void load(List<Contact> localRegistry) {
        this.localContactList = localRegistry;
    }
}
