/**
 * Enum representing the urgency levels of an order.
 * This enum contains three possible urgency levels: EMERGENCY, IMPORTANT, and NONESSENTIAL,
 * each associated with a specific name and a numerical value.
 * 
 * @version (version number or date here)
 */
public enum Urgency {
    /** Emergency orders with the highest urgency, value 5. */
    EMERGENCY("Emergency", 5),

    /** Important orders with medium urgency, value 3. */
    IMPORTANT("Important", 3),

    /** Non-essential orders with the lowest urgency, value 1. */
    NONESSENTIAL("Non essential", 1);

    /** The name representing the urgency level. */
    private final String name;

    /** The numeric value associated with the urgency level. */
    private final int value;

    /**
     * Constructor for the Urgency enum.
     * 
     * @param name The name of the urgency level.
     * @param value The numeric value associated with the urgency level.
     */
    Urgency(String name, int value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Get the name of the urgency level.
     * 
     * @return The name of the urgency level (e.g., "Emergency", "Important", "Non essential").
     */
    public String getName() {
        return name;
    }

    /**
     * Get the numeric value of the urgency level.
     * 
     * @return The numeric value of the urgency level (e.g., 5, 3, 1).
     */
    public int getValor() {
        return value;
    }

    /**
     * Returns a string representation of the urgency level.
     * 
     * @return A string representation of the urgency level, including its name and value.
     */
    @Override
    public String toString() {
        return "<urgency: " + getName() + "(value: " + getValor() + ")>";
    }
}
