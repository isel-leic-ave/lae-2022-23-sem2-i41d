public class App {
    public static void main(String[] args) {
        var s1 = new Student(1, "Ze Manel");
        var s2 = new Student(2, "Maria");
        System.out.println(Student.count());

        var p1 = new Person(3, "Ze GAto");
        var p2 = new Person(4, "Rosa");
        // ???? como chamar o count() em Java ???
        // System.out.println(Person.count());
    }
}
