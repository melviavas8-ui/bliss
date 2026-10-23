package ru.daamky.mixin;

import aethereal.net.PinnableServer;
import net.minecraft.client.network.ServerInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin({ServerInfo.class})
public class ServerInfoMixin implements PinnableServer {

    @Unique
    private boolean daamky$pinned;

    @Override
    public boolean daamky$isPinned() {
        return this.daamky$pinned;
    }

    @Override
    public void daamky$setPinned(boolean z) {
        this.daamky$pinned = z;
    }
}
