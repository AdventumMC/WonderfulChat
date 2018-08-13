package fr.shyrogan.wonderfulchat;

import com.google.common.collect.Sets;
import fr.shyrogan.wonderfulchat.channel.IChannel;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;

/**
 * Our plugin's main class. It is used to store and manage our features.
 *
 * This plugin is made to avoid any null objects. Generally using objects
 * like {@code Optional}. If you don't know what this is, I highly recommend
 * you to take a look. It's worth using.
 *
 * @author Sébastien (Shyrogan)
 */
public final class WonderfulChat extends JavaPlugin {

    // Our stored channels.
    private Collection<IChannel> channels;

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
        // First thing first, we set our channels variable.
        this.channels = Sets.newConcurrentHashSet();

    }

    /**
     * Just our worldwide known onEnable Bukkit's method to unload our plugin.
     */
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     * Returns our stored channels.
     *
     * @return Collection of stored channels.
     */
    public Collection<IChannel> getChannels() {
        return channels;
    }

}
