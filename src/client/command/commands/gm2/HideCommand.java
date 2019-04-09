package client.command.commands.gm2;

import client.SkillFactory;
import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import constants.skills.SuperGM;

public class HideCommand extends Command {

    {
        setName("hide");
        setDescription("Toggle between the GM hidden state.");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        SkillFactory.getSkill(SuperGM.HIDE).getEffect(SkillFactory.getSkill(SuperGM.HIDE).getMaxLevel()).applyTo(player);
    }
}
