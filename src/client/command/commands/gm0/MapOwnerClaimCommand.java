package client.command.commands.gm0;

import client.command.Command;
import client.MapleCharacter;
import client.MapleClient;
import constants.ServerConstants;

public class MapOwnerClaimCommand extends Command {

    {
        setName("mylawn");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        if (c.tryacquireClient()) {
            try {
                MapleCharacter chr = c.getPlayer();

                if (ServerConstants.USE_MAP_OWNERSHIP_SYSTEM) {
                    if (chr.getEventInstance() == null) {
                        if (chr.getMap().unclaimOwnership(chr)) {
                            chr.dropMessage(5, "This lawn is now free real estate.");
                        } else if (chr.getMap().claimOwnership(chr)) {
                            chr.dropMessage(5, "You have leased this lawn for a while, until you leave here or after 1 minute of inactivity.");
                        } else {
                            chr.dropMessage(5, "This lawn has already been leased by another player.");
                        }
                    } else {
                        chr.dropMessage(5, "This lawn cannot be leased.");
                    }
                } else {
                    chr.dropMessage(5, "Feature unavailable.");
                }
            } finally {
                c.releaseClient();
            }
        }
    }
}
