package fr.shyrogan.wonderfulchat;

import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.shyrogan.wonderfulchat.channel.IChannel;
import fr.shyrogan.wonderfulchat.chatter.provider.ChatterProvider;
import fr.shyrogan.wonderfulchat.chatter.provider.implementations.SimpleChatterProvider;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.Optional;

/**
 * Hey before you take a look in source, I would like to say I'm french, meaning I might
 * make incorrect sentences and I'm sorry, I'm doing my best to be clear and comprehensible.
 * Thanks for supporting me by taking a look to my project!
 *
 * Our plugin's main class. It is used to store and manage our features.
 * Keep in mind this plugin is saving informations for a single server using local json files.
 * It means you have to create your own implementations to use it for BungeeCord.
 *
 * This plugin is made to avoid any null objects. Generally using objects
 * like {@code Optional}. If you don't know what this is, I highly recommend
 * you to take a look. It's worth using.
 *
 * @author Sébastien (Shyrogan)
 */
public final class WonderfulChat extends JavaPlugin {

    // An instance to operate on WonderfulChat.
    private static WonderfulChat instance;

    // Our SimpleChatterProvider.
    private ChatterProvider chatterProvider;

    // Our stored channels.
    private Collection<IChannel> channels;

    // A super cool Gson to save data.
    private Gson gson;

    // A variable to know if the plugin is ready to be used.
    // Go to the getter for more informations.
    private boolean ready;

    /**
     * Just our worldwide known onEnable Bukkit's method to load our plugin.
     *
     * Overall this does not include any channel if you disable them through configuration.
     * It basically load our collections and prepare for adding new channels.
     *
     * It also register Listeners.
     */
    @Override
    public void onEnable() {
        // Instance.
        instance = this;

        info("Loading WonderfulChat...");

        // First thing first, we set our channels variable.
        this.channels = Sets.newConcurrentHashSet();

        // Then our Gson, to serialize stuff.
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        // And finally our ChatProvider if asked for in config.
        if(getConfig().getBoolean("default-chatter-provider")) {
            this.setChatterProvider(new SimpleChatterProvider());
        }

        // Looking for channels
        ConfigurationSection section = getConfig().getConfigurationSection("channels");
        if(section == null) {
            getConfig().createSection("channels");
        }
        section.getKeys(false).forEach(channelKey -> {
            ConfigurationSection channelSection = section.getConfigurationSection(channelKey);

            // Well
            if(channelSection == null) {
                return;
            }

            String name = channelSection.getString("name");
            if(name == null) {
                getLogger().warning("Found a Channel without name inside of config.yml (Section " + channelKey + "). Skipping loading.");
            }

            String prefix = channelSection.getString("prefix");
            if(prefix == null) {
                getLogger().warning("Found a Channel without prefix inside of config.yml (Section " + channelKey + "). Skipping loading.");
            }

            String condition = channelSection.getString("name");
            condition = condition == null ? "" : condition;
        });

        // Checking if there are online players.

    }

    /**
     * Just our worldwide known onEnable Bukkit's method to unload our plugin.
     */
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     * Just send an info to the ConsoleSender with colors.
     *
     * @param text Information
     */
    public void info(String text) {
        getServer().getConsoleSender().sendMessage("    §a> (i) §f" + text);
    }

    /**
     * Just send a severe message to the ConsoleSender with colors.
     *
     * @param text Severe
     */
    public void severe(String text) {
        getServer().getConsoleSender().sendMessage("    §a> &c(!SEVERE) §f" + text);
    }

    /**
     * This instance allows operations on each features offered
     * by WonderfulChat.
     *
     * @return WonderfulChat.
     */
    public static WonderfulChat getInstance() {
        return instance;
    }

    /**
     * Returns our SimpleChatterProvider allowing us
     * to manipulate/operate or chat users.
     *
     * @return SimpleChatterProvider.
     */
    public ChatterProvider getChatterProvider() {
        return chatterProvider;
    }

    /**
     * Allow another plugin to use his own ChatterProvider.
     * Only one ChatterProvider can be set. It means if the plugin {@code isReady},
     * you can't set another ChatterProvider.
     *
     * @see this#isReady()
     *
     * @param chatterProvider ChatterProvider
     */
    public void setChatterProvider(ChatterProvider chatterProvider) {
        if(isReady()) {
            severe("Another plugin is attempting to register a ChatterProvider while one is already in use ! (class " + chatterProvider.getClass().getName() + ")");
            return;
        }

        this.chatterProvider = chatterProvider;
        this.ready = true;
    }

    /**
     * If you're a plugin user, checking if the code correspond to your expectations,
     * you can skip that method. It is only used when a developer implements his own ChatterProvider.
     *
     * You're implementing your own ChatterProvider ?!
     * First, turn off "chatter-save" inside of the config, then set your ChatterProvider using method
     * @see this#setChatterProvider(ChatterProvider)
     *
     * You need to know, the plugin will be disabled until ready variable is true.
     * To make it true, you just have to set your own ChatterProvider.
     *
     * @return True if the plugin is ready.
     */
    public boolean isReady() {
        return ready;
    }

    /**
     * Returns our stored channels.
     *
     * @return Collection of stored channels.
     */
    public Collection<IChannel> getChannels() {
        return channels;
    }

    /**
     * Returns a Channel based on his name.
     *
     * @param name Channel's name.
     * @return Channel object.
     */
    public Optional<IChannel> getChannel(String name) {
        return getChannels().stream().filter(channel -> channel.getName().toLowerCase().equalsIgnoreCase(name.toLowerCase())).findFirst();
    }

    /**
     * Returns our Gson to convert Chatters into a json.
     *
     * @return Gson.
     */
    public Gson getGson() {
        return gson;
    }

    /**
     * A method used by WonderfulChat to register listeners.
     *
     * @param listeners Listener
     */
    private void registerListeners(Listener... listeners) {
        for(Listener l : listeners) {
            getServer().getPluginManager().registerEvents(l, this);
        }
    }

}
