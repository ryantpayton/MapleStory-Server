package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import server.life.MapleLifeFactory;
import server.life.MapleNPC;
import tools.MaplePacketCreator;

public class NpcCommand extends Command {

    {
        setName("npc");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !npc <npcid>");
            return;
        }

        MapleNPC npc = MapleLifeFactory.getNPC(Integer.parseInt(params[0]));

        if (npc != null) {
            npc.setPosition(player.getPosition());
            npc.setCy(player.getPosition().y);
            npc.setRx0(player.getPosition().x + 50);
            npc.setRx1(player.getPosition().x - 50);
            npc.setFh(player.getMap().getFootholds().findBelow(c.getPlayer().getPosition()).getId());
            player.getMap().addMapObject(npc);
            player.getMap().broadcastMessage(MaplePacketCreator.spawnNPC(npc));
        }
    }
}
