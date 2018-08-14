package fr.shyrogan.wonderfulchat.chatter.provider.implementations;

import fr.shyrogan.wonderfulchat.WonderfulChat;
import fr.shyrogan.wonderfulchat.chatter.IChatter;
import fr.shyrogan.wonderfulchat.chatter.provider.ChatterProvider;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * WonderfulChat's base implementation of ChatterProvider.
 * @see ChatterProvider
 *
 * @author Sébastien (Shyrogan)
 */
public final class SimpleChatterProvider implements ChatterProvider {

    // Our Chatters Cache.
    private final Map<UUID, IChatter> CHATTER_CACHE = new LinkedHashMap<>();

    /**
     * We're creating cache's folder.
     */
    public SimpleChatterProvider() {
        try {
            Files.createDirectories(Paths.get(WonderfulChat.getInstance().getDataFolder().toURI().toString(), "chatter_cache"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Put a Chatter inside of our cache.
     *
     * @param chatter Chatter.
     */
    @Override
    public void putChatter(IChatter chatter) {
        CHATTER_CACHE.put(chatter.getUniqueId(), chatter);
    }

    /**
     * Returns our Chatter from our cache.
     *
     * @param uuid Chatter's Unique ID.
     * @return Chatter
     */
    @Override
    public IChatter getChatter(UUID uuid) {
        return CHATTER_CACHE.get(uuid);
    }

}
