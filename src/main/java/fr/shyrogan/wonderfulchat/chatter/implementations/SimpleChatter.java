package fr.shyrogan.wonderfulchat.chatter.implementations;

/**
 * SimpleChatter is the implementation of IChatter based on loaded
 * informations.
 * This allows us to not have a Map containing Channels in each Chatter but
 * hold Chatters inside of Channel's map meaning less RAM.
 *
 * It basically allow the manipulation of Chatter through channels and then
 * save into a file when the player disconnects.
 *
 * @author Sébastien (Shyrogan)
 */
public final class SimpleChatter {
}
