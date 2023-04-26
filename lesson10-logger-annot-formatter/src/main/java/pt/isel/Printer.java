package pt.isel;

public interface Printer {
    void print(Object msg);
    void print(byte msg);
    void print(short msg);
    void print(int msg);
    void print(long msg);
    void print(float msg);
    void print(double msg);

    default void println(byte msg) {
        print(msg);
        print(System.lineSeparator());
    }
    default void println(short msg) {
        print(msg);
        print(System.lineSeparator());
    }
    default void println(int msg) {
        print(msg);
        print(System.lineSeparator());
    }
    default void println(long msg) {
        print(msg);
        print(System.lineSeparator());
    }
    default void println(float msg) {
        print(msg);
        print(System.lineSeparator());
    }
    default void println(double msg) {
        print(msg);
        print(System.lineSeparator());
    }
    default void println(Object msg) {
        print(msg);
        print(System.lineSeparator());
    }
}
