import org.cojen.maker.ClassMaker;
import org.cojen.maker.MethodMaker;
import org.cojen.maker.Variable;
import pt.isel.AbstractLogger;
import pt.isel.Printer;

import java.util.Arrays;
import java.util.Locale;

import static java.util.Arrays.stream;

public class LoggerDynamic {

    /**
     * Build somthing equivalent to
     *
     *
     public class LoggerBaselineStudent extends AbstractLogger {

        protected LoggerBaselineStudent(Printer out) {
          super(out);
        }

        public void log(Object target) {
            Student s = (Student) target;
            out.print("Student: ");
            //
            // Foreach prop generate the next code
            //
            out.print("nr = ");
            out.print(s.getNr());
            out.print(", ");
            //
            // END
            //
            out.println("");
        }
     }
     */
    public static ClassMaker buildLoggerDynamicForProperties(Class<?> domain) {
        ClassMaker clazzMaker = ClassMaker.begin().public_().extend(AbstractLogger.class);
        /**
         * Constructor
         */
        MethodMaker ctor = clazzMaker.addConstructor(Printer.class).public_();
        ctor.invokeSuperConstructor(ctor.param(0));
        /**
         * Method log
         */
        final var outFieldName = "out";
        MethodMaker logMaker = clazzMaker.addMethod(void.class, "log", Object.class).public_().override();
        Variable target = logMaker // <=> Student s = (Student) target;
                .param(0)
                .cast(domain);
        logMaker                   // <=> out.print("Student: ");
                .field(outFieldName)
                .invoke("print", domain.getSimpleName() + ": ");
        /**
         * For each property generate:
         *             out.print("nr = ");
         *             out.print(s.getNr());
         *             out.print(", ");
         */
        stream(domain
                .getDeclaredMethods())
                .filter(m -> m.getParameterCount() == 0 && m.getName().startsWith("get"))
                .forEach(m -> {
                    final var propName = m.getName().substring(3).toLowerCase();
                    logMaker.field(outFieldName).invoke("print", propName + " = "); // out.print("nr = ");
                    final var propValueVar =  target.invoke(m.getName()) ;          // var v = s.getNr();
                    logMaker.field(outFieldName).invoke("print", propValueVar);     // out.print(v);
                    logMaker.field(outFieldName).invoke("print", ", ");             // out.print(", ");
                });
        logMaker.field(outFieldName).invoke("println", ""); // out.println("");
        return clazzMaker;
    }
}
