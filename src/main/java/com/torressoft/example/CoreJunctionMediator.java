package com.torressoft.example;

import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.utilities.pipes.interfaces.IPipeAware;
import org.puremvc.java.multicore.utilities.pipes.interfaces.IPipeMessage;
import org.puremvc.java.multicore.utilities.pipes.messages.Message;
import org.puremvc.java.multicore.utilities.pipes.plumbing.*;

public class CoreJunctionMediator extends JunctionMediator {

    public static final String NAME = "CoreJunctionMediator";
    public static final String SEND_MESSAGE = "SendMessage";
        public CoreJunctionMediator() {
            super(NAME, new Junction());

        }

    @Override
    public void onRegister() {
        getJunction().registerPipe(CoreFacade.OUTPUT_TEESPLIT, Junction.OUTPUT, new TeeSplit());
        getJunction().registerPipe(CoreFacade.INPUT_TEEMERGE, Junction.INPUT, new TeeMerge());
        getJunction().addPipeListener(CoreFacade.INPUT_TEEMERGE , this, this::handlePipeMessage);
    }

    @Override
        public String[] listNotificationInterests() {
            return new String[]{
                    CoreFacade.CONNECT_MODULE_TO_CORE
                    , SEND_MESSAGE
            };
        }

        @Override
        public void handleNotification(INotification notification) {

            switch (notification.getName()) {
                case CoreFacade.CONNECT_MODULE_TO_CORE:
                    Object module = notification.getBody();
                    if (!(module instanceof IPipeAware)) {
                        return;
                    }
                    IPipeAware pipeAware = (IPipeAware) module;

                    Pipe inputPipe= new Pipe();
                    pipeAware.acceptInputPipe(CoreFacade.MODULE_INPUT_PIPE, inputPipe);
                    TeeSplit inputTeeSplit = (TeeSplit) getJunction().retrievePipe(CoreFacade.OUTPUT_TEESPLIT);
                    inputTeeSplit.connect(inputPipe);


                    Pipe outputPipe = new Pipe();
                    pipeAware.acceptOutputPipe(CoreFacade.MODULE_OUTPUT_PIPE, outputPipe);
                    TeeMerge outputTeeMerge = (TeeMerge) getJunction().retrievePipe(CoreFacade.INPUT_TEEMERGE);
                    outputTeeMerge.connectInput(outputPipe);


                    break;
                case SEND_MESSAGE:
                    Junction junction = this.getJunction();
                    junction.sendMessage(CoreFacade.OUTPUT_TEESPLIT, new Message(Message.NORMAL, null, "Hello World!", Message.PRIORITY_MED));
                    break;
            }
        }

        @Override
        public void handlePipeMessage(IPipeMessage message) {
            super.handlePipeMessage(message);
            System.out.println(getFacade().toString() + message.getBody());
        }
}
