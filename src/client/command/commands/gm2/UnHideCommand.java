package client.command.commands.gm2;

import client.SkillFactory;
import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;

public class UnHideCommand extends Command {

    {
        setName("unhide");
        setDescription("Toggle between the GM hidden state.");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        SkillFactory.getSkill(9101004).getEffect(SkillFactory.getSkill(9101004).getMaxLevel()).applyTo(player);
    }
}
