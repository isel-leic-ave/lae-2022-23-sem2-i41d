package pt.isel;

public abstract class AbstractLogger {
    protected final Printer out;

    protected AbstractLogger(Printer out) {
        this.out = out;
    }

    public abstract void log(Object target);
}
