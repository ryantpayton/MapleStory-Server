package client.command.commands.gm3;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import constants.GameConstants;
import tools.MaplePacketCreator;

public class MusicCommand extends Command {

    {
        setName("music");
    }

    private static String getSongList() {
        String songList = "Song:\r\n";

        for (String s : GameConstants.GAME_SONGS) {
            songList += ("  " + s + "\r\n");
        }

        return songList;
    }

    @Override
    public void execute(MapleClient c, String[] params) {
        MapleCharacter player = c.getPlayer();

        if (params.length < 1) {
            String sendMsg = "";

            sendMsg += "Syntax: #r!music <song>#k\r\n\r\n";
            sendMsg += getSongList();

            c.announce(MaplePacketCreator.getNPCTalk(1052015, (byte) 0, sendMsg, "00 00", (byte) 0));
            return;
        }

        String song = player.getLastCommandMessage();

        for (String s : GameConstants.GAME_SONGS) {
            if (s.equalsIgnoreCase(song)) {    // thanks Masterrulax for finding an issue here
                player.getMap().broadcastMessage(MaplePacketCreator.musicChange(s));
                player.yellowMessage("Now playing song " + s + ".");
                return;
            }
        }

        String sendMsg = "";
        sendMsg += "Song not found, please enter a song below.\r\n\r\n";
        sendMsg += getSongList();

        c.announce(MaplePacketCreator.getNPCTalk(1052015, (byte) 0, sendMsg, "00 00", (byte) 0));
    }
}
