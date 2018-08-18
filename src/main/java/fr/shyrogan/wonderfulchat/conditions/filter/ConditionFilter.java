package fr.shyrogan.wonderfulchat.conditions.filter;

import org.bukkit.entity.Player;

/**
 * Filters Chatters to know if they can listen to message of a channel.
 *
 * @author Sébastien (Shyrogan)
 */
public interface ConditionFilter {

    /**
     * Returns true if a player can receive messages from a
     * channel.
     *
     * @param listener Player
     * @param conditionParameter Condition
     * @return True if player can listen
     */
    boolean canListen(Player listener, String conditionParameter);

}
