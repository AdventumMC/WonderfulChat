package fr.shyrogan.wonderfulchat.conditions.parser;

/**
 * Interface tagging a class parsing a String into an object.
 *
 * @author Sébastien (Shyrogan)
 */
public interface ConditionParser<T> {

    /**
     * Converts a String into an object.
     *
     * @param text Text
     * @return Parsed object
     */
    T parse(String text);

}
