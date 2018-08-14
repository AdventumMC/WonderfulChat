package fr.shyrogan.wonderfulchat.chatter.provider;

import fr.shyrogan.wonderfulchat.chatter.IChatter;

import java.util.UUID;

/**
 * ChatterProvider is used by the plugin to manipulate Chatter.
 *
 * You can create your own ChatterProvider from an external plugin and set it through instance
 * or clone our GitHub repository to modify it.
 *
 * @author Sébastien (Shyrogan)
 */
public interface ChatterProvider {

    /**
     * Put our Chatter inside of ChatterProvider's implementation cache.
     *
     * @param chatter Chatter
     */
    void putChatter(IChatter chatter);

    /**
     * Returns our Chatter object, based on hold UUID.
     *
     * @param uuid UUID.
     * @return Chatter
     */
    IChatter getChatter(UUID uuid);

}
