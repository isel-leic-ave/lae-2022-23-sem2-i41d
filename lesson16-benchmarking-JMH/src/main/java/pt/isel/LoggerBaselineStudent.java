package pt.isel;

public class LoggerBaselineStudent {

    private final Printer printer;

    public LoggerBaselineStudent(Printer printer) {
        this.printer = printer;
    }

    public void log(Object target) {
        Student s = (Student) target;
        printer.print("Student: nr = ");
        printer.print(s.getNr());
        printer.print(", name = ");
        printer.print(s.getName());
        printer.print(", \n");
    }
}
