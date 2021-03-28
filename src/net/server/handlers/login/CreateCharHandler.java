/*
 This file is part of the OdinMS Maple Story Server
 Copyright (C) 2008 Patrick Huy <patrick.huy@frz.cc>
 Matthias Butz <matze@odinms.de>
 Jan Christian Meyer <vimes@odinms.de>

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU Affero General Public License as
 published by the Free Software Foundation version 3 as published by
 the Free Software Foundation. You may not use, modify or distribute
 this program under any other version of the GNU Affero General Public
 License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Affero General Public License for more details.

 You should have received a copy of the GNU Affero General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.server.handlers.login;

import client.MapleClient;
import client.creator.novice.*;
import constants.ServerConstants;
import net.AbstractMaplePacketHandler;
import tools.FilePrinter;
import tools.MaplePacketCreator;
import tools.data.input.SeekableLittleEndianAccessor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class CreateCharHandler extends AbstractMaplePacketHandler {
    private final static Set<Integer> IDs = new HashSet<>(Arrays.asList(new Integer[]{
            1302000, 1312004, 1322005, 1442079,                                         // Weapons
            1040002, 1040006, 1040010, 1041002, 1041006, 1041010, 1041011, 1042167,     // Bottom
            1060002, 1060006, 1061002, 1061008, 1062115,                                // Top
            1072001, 1072005, 1072037, 1072038, 1072383,                                // Shoes
            30000, 30010, 30020, 30030, 31000, 31040, 31050,                            // Hair
            20000, 20001, 20002, 21000, 21001, 21002, 21201, 20401, 20402, 21700, 20100 // Face
    }));

    private static boolean isLegal(Integer toCompare) {
        return IDs.contains(toCompare);
    }

    @Override
    public final void handlePacket(SeekableLittleEndianAccessor slea, MapleClient c) {
        String name = slea.readMapleAsciiString();
        int job = slea.readInt();
        int face = slea.readInt();

        int hair;
        int hairstyle;
        int haircolor;

        if (ServerConstants.USE_CUSTOM_CLIENT) {
            hair = slea.readInt();
        } else {
            hairstyle = slea.readInt();
            haircolor = slea.readInt();

            hair = hairstyle + haircolor;
        }

        int skin = slea.readInt();
        int top = slea.readInt();
        int bottom = slea.readInt();
        int shoes = slea.readInt();
        int weapon = slea.readInt();
        int gender = slea.readByte();

        if (ServerConstants.USE_CUSTOM_CLIENT) {
            // TODO: Add check for invalid items
        } else {
            int[] items = new int[]{weapon, top, bottom, shoes, hairstyle, face};

            for (int i = 0; i < items.length; i++) {
                if (!isLegal(items[i])) {
                    FilePrinter.printError(FilePrinter.EXPLOITS + name + ".txt", "Owner from account '" + c.getAccountName() + "' tried to packet edit in char creation.");
                    c.disconnect(true, false);
                    return;
                }
            }
        }

        int status;

        if (job == 0) {
            // Cygnus Knights
            status = NoblesseCreator.createCharacter(c, name, face, hair, skin, top, bottom, shoes, weapon, gender);
        } else if (job == 1) {
            // Explorer
            status = BeginnerCreator.createCharacter(c, name, face, hair, skin, top, bottom, shoes, weapon, gender);
        } else if (job == 2) {
            // Aran
            status = LegendCreator.createCharacter(c, name, face, hair, skin, top, bottom, shoes, weapon, gender);
        } else {
            c.announce(MaplePacketCreator.deleteCharResponse(0, 9));
            return;
        }

        if (status == -2) {
            c.announce(MaplePacketCreator.deleteCharResponse(0, 9));
        }
    }
}