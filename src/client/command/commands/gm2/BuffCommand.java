package client.command.commands.gm2;

import client.Skill;
import client.SkillFactory;
import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.javatuples.Quartet;

public class BuffCommand extends Command {

    {
        setName("buff");
        setDescription("Apply a buff by skill id.");

        setParameters(new ArrayList<>(
                Arrays.asList(
                        Quartet.with("id", true, (List<String>) new ArrayList<String>(), "")
                )
        ));
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !buff <buffid>");
            return;
        }

        int skillid = Integer.parseInt(params[0]);

        Skill skill = SkillFactory.getSkill(skillid);

        if (skill != null) {
            skill.getEffect(skill.getMaxLevel()).applyTo(player);
        }
    }
}
