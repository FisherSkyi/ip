package seb;
/**
 * Represents the priority level of a task.
 */
public enum PriorityType {
    LOW,
    MEDIUM,
    HIGH,
    UNSPECIFIEDP;
    
    public static PriorityType fromInt(int value) {
        return switch (value) {
            case 1 -> HIGH;
            case 2 -> MEDIUM;
            case 3 -> LOW;
            default -> UNSPECIFIEDP;
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
