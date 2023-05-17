import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;
import static java.util.Arrays.asList;

public class App {
    public static void main(String[] args) {
        testGenerics();
        // lifeBeforeGenerics();
    }

    /**
     * Generics provide:
     * 1. Expressiveness
     * 2. Type safety
     */
    static void testGenerics(){
        // Expressiveness => the labels variable expresses the type of its elements, i.e. String
        List<String> labels = asList("isel", "super", "ola");
        // Compile Error: incompatible types: int cannot be converted to java.lang.String
        // labels.add(7); // Type Safety
        String first = labels.get(0); // Type Safe
        out.println(first.length());
    }
    static void lifeBeforeGenerics(){
        // NO Expressiveness
        List labels = asList("isel", "super", "ola", 7);
        String first = (String) labels.get(0); // NO type safe
        out.println(first.length());
        String last = (String) labels.get(3); // NO type safe
        out.println(last.length());
    }
}
