/* global cm */

/*
 * @id NPC Id
 * @name NPC name
 * @locationIds All known locations by id
 * @locationNames All known locations by name
 * @description What the NPC does
 */

var status;

function start() {
    status = -1;

    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode === -1) {
        cm.dispose();
    } else {
        if (mode === 0 && type > 0) {
            cm.dispose();
            return;
        }

        if (mode === 1)
            status++;
        else
            status--;

        if (status === 0) {
            cm.sendOk("Sample text.");
            cm.dispose();
        }
    }
}

// Nice tool for generating a string for the sendSimple functionality
function generateSelectionMenu(array) {
    var menu = "";

    for (var i = 0; i < array.length; i++) {
        menu += "#L" + i + "#" + array[i] + "#l\r\n";
    }

    return menu;
}