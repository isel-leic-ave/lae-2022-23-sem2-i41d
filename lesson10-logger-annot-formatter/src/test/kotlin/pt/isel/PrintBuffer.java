package pt.isel;

public class PrintBuffer implements Printer {
    private final StringBuilder buffer = new StringBuilder();
    @Override
    public void print(Object msg) {
        buffer.append(msg);
    }

    @Override
    public void print(byte msg) {
        buffer.append(msg);
    }

    @Override
    public void print(short msg) {
        buffer.append(msg);
    }

    @Override
    public void print(int msg) {
        buffer.append(msg);
    }

    @Override
    public void print(long msg) {
        buffer.append(msg);
    }

    @Override
    public void print(float msg) {
        buffer.append(msg);
    }

    @Override
    public void print(double msg) {
        buffer.append(msg);
    }

    public String buffer() {
        return buffer.toString();
    }
}
