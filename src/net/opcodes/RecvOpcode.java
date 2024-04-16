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
package net.opcodes;

public enum RecvOpcode {
    
    LOGIN_PASSWORD(0x01),
    GUEST_LOGIN(0x02), //basically unused
    LOGIN_EMAIL(0x03), //TODO: should remove this, we don't support email login
    SERVERINFO_REQUEST(0x04),
    CHARLIST_REQUEST(0x05), //TODO
    SERVERSTATUS_REQUEST(0x06), //TODO
    ACCEPT_TOS(0x07), //TODO: we really shouldn't need this either
    SET_GENDER(0x08), //don't need
    AFTER_LOGIN(0x09), //TODO: do we need this still? are we doing account pins? isn't for resetting info on the site?
    REGISTER_PIN(0x0A), //TODO see above
    SERVERLIST_REQUEST(0x0B), //same thing as serverinfo
    PLAYER_DC(0x0C),
    VIEW_ALL_CHAR(0x0D),
    PICK_ALL_CHAR(0x0E),
    NAME_TRANSFER(0x10), //TODO pretty sure this is unused in the v92 codebase
    WORLD_TRANSFER(0x12), //TODO pretty sure this is unused in the v92 codebase
    CHAR_SELECT(0x13),
    PLAYER_LOGGEDIN(0x14), //TODO
    CHECK_CHAR_NAME(0x15), //TODO
    CREATE_CHAR(0x16), //TODO
    DELETE_CHAR(0x17), //TODO
    PONG(0x18), //TODO
    CLIENT_START_ERROR(0x19), //TODO
    CLIENT_ERROR(0x1A), //TODO
    STRANGE_DATA(0x1B), //TODO this isnt' in v92 or is written as PACKET_ERROR
    RELOG(0x1C), //TODO
    REGISTER_PIC(0x1D), //TODO
    CHAR_SELECT_WITH_PIC(0x1E), //TODO
    VIEW_ALL_PIC_REGISTER(0x1F), //TODO
    VIEW_ALL_WITH_PIC(0x20), //TODO
    CHANGE_MAP(0x26), //TODO
    CHANGE_CHANNEL(0x27), //TODO
    ENTER_CASHSHOP(0x28), //TODO
    MOVE_PLAYER(0x29), //TODO
    CANCEL_CHAIR(0x2A), //TODO
    USE_CHAIR(0x2B), //TODO
    CLOSE_RANGE_ATTACK(0x2C), //TODO
    RANGED_ATTACK(0x2D), //TODO
    MAGIC_ATTACK(0x2E), //TODO
    TOUCH_MONSTER_ATTACK(0x2F), //TODO
    TAKE_DAMAGE(0x30), //TODO
    GENERAL_CHAT(0x31), //TODO
    CLOSE_CHALKBOARD(0x32), //TODO
    FACE_EXPRESSION(0x33), //TODO
    USE_ITEMEFFECT(0x34), //TODO
    USE_DEATHITEM(0x35), //TODO
    MOB_BANISH_PLAYER(0x38), //TODO (this is unused in v92 but may be because they couldn't figure out how to use it)
    MONSTER_BOOK_COVER(0x39), //TODO
    NPC_TALK(0x3A), //TODO
    REMOTE_STORE(0x3B), //TODO
    NPC_TALK_MORE(0x3C), //TODO
    NPC_SHOP(0x3D), //TODO
    STORAGE(0x3E), //TODO
    HIRED_MERCHANT_REQUEST(0x3F), //TODO
    FREDRICK_ACTION(0x40), //TODO
    DUEY_ACTION(0x41), //TODO
    OWL_ACTION(0x42),   //sends most searched info to client //TODO
    OWL_WARP(0x43),     //handles player warp to store //TODO
    ADMIN_SHOP(0x44), //TODO
    ITEM_GATHER(0x45), //TODO
    ITEM_SORT(0x46), //TODO
    ITEM_MOVE(0x47), //TODO
    USE_ITEM(0x48), //TODO
    CANCEL_ITEM_EFFECT(0x49), //TODO
    USE_SUMMON_BAG(0x4B), //TODO
    PET_FOOD(0x4C), //TODO
    USE_MOUNT_FOOD(0x4D), //TODO
    SCRIPTED_ITEM(0x4E), //TODO
    USE_CASH_ITEM(0x4F), //TODO
    //USE_OWL_ITEM(0x50), ... no idea
    USE_CATCH_ITEM(0x51), //TODO
    USE_SKILL_BOOK(0x52), //TODO
    USE_TELEPORT_ROCK(0x54), //TODO
    USE_RETURN_SCROLL(0x55), //TODO
    USE_UPGRADE_SCROLL(0x56), //TODO
    DISTRIBUTE_AP(0x57), //TODO
    AUTO_DISTRIBUTE_AP(0x58), //TODO
    HEAL_OVER_TIME(0x59), //TODO: needs to handle chairs as well
    DISTRIBUTE_SP(0x5A), //TODO
    SPECIAL_MOVE(0x5B), //TODO
    CANCEL_BUFF(0x5C), //TODO
    SKILL_EFFECT(0x5D), //TODO
    MESO_DROP(0x5E), //TODO
    GIVE_FAME(0x5F), //TODO
    CHAR_INFO_REQUEST(0x61), //TODO
    SPAWN_PET(0x62), //TODO
    CANCEL_DEBUFF(0x63), //TODO
    CHANGE_MAP_SPECIAL(0x64), //TODO
    USE_INNER_PORTAL(0x65), //TODO
    TROCK_ADD_MAP(0x66), //TODO
    REPORT(0x6A), //TODO
    QUEST_ACTION(0x6B), //TODO
    //USER_CALC_DAMAGE_STAT_SET_REQUEST(0x6C),
    GRENADE_EFFECT(0x6D), //TODO wtf is a grenade
    SKILL_MACRO(0x6E), //TODO
    USE_ITEM_REWARD(0x70), //TODO
    MAKER_SKILL(0x71), //TODO (we gotta get rid of this and replace it)
    USE_REMOTE(0x74), //TODO
    WATER_OF_LIFE(0x75), //TODO
    ADMIN_CHAT(0x76), //TODO
    MULTI_CHAT(0x77), //TODO (should be renamed PARTY_CHAT)
    WHISPER(0x78), //TODO
    SPOUSE_CHAT(0x79), //TODO
    MESSENGER(0x7A), //TODO
    PLAYER_INTERACTION(0x7B), //TODO
    PARTY_OPERATION(0x7C), //TODO (v92 has party request and party result - do we want to split those up?)
    DENY_PARTY_REQUEST(0x7D), //TODO (this is gone in v92 and is just PARTY_RESULT)
    GUILD_OPERATION(0x7E), //TODO: see above
    DENY_GUILD_REQUEST(0x7F), //TODO
    ADMIN_COMMAND(0x80), //TODO
    ADMIN_LOG(0x81), //TODO
    BUDDYLIST_MODIFY(0x82), //TODO
    NOTE_ACTION(0x83), //TODO
    USE_DOOR(0x85), //TODO
    CHANGE_KEYMAP(0x87), //TODO
    RPS_ACTION(0x88), //TODO
    RING_ACTION(0x89),  //TODO
    WEDDING_ACTION(0x8A), //TODO
    WEDDING_TALK(0x8B), //TODO (is this even in v92?)
    WEDDING_TALK_MORE(0x8B), //TODO (same)
    ALLIANCE_OPERATION(0x8F), //TODO (this was also split into request/response)
    DENY_ALLIANCE_REQUEST(0x90), //TODO (see party/guild)
    OPEN_FAMILY(0x92), //TODO honestly pretty sure we can get rid of family as a whole
    ADD_FAMILY(0x93), //TODO
    ACCEPT_FAMILY(0x96), //TODO
    USE_FAMILY(0x97), //TODO
    BBS_OPERATION(0x9B), //TODO
    ENTER_MTS(0x9C), //TODO
    USE_SOLOMON_ITEM(0x9D), //TODO
    USE_GACHA_EXP(0x9E), //TODO
    NEW_YEAR_CARD_REQUEST(0x9F), //TODO (what is this even for?)
    CASHSHOP_SURPRISE(0xA1), //TODO this was disappeared in v92
    CLICK_GUIDE(0xA2), //TODO
    ARAN_COMBO_COUNTER(0xA3), //TODO
    MOVE_PET(0xA7), //TODO
    PET_CHAT(0xA8), //TODO
    PET_COMMAND(0xA9), //TODO
    PET_LOOT(0xAA), //TODO
    PET_AUTO_POT(0xAB), //TODO
    PET_EXCLUDE_ITEMS(0xAC), //TODO
    MOVE_SUMMON(0xAF), //TODO
    SUMMON_ATTACK(0xB0), //TODO
    DAMAGE_SUMMON(0xB1), //TODO
    BEHOLDER(0xB2), //TODO
    MOVE_DRAGON(0xB5), //TODO
    MOVE_LIFE(0xBC), //TODO
    AUTO_AGGRO(0xBD), //TODO
    MOB_DAMAGE_MOB_FRIENDLY(0xC0), //TODO
    MONSTER_BOMB(0xC1), //TODO (self destruct)
    MOB_DAMAGE_MOB(0xC2), //TODO
    NPC_ACTION(0xC5), //TODO
    ITEM_PICKUP(0xCA), //TODO
    DAMAGE_REACTOR(0xCD), //TODO
    TOUCHING_REACTOR(0xCE), //TODO
    PLAYER_MAP_TRANSFER(0xCF), //TODO
    MAPLETV(0xFFFE),//Don't know
    SNOWBALL(0xD3), //TODO
    LEFT_KNOCKBACK(0xD4), //TODO
    COCONUT(0xD5), //TODO
    MATCH_TABLE(0xD6),//Would be cool if I ever get it to work :) TODO what even is this
    MONSTER_CARNIVAL(0xDA), //TODO this would be really nice
    PARTY_SEARCH_REGISTER(0xDC), //TODO
    PARTY_SEARCH_START(0xDE), //TODO
    PLAYER_UPDATE(0xDF), //TODO
    CHECK_CASH(0xE4), //TODO
    CASHSHOP_OPERATION(0xE5), //TODO
    COUPON_CODE(0xE6), //TODO
    OPEN_ITEMUI(0xEC), //TODO
    CLOSE_ITEMUI(0xED), //TODO
    USE_ITEMUI(0xEE), //TODO
    MTS_OPERATION(0xFD), //TODO
    USE_MAPLELIFE(0x100), //TODO (gone in v92)
    USE_HAMMER(0x104), //TODO (gone in v92)

    //new packets and uses
    CLIENT_START(0x1000), //TODO: this does exist in v92 and is used when the game client starts. We can use this as an update check
    //we will need to define some new packets as part of the client start to send over update hash info to update the client

    //v92 new packets
    USE_STARFORCE_SCROLL(0x9200), //TODO: this is a request to apply a starforce scroll (EES) to an item
    USE_MAGNIFYING_GLASS(0x9201), //TODO: this is a request to use a magnifying glass on an item
    USE_POTENTIAL_SCROLL(0x9202), //TODO: this is a request to use a potential scroll
    USE_MIRACLE_CUBE(0x9203), //TODO: this isn't in the base v92 (its rolled into CASH_ITEM) but this is a lot easier
    FOLLOW_OTHER_USER_REQUEST(0x9204), //TODO: this is for "follow" function
    CANCEL_FOLLOW_REQUEST(0x9207), //TODO
    SET_FOLLOW_RESULT(0x9208), //TODO
    QUICKSLOT_CHANGE(0x9209), //TODO: not even sure what this is,
    EXPEDITION_REQUEST(0x920A), //TODO: this is critical for expeditions (how we are gonna structure most raids)
    //other missing stuff: friend finder, a few special ops for handling escort missions like hoblin
    CUSTOM_PACKET(0x3713) ; //13 37 lol
    private int code = -2;

    private RecvOpcode(int code) {
        this.code = code;
    }

    public int getValue() {
        return code;
    }
}
