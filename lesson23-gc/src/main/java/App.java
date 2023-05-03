import static java.lang.System.out;

public class App {

    static final int SIZE = 100_000;
    public static void main(String[] args) {
        printAllocatedMem();
        System.gc();
        printAllocatedMem();
        out.println("----- Allocating memory ----");
        /**
         * objects is a local variable => root reference
         */
        // Object[] objects = makeGarbage(SIZE);
        makeGarbage(SIZE);
        printAllocatedMem();
        System.gc();
        printAllocatedMem();
        System.gc();
        printAllocatedMem();
    }
    public static void printAllocatedMem() {
        Runtime runtime = Runtime.getRuntime();
        long mem = runtime.totalMemory() - runtime.freeMemory();
        out.println(mem);
    }
    public static Object[] makeGarbage(int size) {
        Object[] objects = new Object[size];
        for (int i = 0; i < size; i++) {
            objects[i] = new Object();
        }
        return objects;
    }
}
