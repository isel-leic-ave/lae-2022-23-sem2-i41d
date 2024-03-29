package pt.isel;

public class PrinterConsole implements Printer {

    public static final PrinterConsole INSTANCE = new PrinterConsole();

    private PrinterConsole() {
    }

    @Override
    public void print(Object msg) {
        System.out.print(msg);
    }

    @Override
    public void print(byte msg) {
        System.out.print(msg);
    }

    @Override
    public void print(short msg) {
        System.out.print(msg);
    }

    @Override
    public void print(int msg) {
        System.out.print(msg);
    }

    @Override
    public void print(long msg) {
        System.out.print(msg);
    }

    @Override
    public void print(float msg) {
        System.out.print(msg);
    }

    @Override
    public void print(double msg) {
        System.out.print(msg);
    }
}
