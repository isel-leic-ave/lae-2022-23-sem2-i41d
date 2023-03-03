public final class Student {
    private final int nr; // <=> val
    private String name;  // <=> var

    public Student(int number, String n) {
        nr = number;
        name = n;
    }

    public int getNr() {
        return nr;
    }
    public final String getName() {
        return name;
    }
    public final void setName(String v) {
        name = v;
    }
    public final int getNameLength() {
        return name.length();
    }
    public final void print() {
        System.out.println(String.format(
            "V2 : %d, %s", nr, name));
    }
 }