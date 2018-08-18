package fr.shyrogan.wonderfulchat.channel.implementations;

import fr.shyrogan.wonderfulchat.channel.IChannel;
import fr.shyrogan.wonderfulchat.conditions.serialization.SerializedCondition;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Material;
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

    private final String name, description;
    private String prefix, marker;
    private final List<Player> listeners = new LinkedList<>();
    private final List<SerializedCondition> conditions = new LinkedList<>();
    private final Material logo;

    public SimpleChannel(String name, String description, String prefix, String marker, Material logo) {
        this.name = name;
        this.description = description;
        this.prefix = prefix;
        this.marker = marker;
        this.logo = logo;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
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
    public String getMarker() {
        return marker;
    }

    @Override
    public Material getLogo() {
        return logo;
    }

    @Override
    public Collection<Player> getListeners() {
        listeners.removeIf(player -> !respectConditions(player));
        return listeners;
    }

    @Override
    public void addListener(Player p) {
        listeners.add(p);
    }

    @Override
    public List<SerializedCondition> getConditions() {
        return conditions;
    }

    @Override
    public void addCondition(SerializedCondition condition) {
        conditions.add(condition);
    }

    @Override
    public void send(BaseComponent text) {
        final ComponentBuilder builder = new ComponentBuilder(prefix);
        builder.append(text);

        getListeners().forEach(player -> player.spigot().sendMessage(builder.create()));
    }

}
