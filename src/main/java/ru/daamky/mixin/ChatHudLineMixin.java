package ru.daamky.mixin;

import aethereal.accessor.ChatLineIdAccessor;
import net.minecraft.client.gui.hud.ChatHudLine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin({ChatHudLine.class})
public abstract class ChatHudLineMixin implements ChatLineIdAccessor {

    @Unique
    private String daamky_ru$id = null;

    @Override
    @Unique
    public void daamky_ru$setId(String str) {
        this.daamky_ru$id = str;
    }

    @Override
    @Unique
    public String daamky_ru$getId() {
        return this.daamky_ru$id;
    }
}
