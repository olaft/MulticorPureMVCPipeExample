package com.torressoft.example;

import org.puremvc.java.multicore.utilities.pipes.plumbing.JunctionMediator;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        CoreFacade facade1 = CoreFacade.getInstance("Core1");
        facade1.registerMediator(new CoreJunctionMediator());
        CoreFacade facade2 = CoreFacade.getInstance("Core2");
        facade2.registerMediator(new ModuleJuntionMediator());
        CoreFacade facade3 = CoreFacade.getInstance("Core3");
        facade3.registerMediator(new ModuleJuntionMediator());

        facade1.sendNotification(CoreFacade.CONNECT_INPUT_PIPE, facade2);
        facade1.sendNotification(CoreFacade.CONNECT_INPUT_PIPE, facade3);

        facade1.sendNotification(CoreJunctionMediator.SEND_MESSAGE, "Hello World!");
    }
}
