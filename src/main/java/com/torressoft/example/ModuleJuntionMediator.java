package com.torressoft.example;

import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.utilities.pipes.interfaces.IPipeFitting;
import org.puremvc.java.multicore.utilities.pipes.interfaces.IPipeMessage;
import org.puremvc.java.multicore.utilities.pipes.plumbing.Junction;
import org.puremvc.java.multicore.utilities.pipes.plumbing.JunctionMediator;

public class ModuleJuntionMediator extends JunctionMediator {

    public ModuleJuntionMediator() {
        super(NAME, new Junction());
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }

    @Override
    public void handleNotification(INotification note) {
        super.handleNotification(note);
    }

    @Override
    public void handlePipeMessage(IPipeMessage message) {
        super.handlePipeMessage(message);
        System.out.println(getFacade().toString() + message);
    }
}
