package com.loca.addressbook;

import java.util.ArrayList;
import java.util.List;

import com.loca.addressbook.registry.AutoSave;
import com.loca.addressbook.registry.Registry;
import com.loca.addressbook.registry.RegistryPersister;
import com.loca.addressbook.remoteregistry.CatalogueLoader;
import com.loca.addressbook.remoteregistry.RemoteRegistry;
import com.loca.addressbook.userinterface.CommandLineInterface;
import com.loca.addressbook.userinterface.Console;

public class Application {


    //TODO Main should be outside of application
    //TODO WHY STATIC?
    //private String hostName1 = "172.20.200.157";
    private String hostName1 = "localhost";
    private String hostName2 = "172.20.200.173";
    private String hostName3 = "172.20.201.62";
    private Console console = new Console();
    private Registry registry = new Registry();
    private RegistryPersister registryPersister = new RegistryPersister(registry);
    private RemoteRegistry remoteRegistry = new RemoteRegistry();

    public void start() {
        initiateLocalContacts();
        initiateServerContacts();
        startAutoSaveDaemon();
        initiateCommandLineInterface();
    }

    private void initiateLocalContacts() {
        registryPersister.load();

    }

    private void initiateServerContacts() {
        List<String> hostNames = makeHostNames();
        for (String hostName : hostNames) {
            Runnable runnable = new CatalogueLoader(remoteRegistry, hostName);
            Thread catalogueLoader = new Thread(runnable);
            catalogueLoader.setDaemon(true);
            catalogueLoader.start();
        }
    }

    private List<String> makeHostNames() {
        List<String> hostNames = new ArrayList<>();
        hostNames.add(hostName1);
        hostNames.add(hostName2);
        hostNames.add(hostName3);
        return hostNames;
    }

    private void startAutoSaveDaemon() {
        Runnable runnable = new AutoSave(registryPersister);
        Thread autoSave = new Thread(runnable);
        autoSave.setDaemon(true);
        autoSave.start();
    }

    private void initiateCommandLineInterface() {
        CommandLineInterface commandLineInterface = new CommandLineInterface(registry, remoteRegistry, console, this);
        commandLineInterface.start();
    }

    public void quit() {
        registryPersister.save();
        console.close();
    }

}
