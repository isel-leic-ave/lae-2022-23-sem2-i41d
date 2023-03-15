import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.util.stream.Collectors.toList;

public class ClassesFromUrl {
    public static List<Class<?>> listClasses(URL url) throws IOException {
        try (URLClassLoader loader = new URLClassLoader(new URL[] { url })) {
            try (ZipInputStream zip = new ZipInputStream(url.openStream())) {
                return zipEntryStream(zip)
                        .filter(entry -> !entry.isDirectory() && entry.getName().endsWith(".class"))
                        .map(entry -> loadClass(loader, qualifiedName(entry.getName())))
                        .collect(toList());
            }
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
        return name.replace('/', '.').substring(0, name.length() - ".class".length());
    }

    private static Stream<ZipEntry> zipEntryStream(ZipInputStream zip) {
        return StreamSupport.stream(new ZipEntryIterator(zip).spliterator(), false);
    }

    private static class ZipEntryIterator implements Iterable<ZipEntry>, Iterator<ZipEntry> {
        private final ZipInputStream zip;
        private ZipEntry entry;

        private ZipEntryIterator(ZipInputStream zip) {
            this.zip = zip;
            this.entry = nextEntry();
        }

        private ZipEntry nextEntry() {
            try {
                return zip.getNextEntry();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }

        @Override
        public boolean hasNext() {
            return entry != null;
        }

        @Override
        public ZipEntry next() {
            ZipEntry current = entry;
            entry = nextEntry();
            return current;
        }

        @Override
        public Iterator<ZipEntry> iterator() {
            return this;
        }
    }
}
