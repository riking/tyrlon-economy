package me.riking.tyrlon.db.yaml;

import java.io.IOException;

import me.riking.tyrlon.RescheduleBukkitRunnable;

public class YamlSaveRunnable extends RescheduleBukkitRunnable {
    YamlDatabase db;

    public YamlSaveRunnable(YamlDatabase database) {
        super(database.plugin);
        db = database;
    }

    @Override
    public void run() {
        try {
            db.banks.save(db.bankFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            db.players.save(db.playerFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
