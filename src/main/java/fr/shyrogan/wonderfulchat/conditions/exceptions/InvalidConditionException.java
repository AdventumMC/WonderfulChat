package fr.shyrogan.wonderfulchat.conditions.exceptions;

/**
 * Exception thrown when a Condition is incomplete/invalid.
 *
 * @author Sébastien (Shyrogan)
 */
public final class InvalidConditionException extends RuntimeException {

    public InvalidConditionException(String text) {
        super(text);
    }

}
