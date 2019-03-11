/* global qm */

/*
 * @id Quest Id
 * @name Quest name
 * @npcid NPC Id
 * @npcname NPC name
 * @locationIds All known locations by id
 * @locationNames All known locations by name
 * @description What the quest does
 */

var status = -1;

function start(mode, type, selection) {
    if (mode === -1) {
        qm.dispose();
    } else {
        if (mode === 0 && type > 0) {
            qm.dispose();
            return;
        }

        if (mode === 1)
            status++;
        else
            status--;

        if (status === 0) {
            qm.sendNext("Sample Text.");
        } else if (status === 1) {
            qm.forceStartQuest();
            qm.dispose();
        }
    }
}

function end(mode, type, selection) {
    if (mode === -1) {
        qm.dispose();
    } else {
        if (mode === 0 && type > 0) {
            qm.dispose();
            return;
        }

        if (mode === 1)
            status++;
        else
            status--;

        if (status === 0) {
            qm.sendNext("Sample Text.");
        } else if (status === 1) {
            qm.forceCompleteQuest();
            qm.dispose();
        }
    }
}