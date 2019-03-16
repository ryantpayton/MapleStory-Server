package client.command.commands.gm1;

import client.MapleCharacter;
import client.command.Command;
import client.MapleClient;
import server.life.MapleMonster;

public class MobHpCommand extends Command {

    {
        setName("mobhp");
        setDescription("See remaining HP of any monsters in the current map.");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        for (MapleMonster monster : player.getMap().getMonsters()) {
            if (monster != null && monster.getHp() > 0) {
                player.yellowMessage(monster.getName() + " (" + monster.getId() + ") has " + monster.getHp() + " / " + monster.getMaxHp() + " HP.");
            }
        }
    }
}
