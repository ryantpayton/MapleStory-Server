package client.command.commands.gm0;

import client.MapleCharacter;
import client.MapleClient;
import client.command.Command;
import constants.ServerConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.javatuples.Quartet;

public class StatLukCommand extends Command {

    {
        setName("luk");
        setDescription("Apply a set number of ability points to LUK.");

        setParameters(new ArrayList<>(
                Arrays.asList(
                        Quartet.with("amount", false, (List<String>) new ArrayList<String>(), "#bIf #k[amount]#b is left blank the remaining ability points will be used.")
                )
        ));
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        int remainingAp = player.getRemainingAp();
        int amount;

        if (params.length > 0) {
            try {
                amount = Math.min(Integer.parseInt(params[0]), remainingAp);
            } catch (NumberFormatException e) {
                player.dropMessage("That is not a valid number!");
                return;
            }
        } else {
            amount = Math.min(remainingAp, ServerConstants.MAX_AP - player.getLuk());
        }

        if (!player.assignLuk(Math.max(amount, 0))) {
            player.dropMessage("Please make sure your AP is not over " + ServerConstants.MAX_AP + " and you have enough to distribute.");
        }
    }
}
