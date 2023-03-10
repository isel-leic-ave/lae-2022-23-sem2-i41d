public class Person {
    final int nr;
    final String name;

    public Person(int nr, String name) {
        this.nr = nr;
        this.name = name;
    }

    public void print() {
        System.out.println(nr + ": " + name);
    }
}
