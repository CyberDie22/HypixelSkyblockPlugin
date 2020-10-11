package me.cyberdie22.hypixelskyblock.Items;

import me.cyberdie22.hypixelskyblock.HypixelSkyblock;
import me.cyberdie22.hypixelskyblock.Utilities.Utilities;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ActionTrigger implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void interactEventHandler(PlayerInteractEvent event) {
        switch (event.getAction()) {
            case LEFT_CLICK_AIR:
                if (event.getPlayer().isSneaking()) {
                    for (BaseItem i : HypixelSkyblock.registeredItems) {
                        if (Utilities.getItemInMainHand(event.getPlayer()).equals(i.getItem()) || Utilities.getItemInOffHand(event.getPlayer()).equals(i.getItem())) {
                            i.shiftLeftClickAirAction(event.getPlayer(), event, Utilities.getItemInMainHand(event.getPlayer()), Utilities.getItemInOffHand(event.getPlayer()));
                        }
                    }
                } else {
                    for (BaseItem i : HypixelSkyblock.registeredItems) {
                        if (Utilities.getItemInMainHand(event.getPlayer()).equals(i.getItem()) || Utilities.getItemInOffHand(event.getPlayer()).equals(i.getItem())) {
                            i.leftClickAirAction(event.getPlayer(), event, Utilities.getItemInMainHand(event.getPlayer()), Utilities.getItemInOffHand(event.getPlayer()));
                        }
                    }
                }
                break;
            case LEFT_CLICK_BLOCK:
                if (event.getPlayer().isSneaking()) {
                    for (BaseItem i : HypixelSkyblock.registeredItems) {
                        if (Utilities.getItemInMainHand(event.getPlayer()).equals(i.getItem()) || Utilities.getItemInOffHand(event.getPlayer()).equals(i.getItem())) {
                            i.shiftLeftClickBlockAction(event.getPlayer(), event, event.getClickedBlock(), Utilities.getItemInMainHand(event.getPlayer()), Utilities.getItemInOffHand(event.getPlayer()));
                        }
                    }
                } else {
                    for (BaseItem i : HypixelSkyblock.registeredItems) {
                        if (Utilities.getItemInMainHand(event.getPlayer()).equals(i.getItem()) || Utilities.getItemInOffHand(event.getPlayer()).equals(i.getItem())) {
                            i.leftClickBlockAction(event.getPlayer(), event, event.getClickedBlock(), Utilities.getItemInMainHand(event.getPlayer()), Utilities.getItemInOffHand(event.getPlayer()));
                        }
                    }
                }
                break;
            case RIGHT_CLICK_AIR:
                if (event.getPlayer().isSneaking()) {
                    for (BaseItem i : HypixelSkyblock.registeredItems) {
                        if (Utilities.getItemInMainHand(event.getPlayer()).equals(i.getItem()) || Utilities.getItemInOffHand(event.getPlayer()).equals(i.getItem())) {
                            i.shiftRightClickAirAction(event.getPlayer(), event, Utilities.getItemInMainHand(event.getPlayer()), Utilities.getItemInOffHand(event.getPlayer()));
                        }
                    }
                } else {
                    for (BaseItem i : HypixelSkyblock.registeredItems) {
                        if (Utilities.getItemInMainHand(event.getPlayer()).equals(i.getItem()) || Utilities.getItemInOffHand(event.getPlayer()).equals(i.getItem())) {
                            i.rightClickAirAction(event.getPlayer(), event, Utilities.getItemInMainHand(event.getPlayer()), Utilities.getItemInOffHand(event.getPlayer()));
                        }
                    }
                }
                break;
            case RIGHT_CLICK_BLOCK:
                if (event.getPlayer().isSneaking()) {
                    for (BaseItem i : HypixelSkyblock.registeredItems) {
                        if (Utilities.getItemInMainHand(event.getPlayer()).equals(i.getItem()) || Utilities.getItemInOffHand(event.getPlayer()).equals(i.getItem())) {
                            i.shiftRightClickBlockAction(event.getPlayer(), event, event.getClickedBlock(), Utilities.getItemInMainHand(event.getPlayer()), Utilities.getItemInOffHand(event.getPlayer()));
                        }
                    }
                } else {
                    for (BaseItem i : HypixelSkyblock.registeredItems) {
                        if (Utilities.getItemInMainHand(event.getPlayer()).equals(i.getItem()) || Utilities.getItemInOffHand(event.getPlayer()).equals(i.getItem())) {
                            i.rightClickBlockAction(event.getPlayer(), event, event.getClickedBlock(), Utilities.getItemInMainHand(event.getPlayer()), Utilities.getItemInOffHand(event.getPlayer()));
                        }
                    }
                }
                break;
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void attackEventHandler(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            for (BaseItem i : HypixelSkyblock.registeredItems) {
                if (Utilities.getItemInMainHand((Player) event.getDamager()).equals(i.getItem()) || Utilities.getItemInOffHand((Player) event.getEntity()).equals(i.getItem())) {
                    i.hitEntityAction((Player) event.getDamager(), event, event.getEntity(), Utilities.getItemInMainHand((Player) event.getDamager()), Utilities.getItemInOffHand((Player) event.getDamager()));
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void shootBowEventHandler(EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player) {
            for (BaseItem i : HypixelSkyblock.registeredItems) {
                if (Utilities.getItemInMainHand((Player) event.getEntity()).equals(i.getItem()) || Utilities.getItemInOffHand((Player) event.getEntity()).equals(i.getItem())) {
                    i.shootBowAction((Player) event.getEntity(), event, Utilities.getItemInMainHand((Player) event.getEntity()), Utilities.getItemInOffHand((Player) event.getEntity()));
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void blockBreakEventHandler(BlockBreakEvent event) {
        for (BaseItem i : HypixelSkyblock.registeredItems) {
            if (Utilities.getItemInMainHand(event.getPlayer()).equals(i.getItem()) || Utilities.getItemInOffHand(event.getPlayer()).equals(i.getItem())) {
                i.breakBlockAction(event.getPlayer(), event, event.getBlock(), Utilities.getItemInMainHand(event.getPlayer()), Utilities.getItemInOffHand(event.getPlayer()));
            }
        }
    }
}
