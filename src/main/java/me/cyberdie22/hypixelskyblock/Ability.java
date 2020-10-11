package me.cyberdie22.hypixelskyblock;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings({"FieldCanBeLocal", "FieldMayBeFinal", "ConstantConditions"})
public class Ability {

    private String name;
    private AbilityType type;
    private String description;
    private List<String> lore = new ArrayList<>();
    public boolean prioritized = false;
    public boolean last = false;

    public enum AbilityType {
        PREFIX,
        RIGHT_CLICK,
        LEFT_CLICK,
        FULL_SET,
        PIECE,
        EXTRA,
        NAME,
        PASSIVE,
        SUFFIX
    }
    public Ability(String name1, AbilityType type1, String description1) {
        this.name = name1;
        this.type = type1;
        this.description = description1;

        assert this.lore != null;
        switch (this.type) {
            case RIGHT_CLICK:
                this.lore.add(ChatColor.GOLD + "Item Ability: " + this.name + ChatColor.YELLOW + " " + ChatColor.BOLD + "RIGHT CLICK");
                break;
            case LEFT_CLICK:
                this.lore.add(ChatColor.GOLD + "Item Ability: " + this.name + ChatColor.YELLOW + " " + ChatColor.BOLD + "LEFT CLICK");
                break;
            case FULL_SET:
                this.lore.add(ChatColor.GOLD + "Full Set Bonus: " + this.name);
                break;
            case EXTRA:
                this.lore.add(ChatColor.GOLD + "Piece Bonus: " + this.name);
                break;
            case PIECE:
                this.lore.add(ChatColor.GOLD + "Extra Bonus: " + this.name);
                break;
            case NAME:
                this.lore.add(this.name);
                break;
            case PASSIVE:
                this.lore.add(ChatColor.GOLD + "Item Ability: " + this.name);
                break;
            case PREFIX:
                this.prioritized = true;
                break;
            case SUFFIX:
                this.last = true;
                break;
        }
        this.lore.addAll(Arrays.asList(this.description.split("\n")));
        for (int i = 0; i<this.lore.size(); i++) {
            this.lore.set(i, ChatColor.GRAY + this.lore.get(i));
        }
    }

    public List<String> getLore() {
        return this.lore;
    }
}
