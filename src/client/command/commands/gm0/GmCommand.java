package client.command.commands.gm0;

import client.MapleCharacter;
import client.command.Command;
import client.MapleClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.server.Server;
import org.javatuples.Quartet;
import tools.FilePrinter;
import tools.MaplePacketCreator;
import tools.Randomizer;

public class GmCommand extends Command {

    {
        setName("gm");
        setDescription("Send a message to a GM.");

        setParameters(new ArrayList<>(
                Arrays.asList(
                        Quartet.with("message", true, (List<String>) new ArrayList<String>(), "")
                )
        ));
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        String[] tips = {
                "Please only use @gm in emergencies or to report somebody.",
                "To report a bug or make a suggestion, use the forum.",
                "Please do not use @gm to ask if a GM is online.",
                "Do not ask if you can receive help, just state your issue.",
                "Do not say 'I have a bug to report', just state it."
        };

        MapleCharacter player = c.getPlayer();

        if (params.length < 1 || params[0].length() < 3) {
            player.dropMessage(5, "Your message was too short. Please provide as much detail as possible.");
            return;
        }

        String message = player.getLastCommandMessage();
        Server.getInstance().broadcastGMMessage(c.getWorld(), MaplePacketCreator.sendYellowTip("[GM MESSAGE]:" + MapleCharacter.makeMapleReadable(player.getName()) + ": " + message));
        Server.getInstance().broadcastGMMessage(c.getWorld(), MaplePacketCreator.serverNotice(1, message));
        FilePrinter.printError(FilePrinter.COMMAND_GM, MapleCharacter.makeMapleReadable(player.getName()) + ": " + message);
        player.dropMessage(5, "Your message '" + message + "' was sent to GMs.");
        player.dropMessage(5, tips[Randomizer.nextInt(tips.length)]);
    }
}
