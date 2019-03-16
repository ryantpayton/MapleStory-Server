package client.command.commands.gm4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import net.server.channel.Channel;
import server.life.MapleLifeFactory;
import server.life.MapleNPC;
import client.command.Command;
import client.MapleCharacter;
import client.MapleClient;
import java.awt.Point;
import server.maps.MapleMap;
import tools.DatabaseConnection;
import tools.MaplePacketCreator;

public class PnpcCommand extends Command {

    {
        setName("pnpc");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !pnpc <npcid>");
            return;
        }

        // Command suggestion thanks to HighKey21, none, bibiko94 (TAYAMO), asafgb
        int mapId = player.getMapId();
        int npcId = Integer.parseInt(params[0]);

        if (player.getMap().containsNPC(npcId)) {
            player.dropMessage(5, "This map already contains the specified NPC.");
            return;
        }

        MapleNPC npc = MapleLifeFactory.getNPC(npcId);
        Point checkpos = player.getMap().getGroundBelow(player.getPosition());
        int xpos = checkpos.x;
        int ypos = checkpos.y;
        int fh = player.getMap().getFootholds().findBelow(checkpos).getId();

        if (npc != null && !npc.getName().equals("MISSINGNO")) {
            try {
                Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO plife ( life, f, fh, cy, rx0, rx1, type, x, y, world, map, mobtime, hide ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
                ps.setInt(1, npcId);
                ps.setInt(2, 0);
                ps.setInt(3, fh);
                ps.setInt(4, ypos);
                ps.setInt(5, xpos + 50);
                ps.setInt(6, xpos - 50);
                ps.setString(7, "n");
                ps.setInt(8, xpos);
                ps.setInt(9, ypos);
                ps.setInt(10, player.getWorld());
                ps.setInt(11, mapId);
                ps.setInt(12, -1);
                ps.setInt(13, 0);
                ps.executeUpdate();
                ps.close();
                con.close();

                for (Channel ch : player.getWorldServer().getChannels()) {
                    npc = MapleLifeFactory.getNPC(npcId);
                    npc.setPosition(checkpos);
                    npc.setCy(ypos);
                    npc.setRx0(xpos + 50);
                    npc.setRx1(xpos - 50);
                    npc.setFh(fh);

                    MapleMap map = ch.getMapFactory().getMap(mapId);
                    map.addMapObject(npc);
                    map.broadcastMessage(MaplePacketCreator.spawnNPC(npc));
                }

                player.yellowMessage("Pnpc created.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                player.dropMessage(5, "Failed to store pNPC in the database.");
            }
        } else {
            player.dropMessage(5, "You have entered an invalid NPC id.");
        }
    }
}
