package fr.shyrogan.wonderfulchat.channel.implementations;

import fr.shyrogan.wonderfulchat.channel.IChannel;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * SimpleChannel is an implementation of {@code IChannel}.
 *
 * It represents a Channel with Listeners stored by our WonderfulChat.
 *
 * I recommend you using this class if you do not have another way to store
 * if the player is listening to a channel.
 *
 * @author Sébastien (Shyrogan)
 */
public final class SimpleChannel implements IChannel {

    private final String name;
    private String prefix;
    private final List<Player> listeners = new LinkedList<>();

    public SimpleChannel(String name, String prefix) {
        this.name = name;
        this.prefix = prefix;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPrefix() {
        return prefix;
    }

    @Override
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public Collection<Player> getListeners() {
        return listeners;
    }

    @Override
    public void addListener(Player p) {
        listeners.add(p);
    }

    @Override
    public void send(BaseComponent text) {
        final ComponentBuilder builder = new ComponentBuilder(prefix);
        builder.append(text);
        listeners.forEach(player -> player.spigot().sendMessage(builder.create()));
    }

}
