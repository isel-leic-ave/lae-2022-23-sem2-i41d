package pt.isel;

import org.openjdk.jmh.infra.Blackhole;

class EmptyPrinter implements Printer {
    private final Blackhole bh;

    public EmptyPrinter(Blackhole bh) {
        this.bh = bh;
    }

    public void print(Object msg) {
        // Do nothing
        bh.consume(msg);
    }

    @Override
    public void print(byte msg) {
        bh.consume(msg);
    }

    @Override
    public void print(short msg) {
        bh.consume(msg);
    }

    @Override
    public void print(int msg) {
        bh.consume(msg);
    }

    @Override
    public void print(long msg) {
        bh.consume(msg);
    }

    @Override
    public void print(float msg) {
        bh.consume(msg);
    }

    @Override
    public void print(double msg) {
        bh.consume(msg);
    }
}
