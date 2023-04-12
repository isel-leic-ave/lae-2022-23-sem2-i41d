public class ScratchImpl {
    private static ScratchImpl inst = null;
    private ScratchImpl() {
        // super(); // implicit
    }
    private void run() {
    }
    public static void main(String[] args) {
            inst = new ScratchImpl();
            inst.run();
    }
    public int sum(int a, int b, int c, int d, int e) {
        return a + b + c + d + e;
    } 
}
