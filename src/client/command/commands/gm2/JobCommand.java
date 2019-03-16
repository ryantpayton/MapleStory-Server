package client.command.commands.gm2;

import client.MapleJob;
import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;

public class JobCommand extends Command {

    {
        setName("job");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length == 1) {
            int jobid = Integer.parseInt(params[0]);

            if (jobid < 0 || jobid >= 2200) {
                player.message("Jobid " + jobid + " is not available.");
                return;
            }

            player.changeJob(MapleJob.getById(jobid));
            player.equipChanged();
        } else if (params.length == 2) {
            MapleCharacter victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);

            if (victim != null) {
                int jobid = Integer.parseInt(params[1]);

                if (jobid < 0 || jobid >= 2200) {
                    player.message("Jobid " + jobid + " is not available.");
                    return;
                }

                victim.changeJob(MapleJob.getById(jobid));
                player.equipChanged();
            } else {
                player.message("Player '" + params[0] + "' could not be found.");
            }
        } else {
            player.message("Syntax: !job <job id> <opt: IGN of another person>");
        }
    }
}
