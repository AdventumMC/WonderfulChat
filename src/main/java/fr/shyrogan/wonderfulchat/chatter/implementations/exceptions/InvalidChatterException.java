package fr.shyrogan.wonderfulchat.chatter.implementations.exceptions;

import fr.shyrogan.wonderfulchat.chatter.IChatter;

/**
 * InvalidChatterException is thrown when a OnlineChatter is not online
 * or a OfflineChatter is not offline.
 *
 * @author Sébastien (Shyrogan)
 */
public final class InvalidChatterException extends RuntimeException {

    public InvalidChatterException(IChatter chatter) {
        super("Attempted to create an invalid chatter (read GitHub): " + chatter.getClass().getSimpleName());
    }

}
