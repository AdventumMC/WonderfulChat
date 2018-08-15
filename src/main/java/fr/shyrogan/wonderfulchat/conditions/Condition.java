package fr.shyrogan.wonderfulchat.conditions;

/**
 * Represents a condition inside of our configuration file.
 * It allows us to convert a String into an object too using.
 *
 * It does a cool text form like {@code tag=value} for example:
 * {@code condition: "world=world; permission=admin.chat"}
 *
 * @author Sébastien (Shyrogan)
 */
public class Condition<T> {

    private final String tag;
    private final Class<T> type;

    /**
     * Creates a Condition. I recommend going through
     *
     * @param tag Condition's name.
     * @param type Condition's type.
     */
    private Condition(String tag, Class<T> type) {
        this.tag = tag;
        this.type = type;
    }

    /**
     * Returns the tag to put before the equals inside of our condition.
     *
     * @return Tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * Returns our condition object type.
     * This is used inside of our code, not available from configuration.
     *
     * @return Condition's type
     */
    public Class<T> getType() {
        return type;
    }

}
