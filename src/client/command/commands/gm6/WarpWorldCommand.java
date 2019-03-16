package client.command.commands.gm6;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import net.server.Server;
import tools.MaplePacketCreator;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class WarpWorldCommand extends Command {

    {
        setName("warpworld");
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !warpworld <worldid>");
            return;
        }

        Server server = Server.getInstance();
        byte worldb = Byte.parseByte(params[0]);

        if (worldb <= (server.getWorldsSize() - 1)) {
            try {
                String[] socket = server.getInetSocket(worldb, c.getChannel());
                c.getWorldServer().removePlayer(player);
                player.getMap().removePlayer(player); // LOL FORGOT THIS ><
                c.updateLoginState(MapleClient.LOGIN_SERVER_TRANSITION);
                player.setWorld(worldb);
                player.saveCharToDB(); // To set the new world :O (true because else 2 player instances are created, one in both worlds)
                c.announce(MaplePacketCreator.getChannelChange(InetAddress.getByName(socket[0]), Integer.parseInt(socket[1])));
            } catch (UnknownHostException | NumberFormatException ex) {
                ex.printStackTrace();
                player.message("Unexpected error when changing worlds, are you sure the world you are trying to warp to has the same amount of channels?");
            }
        } else {
            player.message("Invalid world; highest number available: " + (server.getWorldsSize() - 1));
        }
    }
}
