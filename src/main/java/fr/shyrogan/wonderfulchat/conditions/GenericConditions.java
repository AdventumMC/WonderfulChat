package fr.shyrogan.wonderfulchat.conditions;

import fr.shyrogan.wonderfulchat.conditions.parser.ConditionParser;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Sébastien (Shyrogan)
 */
public final class GenericConditions {

    private static final Map<Class, ConditionParser> parsers = new LinkedHashMap<>();

    static {

    }

    public static <T> Condition<T> createCondition(String tag, Class<T> type) {
        
    }

    /**
     * Returns type's parser inside got from Parsers.
     *
     * @param type Type's class
     * @param <T> Type
     * @return ConditionParser for type.
     */
    public <T> ConditionParser<T> getParser(Class<T> type) {
        return parsers.get(type);
    }

    /**
     * Put a parser inside of our parsers.
     *
     * @param type Object type
     * @param parser Parser
     * @param <T> Type
     */
    public <T> void registerParser(Class<T> type, ConditionParser<T> parser) {
        parsers.put(type, parser);
    }

}
