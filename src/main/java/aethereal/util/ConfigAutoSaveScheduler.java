package aethereal.util;
import aethereal.net.CloudConfigService;
import aethereal.Daamky;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public final class ConfigAutoSaveScheduler {
    public static final long autoSaveDelayMs = 1200;
    public static final Object lock = new Object();
    public static ScheduledFuture<?> pendingSave;

    public ConfigAutoSaveScheduler() {
    }

    public static void scheduleAutoSave() {
        CloudConfigService class357VarCloudConfigService = Daamky.INSTANCE.cloudConfigService();
        class357VarCloudConfigService.activeConfig().ifPresent(class304Var -> {
            if (Daamky.INSTANCE.configManager().menuStateConfig().isAutoSaveDisabled(class304Var.id())) {
                return;
            }
            synchronized (lock) {
                if (pendingSave != null && !pendingSave.isDone()) {
                    pendingSave.cancel(false);
                }
                pendingSave = Daamky.INSTANCE.executor().schedule(() -> {
                    class357VarCloudConfigService.saveConfig(class304Var.id(), Daamky.INSTANCE.moduleRepository(), Daamky.INSTANCE.widgetStack()).thenRun(() -> {
                        Daamky.LOGGER.info("Config auto-saved: {}", class304Var.id());
                    }).exceptionally(th -> {
                        Daamky.LOGGER.error("Failed to auto-save config", th);
                        return null;
                    });
                }, autoSaveDelayMs, TimeUnit.MILLISECONDS);
            }
        });
    }
}
