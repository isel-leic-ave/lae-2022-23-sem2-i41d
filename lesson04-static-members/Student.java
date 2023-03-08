public class Student {
    private static int studentsCount;
    final int nr;
    final String name;

    public static int count() {
        // error: non-static variable this 
        // cannot be referenced from a static context
        // System.out.println(this.nr);
        return studentsCount;
    }

    public Student(int nr, String name) {
        this.nr = nr;
        this.name = name;
        Student.studentsCount++;
    }
}