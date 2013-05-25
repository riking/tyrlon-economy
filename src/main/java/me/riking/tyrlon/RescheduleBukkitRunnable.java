package me.riking.tyrlon;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * This class provides an wrapper to {@link BukkitRunnable#runTask(Plugin)}
 * that discards the "already-scheduled" exceptions.
 */
public abstract class RescheduleBukkitRunnable extends BukkitRunnable {
    public Plugin p;

    public RescheduleBukkitRunnable(Plugin plugin) {
        p = plugin;
    }

    /**
     * Runs this on the next server tick if not already scheduled
     */
    public void scheduleSync() {
        try {
            this.runTask(p);
        } catch (IllegalStateException e) {
            // discard
        }
    }

    /**
     * Runs this if not already running
     */
    public void scheduleAsync() {
        try {
            this.runTaskAsynchronously(p);
        } catch (IllegalStateException e) {
            // discard
        }
    }
}
