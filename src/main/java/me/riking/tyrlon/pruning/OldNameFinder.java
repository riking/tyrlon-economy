package me.riking.tyrlon.pruning;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Callable;

import org.bukkit.OfflinePlayer;
import org.bukkit.Server;

public class OldNameFinder implements Callable<Set<String>> {
    Set<String> parameter;
    Server server;
    final long cutoff;

    public OldNameFinder(Set<String> toCheck, long cutoffTimeMillis, Server server) {
        parameter = toCheck;
        this.server = server;
        cutoff = cutoffTimeMillis;
    }

    @Override
    public Set<String> call() throws Exception {
        HashSet<String> result = new HashSet<String>(parameter);
        Iterator<String> iter = result.iterator();
        while (iter.hasNext()) {
            OfflinePlayer player = server.getOfflinePlayer(iter.next());

            if (player.isOnline() || player.getLastPlayed() >= cutoff) {
                iter.remove();
            }
        }
        return result;
    }
}
