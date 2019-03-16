package client.command.commands.gm1;

import client.MapleCharacter;
import client.command.Command;
import client.MapleClient;
import java.util.ArrayList;
import java.util.Arrays;
import server.MapleItemInformationProvider;
import server.life.MapleMonsterInformationProvider;
import server.life.MonsterDropEntry;
import tools.Pair;
import java.util.Iterator;
import java.util.List;
import org.javatuples.Quartet;

public class WhatDropsFromCommand extends Command {

    {
        setName("whatdropsfrom");
        setDescription("See what items drop from a certain monster and the chance of drop.");

        setParameters(new ArrayList<>(
                Arrays.asList(
                        Quartet.with("monster", true, (List<String>) new ArrayList<String>(), "")
                )
        ));
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.dropMessage(5, "Please do @whatdropsfrom <monster name>");
            return;
        }

        String monsterName = player.getLastCommandMessage();
        String output = "";
        int limit = 3;
        Iterator<Pair<Integer, String>> listIterator = MapleMonsterInformationProvider.getMobsIDsFromName(monsterName).iterator();

        for (int i = 0; i < limit; i++) {
            if (listIterator.hasNext()) {
                Pair<Integer, String> data = listIterator.next();
                int mobId = data.getLeft();
                String mobName = data.getRight();
                output += mobName + " drops the following items:\r\n\r\n";

                for (MonsterDropEntry drop : MapleMonsterInformationProvider.getInstance().retrieveDrop(mobId)) {
                    try {
                        String name = MapleItemInformationProvider.getInstance().getName(drop.itemId);

                        if (name == null || name.equals("null") || drop.chance == 0) {
                            continue;
                        }

                        float chance = 1000000 / drop.chance / (!MapleMonsterInformationProvider.getInstance().isBoss(mobId) ? player.getDropRate() : player.getBossDropRate());
                        output += "- " + name + " (1/" + (int) chance + ")\r\n";
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        continue;
                    }
                }

                output += "\r\n";
            }
        }

        c.getAbstractPlayerInteraction().npcTalk(9010000, output);
    }
}
