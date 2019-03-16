package client.command.commands.gm1;

import client.MapleCharacter;
import client.SkillFactory;
import client.command.Command;
import client.MapleClient;

public class BuffMeCommand extends Command {

    {
        setName("buffme");
        setDescription("Receive Haste, Holy Symbol, Bless, Hyper Body, and Echo of Hero blessings.");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();
        SkillFactory.getSkill(4101004).getEffect(SkillFactory.getSkill(4101004).getMaxLevel()).applyTo(player);
        SkillFactory.getSkill(2311003).getEffect(SkillFactory.getSkill(2311003).getMaxLevel()).applyTo(player);
        SkillFactory.getSkill(1301007).getEffect(SkillFactory.getSkill(1301007).getMaxLevel()).applyTo(player);
        SkillFactory.getSkill(2301004).getEffect(SkillFactory.getSkill(2301004).getMaxLevel()).applyTo(player);
        SkillFactory.getSkill(1005).getEffect(SkillFactory.getSkill(1005).getMaxLevel()).applyTo(player);
        player.healHpMp();
    }
}
