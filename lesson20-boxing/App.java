public class App {
    public static void main(String[] args) {
        
    }
    void foo(int n) {
        Object o = n; // boxing
        System.out.println(o);
        Integer i = n; // boxing
        System.out.println(i);
        int x = i;     // unboxing
        System.out.println(x);
    }
}