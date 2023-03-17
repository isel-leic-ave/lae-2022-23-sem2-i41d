package pt.isel;

public interface Printer {
    void print(Object msg);

    default void println(Object msg) {
        print(msg);
        print(System.lineSeparator());
    }
}
