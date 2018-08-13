package fr.shyrogan.wonderfulchat.channel.implementations;

import net.md_5.bungee.api.chat.BaseComponent;

import java.io.Serializable;

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
public final class SimpleChannel implements Serializable {

    private final transient String name;
    private final transient BaseComponent prefix;

    public SimpleChannel(String name, BaseComponent prefix) {
        this.name = name;
        this.prefix = prefix;
    }

}
