package aethereal.event;
import aethereal.type.EventPriority;
import aethereal.Daamky;
import aethereal.type.Mc;
import aethereal.util.StaffDetector;

public class PlayerListListener implements ClientListener {
    public PlayerListListener() {
        Daamky.INSTANCE.eventDispatcher().register(TabListEntryEvent.class, class038Var -> {
            if (Mc.INSTANCE.isWorldLoaded()) {
                StaffDetector.onPlayerListEvent(class038Var);
            }
        }, EventPriority.LOW);
    }
}
