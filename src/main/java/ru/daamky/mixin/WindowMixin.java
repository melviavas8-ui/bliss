package ru.daamky.mixin;

import aethereal.event.WindowResizeEvent;
import aethereal.Daamky;
import net.minecraft.client.WindowEventHandler;
import net.minecraft.client.WindowSettings;
import net.minecraft.client.util.MonitorTracker;
import net.minecraft.client.util.Window;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({Window.class})
public class WindowMixin {
    @Inject(method = {"<init>"}, at = {@At("RETURN")})
    public void init(WindowEventHandler windowEventHandler, MonitorTracker monitorTracker, WindowSettings windowSettings, @Nullable String str, String str2, CallbackInfo callbackInfo) {
        Daamky.INSTANCE.eventDispatcher().dispatch(new WindowResizeEvent(windowSettings.width, windowSettings.height));
        Daamky.INSTANCE.windowControllerAdapter().handleResize(windowSettings.width, windowSettings.height);
    }

    @Inject(method = {"onWindowSizeChanged"}, at = {@At("HEAD")})
    public void onResize(long j, int i, int i2, CallbackInfo callbackInfo) {
        Daamky.INSTANCE.windowControllerAdapter().handleResize(i, i2);
        Daamky.INSTANCE.eventDispatcher().dispatch(new WindowResizeEvent(i, i2));
    }
}
