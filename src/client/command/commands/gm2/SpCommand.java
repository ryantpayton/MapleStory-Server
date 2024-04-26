package client.command.commands.gm2;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import constants.ServerConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.javatuples.Quartet;

public class SpCommand extends Command {

    {
        setName("sp");
        setDescription("Set remaining amount of skill points available.");

        setParameters(new ArrayList<>(
                Arrays.asList(
                        Quartet.with("player", false, (List<String>) new ArrayList<String>(), "#bIf #k[player]#b is left blank the skill points will be assigned to you."),
                        Quartet.with("amount", true, (List<String>) new ArrayList<String>(), "")
                )
        ));
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !sp [<playername>] <newsp>");
            return;
        }

        if (params.length == 1) {
            int newSp = Integer.parseInt(params[0]);

            if (newSp < 0) {
                newSp = 0;
            } else if (newSp > ServerConstants.MAX_AP) {
                newSp = ServerConstants.MAX_AP;
            }

            player.updateRemainingSp(newSp);
        } else {
            MapleCharacter victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);

            if (victim != null) {
                int newSp = Integer.parseInt(params[1]);

                if (newSp < 0) {
                    newSp = 0;
                } else if (newSp > ServerConstants.MAX_AP) {
                    newSp = ServerConstants.MAX_AP;
                }

                victim.updateRemainingSp(newSp);

                player.dropMessage(5, "SP given.");
            } else {
                player.message("Player '" + params[0] + "' could not be found.");
            }
        }
    }
}
