package ru.daamky.mixin;

import aethereal.util.StencilBufferUtil;
import aethereal.util.PinnedServersController;
import aethereal.model.PinnedServerEntry;
import aethereal.Daamky;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerServerListWidget;
import net.minecraft.client.network.ServerInfo;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({MultiplayerServerListWidget.ServerEntry.class})
public class MultiplayerServerListWidget$ServerEntryMixin {

    @Shadow
    @Final
    private ServerInfo field_19120;

    @Shadow
    @Final
    private MinecraftClient field_19119;

    @Inject(method = {"render"}, at = {@At("TAIL")})
    private void daamky$renderPinned(DrawContext drawContext, int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z, float f, CallbackInfo callbackInfo) {
        PinnedServersController class028VarPinnedServersController = Daamky.INSTANCE.pinnedServersController();
        if (class028VarPinnedServersController == null) {
            return;
        }
        String str = this.field_19120.address;
        if (class028VarPinnedServersController.isPinned(str)) {
            int iDaamky$getAccentColor = daamky$getAccentColor(class028VarPinnedServersController, str);
            if (iDaamky$getAccentColor == 0) {
                iDaamky$getAccentColor = 52945;
            }
            int iDaamky$withAlpha = daamky$withAlpha(iDaamky$getAccentColor, z ? 53 : 34);
            int iDaamky$withAlpha2 = daamky$withAlpha(iDaamky$getAccentColor, 204);
            drawContext.fill(i3, i2, i3 + i4, i2 + i5, iDaamky$withAlpha);
            drawContext.fill(i3, i2, i3 + 3, i2 + i5, iDaamky$withAlpha2);
        }
    }

    @Unique
    private static int daamky$getAccentColor(PinnedServersController class028Var, String str) {
        String strDaamky$normalize = daamky$normalize(str);
        for (PinnedServerEntry class029Var : class028Var.favorites()) {
            if (daamky$normalize(class029Var.address()).equals(strDaamky$normalize)) {
                return class029Var.accentColor();
            }
        }
        return 0;
    }

    @Unique
    private static String daamky$normalize(String str) {
        if (str == null) {
            return "";
        }
        String strTrim = str.trim();
        return !strTrim.contains(":") ? strTrim + ":25565" : strTrim;
    }

    @Unique
    private static int daamky$withAlpha(int i, int i2) {
        return ((i2 & StencilBufferUtil.STENCIL_MASK) << 24) | (i & 16777215);
    }
}
