package com.torressoft.example;

import org.puremvc.java.multicore.interfaces.IFacade;
import org.puremvc.java.multicore.patterns.facade.Facade;
import org.puremvc.java.multicore.utilities.pipes.interfaces.IPipeAware;
import org.puremvc.java.multicore.utilities.pipes.interfaces.IPipeFitting;
import org.puremvc.java.multicore.utilities.pipes.plumbing.JunctionMediator;

public class CoreFacade extends Facade implements IFacade, IPipeAware {

    public static final String CONNECT_MODULE_TO_CORE = "ConnectModuleToCore";
    public static final String OUTPUT_TEESPLIT = "OutputTeeSplit";
    public static final String INPUT_TEEMERGE = "OutputTeeMerge";
    public static final String MODULE_INPUT_PIPE = "ModuleInputPipe";
    public static final String MODULE_OUTPUT_PIPE = "ModuleOutputPipe";

    private CoreFacade(String key) {
        super(key);
    }

    public static CoreFacade getInstance(String name) {
        CoreFacade instance = (CoreFacade) instanceMap.get(name);
        if (instance == null) {
            instance = new CoreFacade(name);
        }
        return instance;
    }

    @Override
    public void acceptInputPipe(String name, IPipeFitting pipe) {
        sendNotification(JunctionMediator.ACCEPT_INPUT_PIPE, pipe, name);
    }

    @Override
    public void acceptOutputPipe(String name, IPipeFitting pipe) {
        sendNotification(JunctionMediator.ACCEPT_OUTPUT_PIPE, pipe, name);
    }
}
