package com.loca.addressbook.remoteregistry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AtomicRemoteCatalogueProxy implements RemoteCatalogueProxy {

    private CatalogueClient catalogueClient;
    private String host;
    private List<String> remoteContactList = new ArrayList<>();

    public AtomicRemoteCatalogueProxy(String host, int port) {
        catalogueClient = new CatalogueClient(host, port);
        this.host = host;
    }

    @Override
    public List<String> getContacts() {
        try {
            catalogueClient.connect();
            catalogueClient.sendRequest("getall");
            catalogueClient.sendRequest("exit");

            String result = catalogueClient.waitForResponse();

            String[] contactArray = result.split("\n");

            Collections.addAll(remoteContactList, contactArray);

         //   catalogueClient.sendRequest("exit");

        } catch (IOException e) {
            System.err.println("Connection error (" + this.host + ")");
        } finally {
            try {
                catalogueClient.disconnect();
            } catch (IOException e) {
                System.err.println("Disconnection error (" + this.host + ")");
            } catch (NullPointerException e) {
                System.err.println("Connection never opened (" + this.host + ")");
            }
        }
        return remoteContactList;
    }
}
