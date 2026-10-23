package aethereal.event;
import aethereal.type.EventPriority;
import aethereal.Daamky;
import aethereal.util.GrimDelayHandler;
import aethereal.type.Mc;

public class GrimInputListener implements ClientListener {
    public GrimInputListener() {
        Daamky.INSTANCE.eventDispatcher().register(MovementInputEvent.class, class040Var -> {
            if (Mc.INSTANCE.isWorldLoaded()) {
                GrimDelayHandler.input(class040Var);
            }
        }, EventPriority.HIGHEST);
    }
}
