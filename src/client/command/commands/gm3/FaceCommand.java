package client.command.commands.gm3;

import client.MapleStat;
import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import constants.ItemConstants;
import server.MapleItemInformationProvider;

public class FaceCommand extends Command {

    {
        setName("face");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !face [<playername>] <faceid>");
            return;
        }

        try {
            if (params.length == 1) {
                int itemId = Integer.parseInt(params[0]);

                if (!ItemConstants.isFace(itemId) || MapleItemInformationProvider.getInstance().getName(itemId) == null) {
                    player.yellowMessage("Face id '" + params[0] + "' does not exist.");
                    return;
                }

                player.setFace(itemId);
                player.updateSingleStat(MapleStat.FACE, itemId);
                player.equipChanged();
            } else {
                int itemId = Integer.parseInt(params[1]);

                if (!ItemConstants.isFace(itemId) || MapleItemInformationProvider.getInstance().getName(itemId) == null) {
                    player.yellowMessage("Face id '" + params[1] + "' does not exist.");
                }

                MapleCharacter victim = c.getChannelServer().getPlayerStorage().getCharacterByName(params[0]);

                if (victim != null) {
                    victim.setFace(itemId);
                    victim.updateSingleStat(MapleStat.FACE, itemId);
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
