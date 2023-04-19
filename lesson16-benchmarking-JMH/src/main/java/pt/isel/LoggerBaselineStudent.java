package pt.isel;

public class LoggerBaselineStudent extends AbstractLogger {

    protected LoggerBaselineStudent(Printer out) {
        super(out);
    }

    public void log(Object target) {
        Student s = (Student) target;
        out.print("Student: ");
        /**
         * Prop nr
         */
        out.print("nr = ");
        out.print(s.getNr());
        out.print(", ");
        /**
         * Prop name
         */
        out.print("name = ");
        out.print(s.getName());
        out.print(", ");
        /**
         * End
         */
        out.println("");
    }
}
