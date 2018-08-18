package fr.shyrogan.wonderfulchat.channel;

import fr.shyrogan.wonderfulchat.conditions.serialization.SerializedCondition;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Collection;

/**
 * Interface used to represent a channel inside of our chat.
 *
 * A Channel represents a "Category" to our Chat. Meaning you can
 * restrain people receiving messages from it.
 *
 * @author Sébastien (Shyrogan)
 */
public interface IChannel {

    /**
     * Returns this channel's name, it is used
     * to get and recognize our channel from other.
     *
     * @return Channel's name.
     */
    String getName();

    /**
     * Returns this channel's description, it is used
     * to get and recognize our channel from other.
     *
     * @return Channel's name.
     */
    String getDescription();

    /**
     * Returns our channel's prefix. This one is normally
     * at the beginning of each messages but it might not
     * if you create your own implementation of that interface.
     *
     * @return Channel's prefix.
     */
    String getPrefix();

    /**
     * Modifiy our stored prefix. This one is normally
     * at the beginning of each messages but it might not
     * if you create your own implementation of that interface.
     *
     * @param prefix Channel's new prefix.
     */
    void setPrefix(String prefix);

    /**
     * Returns the marker of our Channel.
     * The marker is the text to put at the beginning of our message to use
     * that specific channel.
     *
     * @return Marker
     */
    String getMarker();

    /**
     * Returns a Material used to represent our channel
     * inside of our Channel GUI (/channels)
     *
     * @return Material
     */
    Material getLogo();

    /**
     * Returns every people receiving message from our
     * channel.
     *
     * I highly recommend you to allow modification operations like add/remove.
     *
     * @return Collection of Listeners (Player).
     */
    Collection<Player> getListeners();

    /**
     * Add a player to the Listener collection.
     *
     * @param p Player.
     */
    void addListener(Player p);

    /**
     * Returns conditions to check if player can listen to that
     * channel.
     *
     * @return Condition
     */
    Collection<SerializedCondition> getConditions();

    /**
     * Adds a condition to our Collection of conditions.
     *
     * @param condition Condition
     */
    void addCondition(SerializedCondition condition);

    default boolean respectConditions(Player player) {
        return getConditions().stream().allMatch(serializedCondition -> serializedCondition.getCondition().getFilter().canListen(player, serializedCondition.getConditionParameter()));
    }

    /**
     * Basically turn our String into a BaseComponent then send it
     * using Channel's
     *
     * @param text Text as a String
     */
    default void send(String text) {
        send(new TextComponent(text));
    }

    /**
     * Send a Spigot's chat component de Listeners
     *
     * @param text Text
     */
    void send(BaseComponent text);

}
