package seb;
/**
 * Represents the priority level of a task.
 */
public enum PriorityType {
    LOW,
    MEDIUM,
    HIGH,
    UNSPECIFIEDP;
    /**
     * Converts an integer value to its corresponding PriorityType.
     * @param value The integer value representing the priority level.
     * @return The corresponding PriorityType.
     */
    public static PriorityType fromInt(int value) {
        return switch (value) {
        case 1 -> LOW;
        case 2 -> MEDIUM;
        case 3 -> HIGH;
        default -> UNSPECIFIEDP;
        };
    }
    /**
     * Converts a PriorityType to its corresponding integer value.
     * @param priority The PriorityType to convert.
     * @return The integer value representing the priority level.
     */
    public static int toInt(PriorityType priority) {
        return switch (priority) {
        case LOW -> 1;
        case MEDIUM -> 2;
        case HIGH -> 3;
        case UNSPECIFIEDP -> 0;
        };
    }
    @Override
    public String toString() {
        return switch (this) {
        case HIGH -> "HIGH";
        case MEDIUM -> "MEDIUM";
        case LOW -> "LOW";
        case UNSPECIFIEDP -> "UNSPECIFIED";
        };
    }
}
