package pt.isel;

public interface Formatter {
    /**
     * Receives the value of a property
     * and return a formatted String.
     */
    String format(Object v);
}
