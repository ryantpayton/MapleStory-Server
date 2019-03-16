package client.command.commands.gm3;

import client.MapleStat;
import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;

public class FameCommand extends Command {

    {
        setName("fame");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 2) {
            player.yellowMessage("Syntax: !fame <playername> <gainfame>");
            return;
        }

        MapleCharacter victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);

        if (victim != null) {
            victim.setFame(Integer.parseInt(params[1]));
            victim.updateSingleStat(MapleStat.FAME, victim.getFame());
            player.message("FAME given.");
        } else {
            player.message("Player '" + params[0] + "' could not be found.");
        }
    }
}
