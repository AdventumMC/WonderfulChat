package fr.shyrogan.wonderfulchat.chatter.implementations;

import fr.shyrogan.wonderfulchat.WonderfulChat;
import fr.shyrogan.wonderfulchat.channel.IChannel;
import fr.shyrogan.wonderfulchat.chatter.IChatter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A FutureChatter is the implementation used to load our Chatter.
 * It does contain a Map of each listened channel to load informations.
 *
 * It basically get all saved informations from file and then add the player to each
 * channels he is listening to.
 * Then it converts into SimpleChatter by adding a new SimpleChatter to the cache.
 *
 * @author Sébastien (Shyrogan)
 */
public final class FutureChatter implements IChatter {

    private final UUID uuid;
    private List<String> listenedChannels;

     /**
     * Basically load our Chatter from his UUID.
     *
     * @param uuid Player's Unique ID.
     */
    public FutureChatter(UUID uuid) {
        this.uuid = uuid;

        // Path to our chatter file.
        final Path chatterPath = Paths.get(WonderfulChat.getInstance().getDataFolder().toURI().toString(), "chatter_cache", uuid.toString() + ".json");

        // We basically create a list containing each channels he can listen to.
        if(!Files.exists(chatterPath)) {
            this.listenedChannels = new LinkedList<>();
        } else {

        }
    }

    /**
     * @see IChatter#getUniqueId()
     *
     * @return Player's Unique ID.
     */
    @Override
    public UUID getUniqueId() {
        return uuid;
    }

    /**
     * Returns our Listened Channels based
     * on their saved names.
     *
     * @return Listened channels.
     */
    @Override
    public Collection<IChannel> getListenedChannels() {
        final WonderfulChat wc = WonderfulChat.getInstance();
        return listenedChannels.stream()
                .map(wc::getChannel)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

}
