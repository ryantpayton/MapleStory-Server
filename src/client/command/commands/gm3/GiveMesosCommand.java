package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;

public class GiveMesosCommand extends Command {

    {
        setName("givems");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !givems [<playername>] <gainmeso>");
            return;
        }

        String recv_, value_;
        long mesos_ = 0;

        if (params.length == 2) {
            recv_ = params[0];
            value_ = params[1];
        } else {
            recv_ = c.getPlayer().getName();
            value_ = params[0];
        }

        try {
            mesos_ = Long.parseLong(value_);

            if (mesos_ > Integer.MAX_VALUE) {
                mesos_ = Integer.MAX_VALUE;
            } else if (mesos_ < Integer.MIN_VALUE) {
                mesos_ = Integer.MIN_VALUE;
            }
        } catch (NumberFormatException nfe) {
            // "max" descriptor suggestion thanks to Vcoc
            if (value_.contentEquals("max")) {
                mesos_ = Integer.MAX_VALUE;
            } else if (value_.contentEquals("min")) {
                mesos_ = Integer.MIN_VALUE;
            }
        }

        MapleCharacter victim = c.getWorldServer().getPlayerStorage().getCharacterByName(recv_);

        if (victim != null) {
            victim.gainMeso((int) mesos_, true);
            player.message("MESO given.");
        } else {
            player.message("Player '" + recv_ + "' could not be found.");
        }
    }
}
