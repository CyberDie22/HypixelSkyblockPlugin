package me.cyberdie22.hypixelskyblock.Items;

import me.cyberdie22.hypixelskyblock.Ability;
import me.cyberdie22.hypixelskyblock.Rarity;
import me.cyberdie22.hypixelskyblock.Utilities.Utilities;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.List;

public class ASPECT_OF_THE_END extends BaseItem{

    public ASPECT_OF_THE_END(int id1, Rarity rarity1, String name1, Material material1, boolean reforgable1, boolean canBreakBlocks1, boolean stackable1, boolean oneTimeUse1, boolean hasActiveEffect1, List<Ability> abilities1) {
        super(id1, rarity1, name1, material1, reforgable1, canBreakBlocks1, stackable1, oneTimeUse1, hasActiveEffect1, abilities1);
    }

    @Override
    public void rightClickAirAction(Player player, PlayerInteractEvent event, ItemStack main_item, ItemStack off_item) { this.teleport8(player); }
    @Override
    public void rightClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack main_item, ItemStack off_item) { this.teleport8(player); }
    @Override
    public void leftClickAirAction(Player player, PlayerInteractEvent event, ItemStack main_item, ItemStack off_item) { }
    @Override
    public void leftClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack main_item, ItemStack off_item) { }
    @Override
    public void shiftRightClickAirAction(Player player, PlayerInteractEvent event, ItemStack main_item, ItemStack off_item) { }
    @Override
    public void shiftRightClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack main_item, ItemStack off_item) { }
    @Override
    public void shiftLeftClickAirAction(Player player, PlayerInteractEvent event, ItemStack main_item, ItemStack off_item) { }
    @Override
    public void shiftLeftClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack main_item, ItemStack off_item) { }
    @Override
    public void hitEntityAction(Player player, EntityDamageByEntityEvent event, Entity target, ItemStack main_item, ItemStack off_item) { }
    @Override
    public void shootBowAction(Player player, EntityShootBowEvent event, ItemStack main_item, ItemStack off_item) { }

    private void teleport8(Player player) {
        for (int i = 1; i<9; i++) {
            if (Utilities.AOTE_NON_BLOCK.contains(player.getLocation().clone().add(player.getLocation().getDirection().clone().multiply(1)).getBlock().getType()) || Utilities.AOTE_NON_BLOCK.contains(player.getLocation().clone().add(player.getLocation().getDirection().clone().multiply(1).subtract(new Vector(0, 1, 0))).getBlock().getType())) {
                if (!Utilities.LEAVES.contains(player.getLocation().clone().add(player.getLocation().getDirection().clone().multiply(1)).getBlock().getType()) || !Utilities.LEAVES.contains(player.getLocation().clone().add(player.getLocation().getDirection().clone().multiply(1).subtract(new Vector(0, 1, 0))).getBlock().getType())) {
                    player.teleport(player.getLocation().clone().add(player.getLocation().getDirection().clone().multiply(1)));
                }
            } else {
                break;
            }
        }
    }
}



