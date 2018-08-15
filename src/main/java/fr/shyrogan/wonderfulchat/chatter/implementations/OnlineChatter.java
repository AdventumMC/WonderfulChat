package fr.shyrogan.wonderfulchat.chatter.implementations;

import fr.shyrogan.wonderfulchat.WonderfulChat;
import fr.shyrogan.wonderfulchat.channel.IChannel;
import fr.shyrogan.wonderfulchat.chatter.IChatter;
import fr.shyrogan.wonderfulchat.chatter.implementations.exceptions.InvalidChatterException;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * OnlineChatter is the implementation of IChatter representing a online Player.
 *
 * This allows us to not have a Map containing Channels in each Chatter but
 * hold Chatters inside of Channel's map meaning less RAM.
 *
 * It basically allow the manipulation of Chatter through channels and then
 * save into a file when the player disconnects.
 *
 * @author Sébastien (Shyrogan)
 */
public final class OnlineChatter implements IChatter {

    private final Player player;

    /**
     * Creates a OnlineChatter with all informations.
     * It adds player to each listened channels and implements IChatter method
     * using them.
     *
     * @param player Player.
     * @param listenedChannels Listened channels.
     */
    public OnlineChatter(Player player, Collection<IChannel> listenedChannels) {
        if(player == null) {
            throw new InvalidChatterException(this);
        }

        this.player = player;

        // Add the Chatter to each Channels.
        listenedChannels.forEach(channel -> channel.addListener(player));
    }

    /**
     * Returns Player's Unique ID.
     *
     * @return Player's Unique ID.
     */
    @Override
    public UUID getUniqueId() {
        return player.getUniqueId();
    }

    /**
     * Loops every Channels to see if the Player listens to them,
     * then collect them.
     *
     * @return Collection of Channels
     */
    @Override
    public Collection<IChannel> getListenedChannels() {
        return WonderfulChat.getInstance().getChannels().stream()
                .filter(channel -> channel.getListeners().contains(player))
                .collect(Collectors.toSet());
    }

}
