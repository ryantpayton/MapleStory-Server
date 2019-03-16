/* global cm */

/*
 * @id 9201143
 * @name Steward
 * @locationIds Any
 * @locationNames Any
 * @description Shows commands available for use
 */

importPackage(Packages.client.command);

var status;

var levels = ["Common", "Donator", "JrGM", "GM", "SuperGM", "Developer", "Admin"];
var commands, heading;
var lvComm, lvDesc, lvOther, lvParams;

function start() {
    status = -1;
    writeCommands();
    action(1, 0, 0);
}

function writeCommands() {
    var commandsExecutorInstance = CommandsExecutor.getInstance();

    commands = commandsExecutorInstance.getGmCommands();
    heading = commandsExecutorInstance.getHeading();
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
            var sendStr = "These are all the commands available to you:\r\n\r\n#b";

            for (var i = 0; i <= cm.getPlayer().gmLevel(); i++)
                if (i < levels.length)
                    sendStr += "#L" + i + "#" + levels[i] + "#l\r\n";

            cm.sendSimple(sendStr);
        } else if (status === 1) {
            var sendStr = "The following commands are available for #b" + levels[selection] + "#k:\r\n\r\n#b";

            if (selection >= 0 && selection <= 6) {
                lvComm = commands.get(selection).getValue0();
                lvDesc = commands.get(selection).getValue1();
                lvOther = commands.get(selection).getValue2();
                lvParams = commands.get(selection).getValue3();
            }

            for (var i = 0; i < lvComm.size(); i++)
                sendStr += "#L" + i + "#" + heading + lvComm.get(i) + "#l\r\n";

            cm.sendSimple(sendStr);
        } else if (status === 2) {
            status = -1;

            var sendStr = "Here are the options for #b" + heading + lvComm.get(selection) + "#k:\r\n\r\n";

            if (lvOther.get(selection).size() > 0) {
                sendStr += "#bOther Usages:#k\r\n";

                for (var i = 0; i < lvOther.get(selection).size(); i++) {
                    sendStr += "#L" + i + "#" + heading + lvOther.get(selection).get(i) + "#l\r\n";
                }

                sendStr += "\r\n\r\n";
            }

            if (lvDesc.get(selection) !== "") {
                sendStr += "#bDescription#k:\r\n";
                sendStr += lvDesc.get(selection);
                sendStr += "\r\n\r\n";
            }

            sendStr += "#bSyntax#k:\r\n";
            sendStr += heading + lvComm.get(selection) + " ";

            var requiredParams = false;
            var paramsText = "";

            for (var i = 0; i < lvParams.get(selection).size(); i++) {
                var colText = "";

                if (String(lvParams.get(selection).get(i).getValue1()) === "true") {
                    colText = "#r";
                    requiredParams = true;
                } else {
                    colText = "#k";
                }

                paramsText += colText + "[" + lvParams.get(selection).get(i).getValue0() + "]#k ";
            }

            sendStr += paramsText + "\r\n";

            if (lvOther.get(selection).size() > 0) {
                for (var i = 0; i < lvOther.get(selection).size(); i++) {
                    sendStr += heading + lvOther.get(selection).get(i) + " " + paramsText + "\r\n";
                }
            }

            for (var i = 0; i < lvParams.get(selection).size(); i++) {
                if (lvParams.get(selection).get(i).getValue2().size() > 0) {
                    var colText = "";

                    if (String(lvParams.get(selection).get(i).getValue1()) === "true") {
                        colText = "#r";
                    } else {
                        colText = "#k";
                    }

                    sendStr += "\r\n" + colText + "[" + lvParams.get(selection).get(i).getValue0() + "]#k:\r\n";

                    for (var f = 0; f < lvParams.get(selection).get(i).getValue2().size(); f++) {
                        sendStr += "#L" + f + "#" + lvParams.get(selection).get(i).getValue2().get(f) + "#l\r\n";
                    }

                    sendStr += "\r\n";
                }

                if (lvParams.get(selection).get(i).getValue3() !== "") {
                    sendStr += "\r\n";
                    sendStr += lvParams.get(selection).get(i).getValue3();
                }
            }

            if (requiredParams) {
                sendStr += "\r\n";
                sendStr += "#rRed parameters are required!#k";
            }

            cm.sendNext(sendStr);
        } else {
            cm.dispose();
        }
    }
}