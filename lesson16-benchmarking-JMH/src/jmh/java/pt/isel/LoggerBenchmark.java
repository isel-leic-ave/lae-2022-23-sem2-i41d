package pt.isel;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
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
    AbstractLogger logger;
    Student s = new Student(3414, "Ze Manel");

    @Param({"baseline", "reflect", "dynamic"}) String approach;

    @Setup
    public void setup(Blackhole bh) {
        emptyPrinter = new EmptyPrinter(bh);
        logger = switch(approach) {
            case "reflect" -> new Logger(emptyPrinter, MemberKind.FIELD);
            case "baseline" -> new LoggerBaselineStudent(emptyPrinter);
            case "dynamic" -> LoggerDynamic.logger(
                    Student.class,
                    MemberKind.PROPERTY,
                    emptyPrinter);
            default -> throw new UnsupportedOperationException();
        };
    }

    @Benchmark
    public void logger() {
        logger.log(s);
    }

}
