package me.cyberdie22.hypixelskyblock.Commands;

import me.cyberdie22.hypixelskyblock.Items.TestItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.cyberdie22.hypixelskyblock.HypixelSkyblock;
import me.cyberdie22.hypixelskyblock.Utilities.MenuUtils;
import me.cyberdie22.hypixelskyblock.Utilities.Utilities;

import java.util.Arrays;

public class MainPluginCommand implements CommandExecutor{

    private HypixelSkyblock main = null;
    public MainPluginCommand(HypixelSkyblock main) { this.main = main; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Bukkit.getConsoleSender().sendMessage("got here 2");

        Player player;
        if (!(sender instanceof Player)) {
            Utilities.warnPlayer(sender, Arrays.asList(main.getPhrase("no-console-message")));
            return true;
        } else {
            player = (Player) sender;
        }

        // verify that the user has proper permissions
        if (!sender.hasPermission("template.user")) {
            Utilities.warnPlayer(sender, Arrays.asList(main.getPhrase("no-permissions-message")));
            return true;
        }

        try {

            switch (args[0].toLowerCase()) {
                case "help":
                    help(sender);
                    break;
                case "info":
                    info(sender);
                    break;
                case "tutorial":
                    MenuUtils.tutorialMenu(player);
                    break;

                // put plugin specific commands here

                case "reload":
                    if (sender.hasPermission("hypixelskyblock.admin")) reload(sender);
                    else Utilities.warnPlayer(sender, Arrays.asList(main.getPhrase("no-permissions-message")));
                    break;
                case "give":
                    if (sender.hasPermission("hypixelskyblock.admin")) {
                        if (HypixelSkyblock.registeredItemNames.contains(args[1])) {
                            player.getInventory().addItem(HypixelSkyblock.items.get(args[1]).getItem());
                            break;
                        }
                    } else {
                        Utilities.warnPlayer(sender, Arrays.asList("E"));
                    }
                    break;
                default:
                    Utilities.warnPlayer(sender, Arrays.asList("e"));
                    help(sender);
                    break;
            }

        } catch(Exception e) {
            e.printStackTrace();
            Utilities.warnPlayer(sender, Arrays.asList("E"));
        }

        return true;
    }

    private void info(CommandSender sender) {
        sender.sendMessage(HypixelSkyblock.prefix + ChatColor.GRAY + "Plugin Info");
        sender.sendMessage(ChatColor.DARK_PURPLE + "- " + ChatColor.GREEN + "Version " + main.getVersion() + " - By CyberDie22");
    }

    private void help(CommandSender sender) {
        sender.sendMessage(HypixelSkyblock.prefix + ChatColor.GRAY + "Commands");
        sender.sendMessage(ChatColor.DARK_PURPLE + "- " + ChatColor.GRAY + "/hypixelskyblock help");
        sender.sendMessage(ChatColor.DARK_PURPLE + "- " + ChatColor.GRAY + "/hypixelskyblock info");
        sender.sendMessage(ChatColor.DARK_PURPLE + "- " + ChatColor.GRAY + "/hypixelskyblock tutorial");
        sender.sendMessage(ChatColor.DARK_PURPLE + "- " + ChatColor.GRAY + "/hypixelskyblock reload");
        sender.sendMessage(ChatColor.DARK_PURPLE + "------------------------------");
    }

    private void reload(CommandSender sender) {
        main.reloadConfig();
        main.loadConfiguration();

        main.loadLangFile();

        Utilities.informPlayer(sender, Arrays.asList("configuration, values, and language settings reloaded"));
    }

}
