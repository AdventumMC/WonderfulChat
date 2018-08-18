package fr.shyrogan.wonderfulchat.conditions.serialization;

import fr.shyrogan.wonderfulchat.conditions.Condition;
import fr.shyrogan.wonderfulchat.conditions.Conditions;
import fr.shyrogan.wonderfulchat.conditions.exceptions.InvalidConditionException;

/**
 * Wraps a Condition and holds it's parameter.
 * 
 * @author Sébastien (Shyrogan)
 */
public final class SerializedCondition {
    
    private final String conditionTag;
    private final String conditionParameter;

    /**
     * Creates our object based on Condition's tag & it's parameter.
     *
     * @param conditionTag Tag
     * @param conditionParameter Parameter
     */
    private SerializedCondition(String conditionTag, String conditionParameter) {
        this.conditionTag = conditionTag;
        this.conditionParameter = conditionParameter;
    }

    /**
     * Returns Serialized condition tag
     *
     * @return Condition tag.
     */
    public String getConditionTag() {
        return conditionTag;
    }

    /**
     * Returns Serialized condition parameter.
     *
     * @return Condition parameter.
     */
    public String getConditionParameter() {
        return conditionParameter;
    }

    /**
     * Returns our Condition from it's tag.
     *
     * @return Condition
     */
    public Condition getCondition() {
        return Conditions.get(getConditionTag());
    }

    /**
     * Creates a SerializedCondition object from it's serialized text.
     *
     * @param serializedText Serialized text.
     * @return SerializedCondition
     */
    public static SerializedCondition of(String serializedText) {
        String[] keyvalue = serializedText.split("=");
        if(keyvalue.length != 2) {
            throw new InvalidConditionException("Found an invalid condition inside of the configuration: " + serializedText);
        }

        if(!Conditions.contains(keyvalue[0])) {
            throw new InvalidConditionException("Unkown condition: " + serializedText);
        }
        return new SerializedCondition(keyvalue[0], keyvalue[1]);
    }

}
