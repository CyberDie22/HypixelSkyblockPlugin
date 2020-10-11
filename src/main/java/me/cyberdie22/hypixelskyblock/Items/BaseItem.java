package me.cyberdie22.hypixelskyblock.Items;

import me.cyberdie22.hypixelskyblock.Ability;
import me.cyberdie22.hypixelskyblock.Color;
import me.cyberdie22.hypixelskyblock.Rarity;
import me.cyberdie22.hypixelskyblock.Utilities.Utilities;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class BaseItem implements Listener{

    private ItemStack item;
    private Rarity rarity;
    private int id;
    private String name;
    private Material material;
    private boolean canBreakBlocks;
    private boolean stackable;
    private boolean oneTimeUse;
    private boolean hasActiveEffect;
    private boolean reforgable;
    private List<Ability> abilities;
    private List<String> lore = new ArrayList<>();
    private List<List<String>> abilitiesLore = new ArrayList<>();
    private List<List<String>> lastAbilitiesLore = new ArrayList<>();
    private List<List<String>> firstAbilitiesLore = new ArrayList<>();
    private Random rand = new Random();

    public BaseItem(int id1, Rarity rarity1, String name1, Material material1, boolean reforgable1, boolean canBreakBlocks1, boolean stackable1, boolean oneTimeUse1, boolean hasActiveEffect1, List<Ability> abilities1) {
        this.rarity = rarity1;
        this.id = id1;
        this.name = name1;
        this.material = material1;
        this.canBreakBlocks = canBreakBlocks1;
        this.stackable = stackable1;
        this.oneTimeUse = oneTimeUse1;
        this.hasActiveEffect = hasActiveEffect1;
        this.abilities = abilities1;
        this.reforgable = reforgable1;
        this.item = new ItemStack(this.material);
        setItem();
    }

    public int getID() {
        return this.id;
    }

    public void setItem() {
        ItemMeta meta = item.getItemMeta();
        if (!this.stackable) {
            Utilities.storeIntInItem(this.item, "stack-prevention", rand.nextInt(Integer.MAX_VALUE));
        }


        // if unfinished add warning
        if (this.rarity.equals(Rarity.UNFINISHED)) {
            this.lore.add(ChatColor.DARK_RED + "This item is unfinished");
            this.lore.add(ChatColor.DARK_RED + "This item may not work as expected");
            this.lore.add(" ");
        }

        // get and sort abiltiies
        for (Ability i : this.abilities) {
            if (i.prioritized) {
                this.firstAbilitiesLore.add(i.getLore());
            } else if (i.last) {
                this.lastAbilitiesLore.add(i.getLore());
            } else {
                this.abilitiesLore.add(i.getLore());
            }
        }

        // add abilities in order
        for (List<String> i : this.firstAbilitiesLore) {
            this.lore.addAll(i);
            this.lore.add(" ");
        }
        for (List<String> i : this.abilitiesLore) {
            this.lore.addAll(i);
            this.lore.add(" ");
        }
        for (List<String> i : this.lastAbilitiesLore) {
            this.lore.addAll(i);
            this.lore.add(" ");
        }

        // add ending text
        if (oneTimeUse) {
            this.lore.add(ChatColor.DARK_GRAY + "This item is one use!");
        }
        if (reforgable) {
            this.lore.add(ChatColor.DARK_GRAY + "This item is reforgable!");
        }
        switch (this.rarity) {
            case COMMON:
                this.lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "COMMON");
                meta.setDisplayName(ChatColor.WHITE + this.name);
                break;
            case UNCOMMON:
                this.lore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "UNCOMMON");
                meta.setDisplayName(ChatColor.GREEN + this.name);
                break;
            case RARE:
                this.lore.add(ChatColor.BLUE + "" + ChatColor.BOLD + "RARE");
                meta.setDisplayName(ChatColor.BLUE + this.name);
                break;
            case EPIC:
                this.lore.add(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "EPIC");
                meta.setDisplayName(ChatColor.DARK_PURPLE + this.name);
                break;
            case LEGENDARY:
                this.lore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "LEGENDARY");
                meta.setDisplayName(ChatColor.GOLD + this.name);
                break;
            case MYTHIC:
                this.lore.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "MYTHIC");
                meta.setDisplayName(ChatColor.LIGHT_PURPLE + this.name);
                break;
            case SPECIAL:
                this.lore.add(ChatColor.RED + "" + ChatColor.BOLD + "SPECIAL");
                meta.setDisplayName(ChatColor.RED + this.name);
                break;
            case VERY_SPECIAL:
                this.lore.add(ChatColor.RED + "" + ChatColor.BOLD + "VERY SPECIAL");
                meta.setDisplayName(ChatColor.RED + this.name);
                break;
            case UNFINISHED:
                this.lore.add(ChatColor.DARK_RED + "" + ChatColor.BOLD + "UNFINISHED");
                meta.setDisplayName(ChatColor.DARK_RED + this.name);
                break;
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
    }

    public ItemStack getItem() {
        return this.item;
    }

    public void setLorePrefix(List<String> lore, ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta.hasLore()) {
            lore.addAll(meta.getLore());
        }
        meta.setLore(lore);
    }

    public void setLoreSuffix(List<String> lore, ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta.hasLore()) {
            List<String> lore1 = new ArrayList<>(); lore1.addAll(meta.getLore()); lore1.addAll(lore); lore = lore1;
        }
        meta.setLore(lore);
    }

    public void rightClickAirAction(Player player, PlayerInteractEvent event, ItemStack main_item, ItemStack off_item) { }
    public void rightClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack main_item, ItemStack off_item) { }
    public void leftClickAirAction(Player player, PlayerInteractEvent event, ItemStack main_item, ItemStack off_item) { }
    public void leftClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack main_item, ItemStack off_item) { }
    public void shiftRightClickAirAction(Player player, PlayerInteractEvent event, ItemStack main_item, ItemStack off_item) { }
    public void shiftRightClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack main_item, ItemStack off_item) { }
    public void shiftLeftClickAirAction(Player player, PlayerInteractEvent event, ItemStack main_item, ItemStack off_item) { }
    public void shiftLeftClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack main_item, ItemStack off_item) { }
    public void hitEntityAction(Player player, EntityDamageByEntityEvent event, Entity target, ItemStack main_item, ItemStack off_item) { }
    public void shootBowAction(Player player, EntityShootBowEvent event, ItemStack main_item, ItemStack off_item) { }
    public void breakBlockAction(Player player, BlockBreakEvent event, Block block, ItemStack main_item, ItemStack off_item) { }

}
