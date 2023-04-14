package pt.isel;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;

@BenchmarkMode(Mode.Throughput)
public class LoggerBenchmark {

    static final Printer emptyPrinter = new Printer() {
        public void print(Object msg) {
            // Do nothing
        }
    };
    static final Logger logger = new Logger(emptyPrinter, MemberKind.FIELD);
    static final LoggerBaselineStudent baseline = new LoggerBaselineStudent(emptyPrinter);

    static final Student s = new Student(3414, "Ze Manel");

    @Benchmark
    public void benchLoggerReflect() {
        logger.log(s);
    }

    @Benchmark
    public void benchLoggerBaseline() {
        baseline.log(s);
    }
}
