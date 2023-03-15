import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.lang.System.lineSeparator;
import static java.util.Collections.list;

public class App {
    static final String javaPoetPath = "https://repo1.maven.org/maven2/com/squareup/javapoet/1.13.0/javapoet-1.13.0.jar";

    public static void main(String[] args) throws IOException {
        ClassesFromUrl
            .listClasses(new URL(javaPoetPath))
            .forEach(c -> printMembers(System.out, c));
    }

    public static void printMembers(Appendable out, Class<?> clazz) {
        try {
            out.append(clazz.getName());
            out.append(lineSeparator());
            printFields(out, clazz);
            printMethods(out, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void printFields(Appendable out, Class<?> clazz) throws IOException {
        for (Field f : clazz.getDeclaredFields()) {
            out.append(format("   field: %s %s", f.getType(), f.getName()));
            out.append(lineSeparator());
        }
    }
    public static void printMethods(Appendable out, Class<?> clazz) throws Exception {
        for (Executable m : concat(Executable.class, clazz.getMethods(), clazz.getDeclaredConstructors())) {
            out.append(format("   method: %s(", m.getName()));
            out.append(Arrays
                .stream(m.getParameters())
                .map(p -> p.getType().getSimpleName())
                .collect(Collectors.joining(",")));
            out.append(")");
            out.append(lineSeparator());
            if(m instanceof Method 
                && m.getParameterCount() == 0 
                && ((Method) (m)).getReturnType() != void.class)
            {
                // m.setAccessible(true); // Ultrapassa a acessibilidade se nao for public
                printCallParameterlessMethod(out, clazz, (Method) m);
                out.append(lineSeparator());
            }
        }
    }
    public static void printCallParameterlessMethod(Appendable out, Class<?> receiverClass, Method m) throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        if(Modifier.isStatic(m.getModifiers())) {
            out.append(format("   returns: %s", m.invoke(null)).toString());
        }
        else {
            Object o = receiverClass.getConstructor().newInstance();
            out.append(format("   returns: %s", m.invoke(o)).toString());
        }
    }

    
    @SuppressWarnings("unchecked")
    public static <T> T[] concat(Class<T> eClazz, T[] a, T[] b) {

        return Stream
            .concat(Arrays.stream(a), Arrays.stream(b))
            .toArray(size -> (T[]) Array.newInstance(eClazz, size));
    }
}

