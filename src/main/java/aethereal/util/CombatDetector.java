package aethereal.util;
import aethereal.event.AttackEntityEvent;
import aethereal.event.ClientListener;
import aethereal.type.CombatPauseManager;
import aethereal.Daamky;

public class CombatDetector implements ClientListener {
    public CombatDetector() {
        Daamky.INSTANCE.eventDispatcher().register(AttackEntityEvent.class, class144Var -> {
            CombatPauseManager.INSTANCE.inCombatForAtLeast(40);
        });
    }
}
