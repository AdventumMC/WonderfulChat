package fr.shyrogan.wonderfulchat.chatter;

import fr.shyrogan.wonderfulchat.channel.IChannel;

import java.util.Collection;
import java.util.UUID;

/**
 * IChatter is the representation of a Player for our plugin.
 * It allows us to know which Channel the player is listening to.
 *
 * There are normally 2 IChatter implementations inside of WonderfulChat.
 * <ul>
 *     <li>FutureChatter</li>
 *     <li>SimpleChatter</li>
 * </ul>
 *
 * Both have really specific usages but if you go through the plugin,
 * it is also useful for your own implementations but I recommend doing your owns.
 *
 * However if you go through the AccountProvider, everything should be ok.
 *
 * @author Sébastien (Shyrogan)
 */
public interface IChatter {

    /**
     * Returns our IChatter's UUID. I mean, we need to recognize him lul.
     *
     * @return Chatter's Unique ID.
     */
    UUID getUniqueId();

    /**
     * Returns our Chatter listened channels.
     *
     * @return IChannel Collection
     */
    Collection<IChannel> getListenedChannels();

}
