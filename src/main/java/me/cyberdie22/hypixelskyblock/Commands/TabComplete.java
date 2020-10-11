package me.cyberdie22.hypixelskyblock.Commands;

import me.cyberdie22.hypixelskyblock.HypixelSkyblock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("Annotator")
public class TabComplete implements TabCompleter {

    private HypixelSkyblock main = null;
    public TabComplete(HypixelSkyblock main) { this.main = main; }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        // verify sender is a player
        if (!(sender instanceof Player)) return null;
        Player player = (Player) sender;

        ArrayList<String> arguments = new ArrayList<>();

        // tab completion for /exchange command
        if (command.getName().equals("hypixelskyblock")) {

            // no arguments
            if (args.length == 1){
                if (player.hasPermission("hypixelskyblock.user")) { arguments.addAll(Arrays.asList("help", "info", "tutorial")); }
                if (player.hasPermission("hypixelskyblock.admin")) { arguments.addAll(Arrays.asList("reload", "give")); }

                Iterator<String> iter = arguments.iterator(); while (iter.hasNext()) { String str = iter.next().toLowerCase(); if (!str.contains(args[0].toLowerCase())) iter.remove(); }
            }

            // args is give
            if (args[0].equals("give")) {
                if (player.hasPermission("hypixelskyblock.admin")) { arguments.addAll(HypixelSkyblock.registeredItemNames); }
            }
        }

        return arguments;
    }

}
