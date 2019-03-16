package client.command.commands.gm3;

import client.MapleStat;
import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import constants.ItemConstants;
import server.MapleItemInformationProvider;

public class HairCommand extends Command {

    {
        setName("hair");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !hair [<playername>] <hairid>");
            return;
        }

        try {
            if (params.length == 1) {
                int itemId = Integer.parseInt(params[0]);

                if (!ItemConstants.isHair(itemId) || MapleItemInformationProvider.getInstance().getName(itemId) == null) {
                    player.yellowMessage("Hair id '" + params[0] + "' does not exist.");
                    return;
                }

                player.setHair(itemId);
                player.updateSingleStat(MapleStat.HAIR, itemId);
                player.equipChanged();
            } else {
                int itemId = Integer.parseInt(params[1]);

                if (!ItemConstants.isHair(itemId) || MapleItemInformationProvider.getInstance().getName(itemId) == null) {
                    player.yellowMessage("Hair id '" + params[1] + "' does not exist.");
                    return;
                }

                MapleCharacter victim = c.getChannelServer().getPlayerStorage().getCharacterByName(params[0]);

                if (victim != null) {
                    victim.setHair(itemId);
                    victim.updateSingleStat(MapleStat.HAIR, itemId);
                    victim.equipChanged();
                } else {
                    player.yellowMessage("Player '" + params[0] + "' has not been found on this channel.");
                }
            }
        } catch (Exception ex) {
            // TODO: Blank?
        }
    }
}
