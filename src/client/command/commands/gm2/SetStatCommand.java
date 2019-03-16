package client.command.commands.gm2;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;

public class SetStatCommand extends Command {

    {
        setName("setstat");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !setstat <newstat>");
            return;
        }

        try {
            int x = Integer.parseInt(params[0]);

            if (x > Short.MAX_VALUE) {
                x = Short.MAX_VALUE;
            } else if (x < 4) {
                // Thanks Vcoc for pointing the minimal allowed stat value here
                x = 4;
            }

            player.updateStrDexIntLuk(x);
        } catch (NumberFormatException nfe) {
            // TODO: Blank?
        }
    }
}
