package client.command.commands.gm2;

import client.SkillFactory;
import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;

public class BuffMapCommand extends Command {

    {
        setName("buffmap");

        setDescription(
                "Gives the following buffs to all players on the map:\r\n"
                        + "#L0#Super Haste#l\r\n"
                        + "#L1#Super Holy Symbol#l\r\n"
                        + "#L2#Super Bless#l\r\n"
                        + "#L3#Super Hyper Body#l\r\n"
                        + "#L4#Super Echo of Hero#l\r\n"
        );
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        SkillFactory.getSkill(9101001).getEffect(SkillFactory.getSkill(9101001).getMaxLevel()).applyTo(player, true);
        SkillFactory.getSkill(9101002).getEffect(SkillFactory.getSkill(9101002).getMaxLevel()).applyTo(player, true);
        SkillFactory.getSkill(9101003).getEffect(SkillFactory.getSkill(9101003).getMaxLevel()).applyTo(player, true);
        SkillFactory.getSkill(9101008).getEffect(SkillFactory.getSkill(9101008).getMaxLevel()).applyTo(player, true);
        SkillFactory.getSkill(1005).getEffect(SkillFactory.getSkill(1005).getMaxLevel()).applyTo(player, true);
    }
}
