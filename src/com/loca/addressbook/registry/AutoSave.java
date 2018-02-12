package com.loca.addressbook.registry;

public class AutoSave implements Runnable {

    private RegistryPersister registryPersister;

    public AutoSave(RegistryPersister registryPersister) {
        this.registryPersister = registryPersister;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("AutoSave");
        while (true) {
            try {
                Thread.sleep(5000);
                registryPersister.save();
            } catch (InterruptedException e) {
                //TODO print to a better place
                System.err.println("Autosave interrupted");
            }
        }
    }
}
