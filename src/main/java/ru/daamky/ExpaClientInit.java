package ru.daamky;

import aethereal.DaamkyBootstrap;
import net.fabricmc.api.ClientModInitializer;

public class ExpaClientInit implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        DaamkyBootstrap.init();
    }
}
