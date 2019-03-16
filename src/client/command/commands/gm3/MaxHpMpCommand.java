package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;

public class MaxHpMpCommand extends Command {

    {
        setName("maxhpmp");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        MapleCharacter victim = player;

        int statUpdate = 1;

        if (params.length >= 2) {
            victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);
            statUpdate = Integer.valueOf(params[1]);
        } else if (params.length == 1) {
            statUpdate = Integer.valueOf(params[0]);
        } else {
            player.yellowMessage("Syntax: !maxhpmp [<playername>] <value>");
        }

        if (victim != null) {
            int extraHp = victim.getCurrentMaxHp() - victim.getClientMaxHp();
            int extraMp = victim.getCurrentMaxMp() - victim.getClientMaxMp();
            statUpdate = Math.max(1 + Math.max(extraHp, extraMp), statUpdate);

            int maxhpUpdate = statUpdate - extraHp;
            int maxmpUpdate = statUpdate - extraMp;
            victim.updateMaxHpMaxMp(maxhpUpdate, maxmpUpdate);
        } else {
            player.message("Player '" + params[0] + "' could not be found on this world.");
        }
    }
}
