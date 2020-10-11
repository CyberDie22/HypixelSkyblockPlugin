package me.cyberdie22.hypixelskyblock;

import me.cyberdie22.hypixelskyblock.Commands.MainPluginCommand;
import me.cyberdie22.hypixelskyblock.Commands.TabComplete;
import me.cyberdie22.hypixelskyblock.Items.ASPECT_OF_THE_END;
import me.cyberdie22.hypixelskyblock.Items.ActionTrigger;
import me.cyberdie22.hypixelskyblock.Items.BaseItem;
import me.cyberdie22.hypixelskyblock.Items.TestItem;
import me.cyberdie22.hypixelskyblock.Utilities.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.*;

@SuppressWarnings({"FieldCanBeLocal", "FieldMayBeFinal", "SameParameterValue"})
public final class HypixelSkyblock extends JavaPlugin {

    // other
    public static List<BaseItem> registeredItems = new ArrayList<>();
    public static List<String> registeredItemNames = new ArrayList<>();
    public static Map<String, BaseItem> items = new HashMap<>();
    public static Map<Integer, String> itemIDs = new HashMap<>();

    // console and IO
    private File langFile;
    private FileConfiguration langFileConfig;

    // chat messages
    private Map<String, String> phrases = new HashMap<String, String>();

    // core settings
    public static String prefix = ChatColor.translateAlternateColorCodes('&', "&c&l[&5&lTemplatePlugin&c&l] &8&l"); // generally unchanged unless otherwise stated in config
    public static String consolePrefix = "[TemplatePlugin] ";

    @Override
    public void onEnable() {
        // Plugin startup logic
        // load config.yml (generate one if not there)
        //loadConfiguration();

        // load language.yml (generate one if not there)
        //loadLangFile();

        // register commands and events
        Bukkit.getConsoleSender().sendMessage("got here 1");
        registerCommands();
        registerEvents();

        // register items
        registerItems();

        // posts confirmation in chat
        getLogger().info(getDescription().getName() + " V: " + getDescription().getVersion() + " has been enabled");

        //
        Utilities.add_tp_blocks();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerItems() {
        putItem("ASPECT_OF_THE_END", new ASPECT_OF_THE_END(0, Rarity.UNFINISHED, "Aspect of the End", Material.DIAMOND_SWORD, true, false, false, false, false, Arrays.asList(new Ability("Instant Transmission", Ability.AbilityType.RIGHT_CLICK, "Teleport " + ChatColor.GREEN + "8 blocks" + ChatColor.GRAY + "ahead of\nyou and gain " + ChatColor.GREEN + "+50" + ChatColor.WHITE + "✦ Speed\nfor " + ChatColor.GREEN + "3 seconds" + ChatColor.GRAY + "."))));
    }

    private void putItem(String name, BaseItem item) {
        registeredItems.add(item);
        registeredItemNames.add(name);
        items.put(name, item);
        itemIDs.put(item.getID(), name);
    }

    private void registerCommands() {
        this.getCommand("hypixelskyblock").setExecutor(new MainPluginCommand(this));

        // set up tab completion
        this.getCommand("hypixelskyblock").setTabCompleter(new TabComplete(this));
    }
    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new ActionTrigger(), this);
    }

    // load the config file and apply settings
    public void loadConfiguration() {
        // prepare config.yml (generate one if not there)
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()){
            Utilities.loadResource(this, "config.yml");
        }
        FileConfiguration config = this.getConfig();

        // general settings
        prefix = ChatColor.translateAlternateColorCodes('&', config.getString("plugin-prefix"));
        // put more settings here

        Bukkit.getLogger().info(consolePrefix + "Settings Reloaded from config");
    }

    // load the language file and apply settings
    public void loadLangFile() {

        // load language.yml (generate one if not there)
        langFile = new File(getDataFolder(), "language.yml");
        langFileConfig = new YamlConfiguration();
        if (!langFile.exists()){ Utilities.loadResource(this, "language.yml"); }

        try { langFileConfig.load(langFile); }
        catch (Exception e3) { e3.printStackTrace(); }

        for(String priceString : langFileConfig.getKeys(false)) {
            phrases.put(priceString, langFileConfig.getString(priceString));
        }
    }

    // getters
    public String getPhrase(String key) {
        return phrases.get(key);
    }
    public String getVersion() {
        return getDescription().getVersion();
    }

}
