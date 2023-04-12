public class ScratchImpl {
    private static ScratchImpl inst = null;
    private ScratchImpl() {
        }
        private void run() {
    }
        public static void main(String[] args) {
            inst = new ScratchImpl();
            inst.run();
    }
}
