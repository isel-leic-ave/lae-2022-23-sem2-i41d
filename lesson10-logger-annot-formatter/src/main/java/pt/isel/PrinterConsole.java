package pt.isel;

public class PrinterConsole implements Printer {

    public static final PrinterConsole INSTANCE = new PrinterConsole();

    private PrinterConsole() {
    }

    @Override
    public void print(Object msg) {
        System.out.print(msg);
    }
}
