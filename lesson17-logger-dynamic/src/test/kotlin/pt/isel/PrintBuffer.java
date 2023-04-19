package pt.isel;

public class PrintBuffer implements Printer {
    private final StringBuilder buffer = new StringBuilder();
    @Override
    public void print(Object msg) {
        buffer.append(msg);
    }

    public String buffer() {
        return buffer.toString();
    }
}
