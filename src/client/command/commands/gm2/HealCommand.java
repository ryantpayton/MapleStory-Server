package client.command.commands.gm2;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;

public class HealCommand extends Command {

    {
        setName("heal");
        setDescription("Recover HP/MP to fullest.");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        player.healHpMp();
    }
}
