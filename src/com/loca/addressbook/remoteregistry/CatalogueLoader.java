package com.loca.addressbook.remoteregistry;

public class CatalogueLoader implements Runnable{
    private int port = 61616;
    private RemoteCatalogueFactory catalogueFactory;
    private RemoteRegistry remoteRegistry;
    private String hostName;

    public CatalogueLoader(RemoteRegistry remoteRegistry, String hostName) {
        this.remoteRegistry = remoteRegistry;
        this.hostName = hostName;
        catalogueFactory = new RemoteCatalogueFactory(port);
    }

    @Override
    public void run() {
        RemoteCatalogueProxy remoteCatalogueProxy = catalogueFactory.create(hostName);

        for(String contact : remoteCatalogueProxy.getContacts()) {
           // System.out.println(contact.length());
            if (!contact.equals("EOF")){
                try {

                    String[] splittedList = contact.split(" ");
                    //  if (   (splittedList.length()) == 4   ){

                    remoteRegistry.add(splittedList[0], splittedList[1], splittedList[2], splittedList[3]);
                }
                catch (Exception e){
                    System.out.println("string in addressbook: "+ contact+" contains not expected format");
                }
        }}

    }
}
