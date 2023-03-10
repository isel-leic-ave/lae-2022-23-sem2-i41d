import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
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

    public static void main(String[] args) throws IOException {
        listClassesInClasspath()
            .forEach(c -> printMembers(System.out, c));
    }

    public static void printMembers(Appendable out, Class<?> clazz) {
        try {
            out.append(clazz.getName());
            out.append(lineSeparator());
            printFields(out, clazz);
            printMethods(out, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void printFields(Appendable out, Class<?> clazz) throws IOException {
        for (Field f : clazz.getDeclaredFields()) {
            out.append(format("   field: %s %s", f.getType(), f.getName()));
            out.append(lineSeparator());
        }
    }
    public static void printMethods(Appendable out, Class<?> clazz) throws IOException {
        for (Executable m : concat(Executable.class, clazz.getMethods(), clazz.getDeclaredConstructors())) {
            out.append(format("   method: %s(", m.getName()));
            out.append(Arrays
                .stream(m.getParameters())
                .map(p -> p.getType().getSimpleName())
                .collect(Collectors.joining(",")));
            out.append(")");
            out.append(lineSeparator());
        }
    }

    public static Stream<Class<?>>listClassesInClasspath() throws IOException {
        ClassLoader cl = ClassLoader.getSystemClassLoader(); // An AppClassLoader instance
        return list(cl.getResources(""))
                .stream()
                .filter(url -> url.getProtocol().equals("file")) // Exclude jar files
                .map(url -> new File(toURI(url)))
                .flatMap(f -> f.isDirectory()
                        ? Arrays.stream(f.listFiles())
                        : Stream.of(f))
                .filter(f -> f.getName().contains(".class"))
                .map(f -> loadClass(cl, qualifiedName(f.getName())));
    }


    private static URI toURI(URL url) {
        try {
            return url.toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    private static Class<?> loadClass(ClassLoader loader, String name) {
        try {
            return loader.loadClass(name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static String qualifiedName(String name) {
        return name
            .replace('/', '.')
            .substring(0, name.length() - ".class".length());
    }
    @SuppressWarnings("unchecked")
    public static <T> T[] concat(Class<T> eClazz, T[] a, T[] b) {

        return Stream
            .concat(Arrays.stream(a), Arrays.stream(b))
            .toArray(size -> (T[]) Array.newInstance(eClazz, size));
    }
}

