package fr.shyrogan.wonderfulchat.conditions;

import fr.shyrogan.wonderfulchat.conditions.filter.ConditionFilter;

/**
 * Represents a condition inside of our configuration file.
 * It allows us to convert a String into an object too using.
 *
 * It does a cool text form like {@code tag=value} for example:
 * {@code condition: "world=world; permission=admin.chat"}
 *
 * @author Sébastien (Shyrogan)
 */
public class Condition {

    private final String tag;
    private final ConditionFilter filter;

    /**
     * Creates a Condition. I recommend going through
     *
     * @param tag Condition's name.
     */
    protected Condition(String tag, ConditionFilter filter) {
        this.tag = tag;
        this.filter = filter;
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
     * Returns our channel filter to know if a Player is eligible to receive
     * messages.
     *
     * @return Predicate
     */
    public ConditionFilter getFilter() {
        return filter;
    }
}
