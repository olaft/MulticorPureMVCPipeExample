package com.torressoft.example;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        CoreFacade facade1 = CoreFacade.getInstance("Core1");
        facade1.registerMediator(new CoreJunctionMediator());
        CoreFacade module1 = CoreFacade.getInstance("Module1");
        module1.registerMediator(new ModuleJuntionMediator());
        CoreFacade module2 = CoreFacade.getInstance("Module2");
        module2.registerMediator(new ModuleJuntionMediator());

        facade1.sendNotification(CoreFacade.CONNECT_MODULE_TO_CORE, module1);
        facade1.sendNotification(CoreFacade.CONNECT_MODULE_TO_CORE, module2);

        facade1.sendNotification(CoreJunctionMediator.SEND_MESSAGE, "Hello World!");
    }
}
