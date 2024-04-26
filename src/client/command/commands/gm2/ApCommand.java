package client.command.commands.gm2;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import constants.ServerConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.javatuples.Quartet;

public class ApCommand extends Command {

    {
        setName("ap");
        setDescription("Set remaining amount of ability points available.");

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
            player.yellowMessage("Syntax: !ap [<playername>] <newap>");
            return;
        }

        if (params.length < 2) {
            int newAp = Integer.parseInt(params[0]);

            if (newAp < 0) {
                newAp = 0;
            } else if (newAp > ServerConstants.MAX_AP) {
                newAp = ServerConstants.MAX_AP;
            }

            player.changeRemainingAp(newAp, false);
        } else {
            MapleCharacter victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);

            if (victim != null) {
                int newAp = Integer.parseInt(params[1]);

                if (newAp < 0) {
                    newAp = 0;
                } else if (newAp > ServerConstants.MAX_AP) {
                    newAp = ServerConstants.MAX_AP;
                }

                victim.changeRemainingAp(newAp, false);
            } else {
                player.message("Player '" + params[0] + "' could not be found.");
            }
        }
    }
}
