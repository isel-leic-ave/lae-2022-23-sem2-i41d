package pt.isel;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class LoggerBenchmark {

    Printer emptyPrinter;
    Logger loggerReflect;
    LoggerBaselineStudent baseline;

    static final Student s = new Student(3414, "Ze Manel");

    @Setup
    public void setup(Blackhole bh) {
        emptyPrinter = new Printer() {
            public void print(Object msg) {
                // Do nothing
                bh.consume(msg);
            }
        };
        loggerReflect = new Logger(emptyPrinter, MemberKind.FIELD);
        baseline = new LoggerBaselineStudent(emptyPrinter);
    }

    @Benchmark
    public void benchLoggerReflect() {
        loggerReflect.log(s);
    }

    @Benchmark
    public void benchLoggerBaseline() {
        baseline.log(s);
    }
}
