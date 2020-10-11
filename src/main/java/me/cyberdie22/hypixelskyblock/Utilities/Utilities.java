package me.cyberdie22.hypixelskyblock.Utilities;

import com.google.common.io.ByteStreams;
import me.cyberdie22.hypixelskyblock.HypixelSkyblock;
import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

public final class Utilities {

    private static final HypixelSkyblock main = HypixelSkyblock.getPlugin(HypixelSkyblock.class);

    // list of transparent blocks to be ignored when a player looks at a block
    public static final Set<Material> TRANSPARENT = EnumSet.of(Material.AIR, Material.CAVE_AIR, Material.VOID_AIR, Material.BLACK_CARPET, Material.BLUE_CARPET,
            Material.BROWN_CARPET, Material.CYAN_CARPET, Material.GRAY_CARPET, Material.GREEN_CARPET, Material.LIGHT_BLUE_CARPET,
            Material.LIME_CARPET, Material.MAGENTA_CARPET, Material.ORANGE_CARPET, Material.PINK_CARPET, Material.PURPLE_CARPET,
            Material.RED_CARPET, Material.WHITE_CARPET, Material.YELLOW_CARPET, Material.SNOW);

    public static final Set<Material> SINGLE_FLOWER = EnumSet.of(Material.POPPY, Material.DANDELION, Material.BLUE_ORCHID, Material.ALLIUM, Material.AZURE_BLUET, Material.RED_TULIP,
            Material.ORANGE_TULIP, Material.WHITE_TULIP, Material.PINK_TULIP, Material.OXEYE_DAISY, Material.CORNFLOWER, Material.LILY_OF_THE_VALLEY, Material.WITHER_ROSE);

    public static final Set<Material> DOUBLE_FLOWER = EnumSet.of(Material.SUNFLOWER, Material.LILAC, Material.ROSE_BUSH, Material.PEONY);

    public static final Set<Material> LEAVES = EnumSet.of(Material.ACACIA_LEAVES, Material.BIRCH_LEAVES, Material.DARK_OAK_LEAVES, Material.JUNGLE_LEAVES,
            Material.JUNGLE_LEAVES, Material.OAK_LEAVES, Material.SPRUCE_LEAVES);

    public static final Set<Material> GRASS = EnumSet.of(Material.GRASS, Material.TALL_GRASS, Material.SEAGRASS, Material.TALL_SEAGRASS);

    public static Set<Material> AOTE_NON_BLOCK = new HashSet<>();

    // list of all supported inventory blocks in the plugin
    public static final List<Material> INVENTORY_BLOCKS = Arrays.asList(Material.CHEST,Material.TRAPPED_CHEST, Material.ENDER_CHEST, Material.SHULKER_BOX, Material.BLACK_SHULKER_BOX,
            Material.BLUE_SHULKER_BOX, Material.BROWN_SHULKER_BOX, Material.CYAN_SHULKER_BOX, Material.GRAY_SHULKER_BOX,
            Material.GREEN_SHULKER_BOX, Material.LIGHT_BLUE_SHULKER_BOX, Material.LIGHT_GRAY_SHULKER_BOX, Material.LIME_SHULKER_BOX,
            Material.MAGENTA_SHULKER_BOX, Material.ORANGE_SHULKER_BOX, Material.PINK_SHULKER_BOX, Material.PURPLE_SHULKER_BOX,
            Material.RED_SHULKER_BOX, Material.WHITE_SHULKER_BOX, Material.YELLOW_SHULKER_BOX);

    // add blocks to AOTE_NON_BLOCK
    public static void add_tp_blocks() {
        AOTE_NON_BLOCK.addAll(TRANSPARENT);
        AOTE_NON_BLOCK.addAll(SINGLE_FLOWER);
        AOTE_NON_BLOCK.addAll(DOUBLE_FLOWER);
        AOTE_NON_BLOCK.addAll(GRASS);
        AOTE_NON_BLOCK.add(Material.WATER);
        AOTE_NON_BLOCK.add(Material.LAVA);
        AOTE_NON_BLOCK.add(Material.KELP_PLANT);
    }

    // load file from JAR with comments
    public static File loadResource(Plugin plugin, String resource) {
        File folder = plugin.getDataFolder();
        if (!folder.exists())
            folder.mkdir();
        File resourceFile = new File(folder, resource);
        try {
            if (!resourceFile.exists()) {
                resourceFile.createNewFile();
                try (InputStream in = plugin.getResource(resource);
                     OutputStream out = new FileOutputStream(resourceFile)) {
                    ByteStreams.copy(in, out);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resourceFile;
    }

    // convert a location to formatted string (world,x,y,z)
    public static String toLocString(Location location) {
        if (location.equals(null)) return "";
        return location.getWorld().getName() + "," + (int)location.getX() + "," + (int)location.getY() + "," + (int)location.getZ();
    }

    // renames item
    public static ItemStack nameItem(ItemStack item, String name) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }

    // creates item that is renamed given material and name
    public static ItemStack nameItem(Material item, String name) {
        return nameItem(new ItemStack(item), name);
    }

    // set the lore of an item
    public static ItemStack loreItem(ItemStack item, List<String> lore) {
        ItemMeta meta = item.getItemMeta();

        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    // send player a collection of error messages and play error noise
    public static void warnPlayer(CommandSender sender, List<String> messages) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            playSound(ActionSound.ERROR, player);
        }

        for (String message : messages) {
            sender.sendMessage(HypixelSkyblock.prefix + ChatColor.RESET + ChatColor.RED + message);
        }
    }

    // send player a collection of messages
    public static void informPlayer(CommandSender player, List<String> messages) {
        for (String message : messages) {
            player.sendMessage(HypixelSkyblock.prefix + ChatColor.RESET + ChatColor.GRAY + message);
        }
    }

    // return the block the player is looking at, ignoring transparent blocks
    public static Block getBlockLookingAt(Player player) {
        return player.getTargetBlock(TRANSPARENT, 120);
    }

    // play sound at player (version independent)
    public static void playSound(ActionSound sound, Player player) {

        switch (sound) {
            case OPEN:
                player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1f, 1f);
                break;
            case MODIFY:
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1f, 1f);
                break;
            case SELECT:
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
                break;
            case CLICK:
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1f, 1f);
                break;
            case POP:
                player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1f, 1f);
                break;
            case BREAK:
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
                break;
            case ERROR:
                player.playSound(player.getLocation(), Sound.ENTITY_BAT_DEATH, 1f, 1f);
                break;
        }

    }

    // get item in players main hand
    public static ItemStack getItemInMainHand(Player player) {
        return player.getInventory().getItemInMainHand();
    }

    // get item in players off hand
    public static ItemStack getItemInOffHand(Player player) {
        return player.getInventory().getItemInOffHand();
    }

    // store an int in an item
    public static void storeIntInItem(ItemStack item, String name, int stored) {
        ItemMeta meta = item.getItemMeta();
        NamespacedKey key = new NamespacedKey(main, name);
        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, stored);
        item.setItemMeta(meta);
    }

    public static Integer getIntFromItem(ItemStack item, String name) {
        ItemMeta meta = item.getItemMeta();
        NamespacedKey key = new NamespacedKey(main, name);
        if (meta.getPersistentDataContainer().has(key, PersistentDataType.INTEGER)) {
            try {
                return meta.getPersistentDataContainer().get(key, PersistentDataType.INTEGER);
            } catch (NullPointerException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }

    }

}
