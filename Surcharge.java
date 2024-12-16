/**
 * Enum representing surcharge levels for orders.
 * This enum defines two surcharge levels: MEDIUM and LOW, each with an associated name and numeric value.
 * 
 * @version (version number or date here)
 */
public enum Surcharge {
    /** Medium surcharge with a value of 10. */
    MEDIUM("Medium", 10),

    /** Low surcharge with a value of 4. */
    LOW("Low", 4);

    /** The name representing the surcharge level. */
    private final String name;

    /** The numeric value associated with the surcharge level. */
    private final int value;

    /**
     * Constructor for the Surcharge enum.
     * Initializes the name and value for the surcharge level.
     * 
     * @param name The name of the surcharge level (e.g., "Medium", "Low").
     * @param value The numeric value associated with the surcharge level (e.g., 10, 4).
     */
    Surcharge(String name, int value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Get the name of the surcharge level.
     * 
     * @return The name of the surcharge level (e.g., "Medium", "Low").
     */
    public String getName() {
        return name;
    }

    /**
     * Get the numeric value of the surcharge level.
     * 
     * @return The numeric value of the surcharge level (e.g., 10, 4).
     */
    public int getValor() {
        return value;
    }

    /**
     * Returns a string representation of the surcharge level.
     * This representation includes both the name and the numeric value of the surcharge level.
     * 
     * @return A string in the format "<charge: name(value: value)>".
     */
    @Override
    public String toString() {
        return "<charge: " + getName() + "(value: " + getValor() + ")>";
    }
}

