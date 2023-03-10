public class Person {
    final int nr;
    final String name;

    public Person(int nr, String name) {
        this.nr = nr;
        this.name = name;
        Person.Companion.personsCount++;
    }

    public static class Companion {
        private static int personsCount = 0;

        public static int count() {
            return personsCount;
        }
    }
}