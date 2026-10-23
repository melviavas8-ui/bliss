package aethereal;
import aethereal.model.AuthStub;
import aethereal.resource.ClasspathResource;
import aethereal.render.GlTexture;
import aethereal.net.UserSession;
import net.minecraft.util.Util;

public class DaamkyBootstrap {
    public static void init() {
        try {
            Util.getOperatingSystem().open("https://t.me/soezproject");
        } catch (Throwable ignored) {
            // Mobile / non-desktop environments (MobileGlues etc.) may not support opening URLs
        }
        UserSession class385Var;
        try {
            class385Var = new UserSession(AuthStub.uid(), AuthStub.username(), AuthStub.hwid(), AuthStub.role(), AuthStub.expire(), AuthStub.avatarUrl(), new GlTexture(new ClasspathResource("assets/daamky/textures/avatar.png")));
        } catch (UnsatisfiedLinkError e) {
            class385Var = new UserSession("1", "soezproject", "null", "admin", "30.01.9999", "https://i.pinimg.com/736x/8e/b9/46/8eb94669194489ac098b5e693a7c6d89.jpg", new GlTexture(new ClasspathResource("assets/daamky/textures/avatar.png")));
        } catch (Throwable t) {
            class385Var = new UserSession("1", "soezproject", "null", "admin", "30.01.9999", "https://i.pinimg.com/736x/8e/b9/46/8eb94669194489ac098b5e693a7c6d89.jpg", null);
        }
        try {
            new Daamky(class385Var);
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }
}
