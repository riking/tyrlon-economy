package me.riking.tyrlon.pruning;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import me.riking.tyrlon.Tyrlon;

import org.bukkit.Server;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Task to prune old players from the account data.
 * <p>
 * <b>Do NOT call this from the main thread!</b> It will cause a deadlock,
 * with the Futures never being executed by the Scheduler.
 */
public class PrunePlayersTask extends BukkitRunnable {
    Tyrlon plugin;

    public PrunePlayersTask(Tyrlon plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        Set<String> initPlayerSet = plugin.getAccountStorage().playerAccounts.keySet();
        HashSet<String> resultSet = new HashSet<String>();
        int batchSize = Math.min(plugin.pruneBatchSize, initPlayerSet.size());

        long cutoff = System.currentTimeMillis() - plugin.pruneMaxAge;
        Server server = plugin.getServer();

        // Create all of the OldNameFinders
        ArrayList<OldNameFinder> finders = new ArrayList<OldNameFinder>();

        HashSet<String> currentSet = new HashSet<String>(batchSize);
        int i = 0;
        for (String s : initPlayerSet) {
            i++;
            currentSet.add(s);
            if (i % batchSize == 0) {
                finders.add(new OldNameFinder(currentSet, cutoff, server));
                currentSet = new HashSet<String>(batchSize);;
            }
        }
        initPlayerSet = null;

        // Submit all the OldNameFinders to Bukkit
        ArrayList<Future<Set<String>>> futures = new ArrayList<Future<Set<String>>>();
        for (OldNameFinder task : finders) {
            futures.add(server.getScheduler().callSyncMethod(plugin, task));
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                // discard
            }
        }

        // Get and coallate the results
        for (Future<Set<String>> future : futures) {
            try {
                resultSet.addAll(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                plugin.getLogger().severe("Old player pruning task failed!");
            }
        }

        // Pass the results to the database handler
        plugin.getCurrentDatabase().pruneTheseOldAccounts(resultSet);
    }
}
