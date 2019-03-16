package client.command.commands.gm1;

import client.MapleCharacter;
import client.command.Command;
import client.MapleClient;
import server.life.MapleMonster;

public class BossHpCommand extends Command {

    {
        setName("bosshp");
        setDescription("See remaining HP of any boss monsters in the current map.");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        for (MapleMonster monster : player.getMap().getMonsters()) {
            if (monster != null && monster.isBoss() && monster.getHp() > 0) {
                long percent = monster.getHp() * 100L / monster.getMaxHp();
                String bar = "[";

                for (int i = 0; i < 100; i++) {
                    bar += i < percent ? "|" : ".";
                }

                bar += "]";
                player.yellowMessage(monster.getName() + " (" + monster.getId() + ") has " + percent + "% HP left.");
                player.yellowMessage("HP: " + bar);
            }
        }
    }
}
