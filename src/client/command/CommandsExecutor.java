package client.command;

import client.MapleClient;
import client.command.commands.gm0.*;
import client.command.commands.gm1.*;
import client.command.commands.gm2.*;
import client.command.commands.gm3.*;
import client.command.commands.gm4.*;
import client.command.commands.gm5.*;
import client.command.commands.gm6.*;
import constants.ServerConstants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.javatuples.Quartet;
import tools.FilePrinter;

public class CommandsExecutor {

    public static CommandsExecutor instance = new CommandsExecutor();

    public static CommandsExecutor getInstance() {
        return instance;
    }

    private static final char USER_HEADING = '@';
    private static final char GM_HEADING = '!';

    public static boolean isCommand(MapleClient client, String content) {
        char heading = content.charAt(0);

        if (client.getPlayer().isGM()) {
            return heading == GM_HEADING;
        }

        return heading == USER_HEADING;
    }

    private HashMap<String, RegisteredCommand> registeredCommands = new HashMap<>();
    private Quartet<List<String>, List<String>, List<List<String>>, List<List<Quartet<String, Boolean, List<String>, String>>>> levelCommandsCursor;
    private List<Quartet<List<String>, List<String>, List<List<String>>, List<List<Quartet<String, Boolean, List<String>, String>>>>> commandsNameDesc = new ArrayList<>();
    private String commandHeading;

    private CommandsExecutor() {
        registerLv0Commands();
        registerLv1Commands();
        registerLv2Commands();
        registerLv3Commands();
        registerLv4Commands();
        registerLv5Commands();
        registerLv6Commands();
    }

    public String getHeading() {
        return commandHeading;
    }

    public List<Quartet<List<String>, List<String>, List<List<String>>, List<List<Quartet<String, Boolean, List<String>, String>>>>> getGmCommands() {
        return commandsNameDesc;
    }

    public void handle(MapleClient client, String message) {
        if (client.tryacquireClient()) {
            try {
                handleInternal(client, message);
            } finally {
                client.releaseClient();
            }
        } else {
            client.getPlayer().dropMessage(5, "Try again in a while... Latest commands are currently being processed.");
        }
    }

    private void handleInternal(MapleClient client, String message) {
        final char heading = message.charAt(0);
        final String splitRegex = "[ ]";
        String[] splitedMessage = message.substring(1).split(splitRegex, 2);

        if (splitedMessage.length < 2) {
            splitedMessage = new String[]{splitedMessage[0], ""};
        }

        client.getPlayer().setLastCommandMessage(splitedMessage[1]); // Thanks Tochi & Nulliphite for noticing string messages being marshalled lowercase
        final String commandName = splitedMessage[0].toLowerCase();
        final String[] lowercaseParams = splitedMessage[1].toLowerCase().split(splitRegex);

        final RegisteredCommand command = registeredCommands.get(commandName);

        if (command == null) {
            client.getPlayer().yellowMessage("Command '" + commandName + "' is not available. See " + heading + "commands for a list of available commands.");
            return;
        }

        if (client.getPlayer().gmLevel() < command.getRank()) {
            client.getPlayer().yellowMessage("You not have permission to use this command.");
            return;
        }

        String[] params;

        if (lowercaseParams.length > 0) {
            params = Arrays.copyOfRange(lowercaseParams, 0, lowercaseParams.length);
        } else {
            params = new String[]{};
        }

        try {
            commandHeading = String.valueOf(heading);

            Command commandInstance = command.getCommandClass().newInstance();
            commandInstance.execute(client, params);
            writeLog(client, message);
        } catch (InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }

    private void writeLog(MapleClient client, String command) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        FilePrinter.print(FilePrinter.USED_COMMANDS, client.getPlayer().getName() + " used: " + command + " on " + sdf.format(Calendar.getInstance().getTime()));
    }

    private void addCommand(int rank, Class<? extends Command> commandClass) {
        try {
            Command command = commandClass.newInstance();
            String commandName = command.getName().toLowerCase();
            List<String> otherNames = command.getOtherNames();

            if (registerCommand(commandName, rank, commandClass)) {
                levelCommandsCursor.getValue0().add(commandName);
                levelCommandsCursor.getValue1().add(command.getDescription());
                levelCommandsCursor.getValue2().add(otherNames);
                levelCommandsCursor.getValue3().add(command.getParameters());

                if (otherNames.size() > 0) {
                    for (String otherName : otherNames) {
                        registerCommand(otherName, rank, commandClass);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private boolean registerCommand(String name, int rank, Class<? extends Command> commandClass) {
        RegisteredCommand registeredCommand = new RegisteredCommand(commandClass, rank);
        String commandName = name.toLowerCase();

        if (registeredCommands.containsKey(commandName)) {
            System.out.println("Error on register command with name: " + commandName + ". Already exists.");
            return false;
        } else {
            registeredCommands.put(commandName, registeredCommand);
            return true;
        }
    }

    private void registerLv0Commands() {
        levelCommandsCursor = new Quartet<>(
                (List<String>) new ArrayList<String>(),
                (List<String>) new ArrayList<String>(),
                (List<List<String>>) new ArrayList<List<String>>(),
                (List<List<Quartet<String, Boolean, List<String>, String>>>) new ArrayList<List<Quartet<String, Boolean, List<String>, String>>>()
        );

        addCommand(0, HelpCommand.class);
        addCommand(0, DropLimitCommand.class);

        if (ServerConstants.USE_BUYBACK_SYSTEM) {
            addCommand(0, BuyBackCommand.class);
        }

        addCommand(0, TimeCommand.class);
        addCommand(0, UptimeCommand.class);
        addCommand(0, GachaCommand.class);
        addCommand(0, DisposeCommand.class);
        addCommand(0, EquipLvCommand.class);
        addCommand(0, ShowRatesCommand.class);
        addCommand(0, RatesCommand.class);
        addCommand(0, OnlineCommand.class);
        addCommand(0, GmCommand.class);
        addCommand(0, ReportBugCommand.class);
        addCommand(0, JoinEventCommand.class);
        addCommand(0, LeaveEventCommand.class);
        addCommand(0, RanksCommand.class);
        addCommand(0, StatStrCommand.class);
        addCommand(0, StatDexCommand.class);
        addCommand(0, StatIntCommand.class);
        addCommand(0, StatLukCommand.class);

        addCommand(0, ReadPointsCommand.class);
        addCommand(0, EnableAuthCommand.class);
        addCommand(0, ToggleExpCommand.class);
        addCommand(0, MapOwnerClaimCommand.class);

        commandsNameDesc.add(levelCommandsCursor);
    }

    private void registerLv1Commands() {
        levelCommandsCursor = new Quartet<>(
                (List<String>) new ArrayList<String>(),
                (List<String>) new ArrayList<String>(),
                (List<List<String>>) new ArrayList<List<String>>(),
                (List<List<Quartet<String, Boolean, List<String>, String>>>) new ArrayList<List<Quartet<String, Boolean, List<String>, String>>>()
        );

        addCommand(1, BossHpCommand.class);
        addCommand(1, MobHpCommand.class);
        addCommand(1, WhatDropsFromCommand.class);
        addCommand(1, WhoDropsCommand.class);
        addCommand(1, BuffMeCommand.class);
        addCommand(1, GotoCommand.class);

        commandsNameDesc.add(levelCommandsCursor);
    }

    private void registerLv2Commands() {
        levelCommandsCursor = new Quartet<>(
                (List<String>) new ArrayList<String>(),
                (List<String>) new ArrayList<String>(),
                (List<List<String>>) new ArrayList<List<String>>(),
                (List<List<Quartet<String, Boolean, List<String>, String>>>) new ArrayList<List<Quartet<String, Boolean, List<String>, String>>>()
        );

        addCommand(2, RechargeCommand.class);
        addCommand(2, WhereAmICommand.class);
        addCommand(2, HideCommand.class);
        addCommand(2, UnHideCommand.class);
        addCommand(2, SpCommand.class);
        addCommand(2, ApCommand.class);
        addCommand(2, EmpowerMeCommand.class);
        addCommand(2, BuffMapCommand.class);
        addCommand(2, BuffCommand.class);
        addCommand(2, BombCommand.class);
        addCommand(2, DcCommand.class);
        addCommand(2, ClearDropsCommand.class);
        addCommand(2, ClearSlotCommand.class);
        addCommand(2, WarpCommand.class);
        addCommand(2, WarpToCommand.class);
        addCommand(2, WarpHereCommand.class);
        addCommand(2, GmShopCommand.class);
        addCommand(2, HealCommand.class);
        addCommand(2, ItemCommand.class);
        addCommand(2, ItemDropCommand.class);

        addCommand(2, ClearSavedLocationsCommand.class);
        addCommand(2, LevelCommand.class);
        addCommand(2, LevelProCommand.class);
        addCommand(2, SetStatCommand.class);
        addCommand(2, MaxStatCommand.class);
        addCommand(2, MaxSkillCommand.class);
        addCommand(2, ResetSkillCommand.class);
        addCommand(2, SearchCommand.class);
        addCommand(2, JailCommand.class);
        addCommand(2, UnJailCommand.class);
        addCommand(2, JobCommand.class);
        addCommand(2, UnBugCommand.class);

        commandsNameDesc.add(levelCommandsCursor);
    }

    private void registerLv3Commands() {
        levelCommandsCursor = new Quartet<>(
                (List<String>) new ArrayList<String>(),
                (List<String>) new ArrayList<String>(),
                (List<List<String>>) new ArrayList<List<String>>(),
                (List<List<Quartet<String, Boolean, List<String>, String>>>) new ArrayList<List<Quartet<String, Boolean, List<String>, String>>>()
        );

        addCommand(3, DebuffCommand.class);
        addCommand(3, FlyCommand.class);
        addCommand(3, SpawnCommand.class);
        addCommand(3, MuteMapCommand.class);
        addCommand(3, CheckDmgCommand.class);
        addCommand(3, InMapCommand.class);
        addCommand(3, ReloadEventsCommand.class);
        addCommand(3, ReloadDropsCommand.class);
        addCommand(3, ReloadPortalsCommand.class);
        addCommand(3, ReloadMapCommand.class);
        addCommand(3, ReloadShopsCommand.class);
        addCommand(3, HpMpCommand.class);
        addCommand(3, MaxHpMpCommand.class);
        addCommand(3, MusicCommand.class);
        addCommand(3, MonitorCommand.class);
        addCommand(3, MonitorsCommand.class);
        addCommand(3, IgnoreCommand.class);
        addCommand(3, IgnoredCommand.class);
        addCommand(3, PosCommand.class);
        addCommand(3, ToggleCouponCommand.class);
        addCommand(3, ChatCommand.class);
        addCommand(3, FameCommand.class);
        addCommand(3, GiveNxCommand.class);
        addCommand(3, GiveVpCommand.class);
        addCommand(3, GiveMesosCommand.class);
        addCommand(3, GiveRpCommand.class);
        addCommand(3, IdCommand.class);
        addCommand(3, ExpedsCommand.class);
        addCommand(3, KillCommand.class);
        addCommand(3, SeedCommand.class);
        addCommand(3, MaxEnergyCommand.class);
        addCommand(3, KillAllCommand.class);
        addCommand(3, NoticeCommand.class);
        addCommand(3, RipCommand.class);
        addCommand(3, OpenPortalCommand.class);
        addCommand(3, ClosePortalCommand.class);
        addCommand(3, PeCommand.class);
        addCommand(3, StartEventCommand.class);
        addCommand(3, EndEventCommand.class);
        addCommand(3, StartMapEventCommand.class);
        addCommand(3, StopMapEventCommand.class);
        addCommand(3, OnlineTwoCommand.class);
        addCommand(3, WarpSnowBallCommand.class);
        addCommand(3, BanCommand.class);
        addCommand(3, UnBanCommand.class);
        addCommand(3, HealMapCommand.class);
        addCommand(3, HealPersonCommand.class);
        addCommand(3, HurtCommand.class);
        addCommand(3, KillMapCommand.class);
        addCommand(3, NightCommand.class);
        addCommand(3, NpcCommand.class);
        addCommand(3, FaceCommand.class);
        addCommand(3, HairCommand.class);
        addCommand(3, QuestStartCommand.class);
        addCommand(3, QuestCompleteCommand.class);
        addCommand(3, QuestResetCommand.class);
        addCommand(3, TimerCommand.class);
        addCommand(3, TimerMapCommand.class);
        addCommand(3, TimerAllCommand.class);
        addCommand(3, WarpMapCommand.class);
        addCommand(3, WarpAreaCommand.class);

        commandsNameDesc.add(levelCommandsCursor);
    }

    private void registerLv4Commands() {
        levelCommandsCursor = new Quartet<>(
                (List<String>) new ArrayList<String>(),
                (List<String>) new ArrayList<String>(),
                (List<List<String>>) new ArrayList<List<String>>(),
                (List<List<Quartet<String, Boolean, List<String>, String>>>) new ArrayList<List<Quartet<String, Boolean, List<String>, String>>>()
        );

        addCommand(4, ServerMessageCommand.class);
        addCommand(4, ProItemCommand.class);
        addCommand(4, SetEqStatCommand.class);
        addCommand(4, ExpRateCommand.class);
        addCommand(4, MesoRateCommand.class);
        addCommand(4, DropRateCommand.class);
        addCommand(4, QuestRateCommand.class);
        addCommand(4, TravelRateCommand.class);
        addCommand(4, FishingRateCommand.class);
        addCommand(4, ItemVacCommand.class);
        addCommand(4, ForceVacCommand.class);
        addCommand(4, ZakumCommand.class);
        addCommand(4, HorntailCommand.class);
        addCommand(4, PinkbeanCommand.class);
        addCommand(4, PapCommand.class);
        addCommand(4, PianusCommand.class);
        addCommand(4, CakeCommand.class);
        addCommand(4, PlayerNpcCommand.class);
        addCommand(4, PlayerNpcRemoveCommand.class);
        addCommand(4, PnpcCommand.class);
        addCommand(4, PnpcRemoveCommand.class);
        addCommand(4, PmobCommand.class);
        addCommand(4, PmobRemoveCommand.class);

        commandsNameDesc.add(levelCommandsCursor);
    }

    private void registerLv5Commands() {
        levelCommandsCursor = new Quartet<>(
                (List<String>) new ArrayList<String>(),
                (List<String>) new ArrayList<String>(),
                (List<List<String>>) new ArrayList<List<String>>(),
                (List<List<Quartet<String, Boolean, List<String>, String>>>) new ArrayList<List<Quartet<String, Boolean, List<String>, String>>>()
        );

        addCommand(5, DebugCommand.class);
        addCommand(5, SetCommand.class);
        addCommand(5, ShowPacketsCommand.class);
        addCommand(5, ShowMoveLifeCommand.class);
        addCommand(5, ShowSessionsCommand.class);
        addCommand(5, IpListCommand.class);

        commandsNameDesc.add(levelCommandsCursor);
    }

    private void registerLv6Commands() {
        levelCommandsCursor = new Quartet<>(
                (List<String>) new ArrayList<String>(),
                (List<String>) new ArrayList<String>(),
                (List<List<String>>) new ArrayList<List<String>>(),
                (List<List<Quartet<String, Boolean, List<String>, String>>>) new ArrayList<List<Quartet<String, Boolean, List<String>, String>>>()
        );

        addCommand(6, SetGmLevelCommand.class);
        addCommand(6, WarpWorldCommand.class);
        addCommand(6, SaveAllCommand.class);
        addCommand(6, DCAllCommand.class);
        addCommand(6, MapPlayersCommand.class);
        addCommand(6, GetAccCommand.class);
        addCommand(6, GetCharIdCommand.class);
        addCommand(6, ShutdownCommand.class);
        addCommand(6, ClearQuestCacheCommand.class);
        addCommand(6, ClearQuestCommand.class);
        addCommand(6, SupplyRateCouponCommand.class);
        addCommand(6, SpawnAllPNpcsCommand.class);
        addCommand(6, EraseAllPNpcsCommand.class);
        addCommand(6, ServerAddChannelCommand.class);
        addCommand(6, ServerAddWorldCommand.class);
        addCommand(6, ServerRemoveChannelCommand.class);
        addCommand(6, ServerRemoveWorldCommand.class);

        commandsNameDesc.add(levelCommandsCursor);
    }
}
