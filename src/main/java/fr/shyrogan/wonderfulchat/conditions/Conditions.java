package fr.shyrogan.wonderfulchat.conditions;

import fr.shyrogan.wonderfulchat.conditions.filter.ConditionFilter;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.permissions.Permissible;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Sébastien (Shyrogan)
 */
public final class Conditions {

    private static final List<Condition> conditions = new LinkedList<>();

    static {
        create("world", (listener, conditionParameter) -> {
            final World world = Bukkit.getWorld(conditionParameter);

            return listener.getWorld() == world;
        });

        create("permission", Permissible::hasPermission);
    }

    /**
     * Creates and registers a Condition.
     *
     * @param tag Condition's tag
     * @param filter Condition's filter.
     */
    public static void create(String tag, ConditionFilter filter) {
        Condition condition = new Condition(tag, filter);
        conditions.add(condition);
    }

    /**
     * Checks if a condition with indicated tag exists.
     *
     * @param tag Tag
     * @return True if condition exists.
     */
    public static boolean contains(String tag) {
        final String lowerTag = tag.toLowerCase();
        return conditions.stream().anyMatch(condition -> condition.getTag().toLowerCase().equalsIgnoreCase(lowerTag));
    }

    /**
     * Get our condition from it's tag.
     * If it's not found, returns null.
     *
     * @param tag Tag
     * @return Condition
     */
    public static Condition get(String tag) {
        final String lowerTag = tag.toLowerCase();
        return conditions.stream().filter(condition -> condition.getTag().toLowerCase().equalsIgnoreCase(lowerTag)).findFirst().orElse(null);
    }

}
