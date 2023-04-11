package client.command.commands.gm2;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import server.life.MapleMonster;
import server.life.MapleNPC;
import server.life.MaplePlayerNPC;
import server.maps.MapleMapObject;
import java.util.HashSet;

public class WhereAmICommand extends Command {

    {
        setName("whereami");
        setDescription("See the current map name and map id.");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        HashSet<MapleCharacter> chars = new HashSet<>();
        HashSet<MapleNPC> npcs = new HashSet<>();
        HashSet<MaplePlayerNPC> playernpcs = new HashSet<>();
        HashSet<MapleMonster> mobs = new HashSet<>();

        for (MapleMapObject mmo : player.getMap().getMapObjects()) {
            if (mmo instanceof MapleNPC) {
                MapleNPC npc = (MapleNPC) mmo;
                npcs.add(npc);
            } else if (mmo instanceof MapleCharacter) {
                MapleCharacter mc = (MapleCharacter) mmo;
                chars.add(mc);
            } else if (mmo instanceof MapleMonster) {
                MapleMonster mob = (MapleMonster) mmo;

                if (mob.isAlive()) {
                    mobs.add(mob);
                }
            } else if (mmo instanceof MaplePlayerNPC) {
                MaplePlayerNPC npc = (MaplePlayerNPC) mmo;
                playernpcs.add(npc);
            }
        }

        player.yellowMessage("Map ID: " + player.getMap().getId());

        player.yellowMessage("Players on this map:");

        for (MapleCharacter chr : chars) {
            player.dropMessage(5, ">> " + chr.getName() + " - " + chr.getId() + " - Oid: " + chr.getObjectId());
        }

        if (!playernpcs.isEmpty()) {
            player.yellowMessage("PlayerNPCs on this map:");

            for (MaplePlayerNPC pnpc : playernpcs) {
                player.dropMessage(5, ">> " + pnpc.getName() + " - Scriptid: " + pnpc.getScriptId() + " - Oid: " + pnpc.getObjectId());
            }
        }

        if (!npcs.isEmpty()) {
            player.yellowMessage("NPCs on this map:");

            for (MapleNPC npc : npcs) {
                player.dropMessage(5, ">> " + npc.getName() + " - " + npc.getId() + " - Oid: " + npc.getObjectId());
            }
        }

        if (!mobs.isEmpty()) {
            player.yellowMessage("Monsters on this map:");

            for (MapleMonster mob : mobs) {
                if (mob.isAlive()) {
                    player.dropMessage(5, ">> " + mob.getName() + " - " + mob.getId() + " - Oid: " + mob.getObjectId());
                }
            }
        }
    }
}
