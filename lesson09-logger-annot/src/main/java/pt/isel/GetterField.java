package pt.isel;

import java.lang.reflect.Field;

public class GetterField implements Getter {
    private final Printer out;
    private final Field f;
    private final String name;

    public GetterField(Printer out, Field f) {
        this.out = out;
        this.f = f;
        AltName altName = f.getAnnotation(AltName.class);
        this.name = altName != null ? altName.name() : f.getName();
        f.setAccessible(true);
    }

    @Override
    public void getAndPrint(Object target) {
        try {
            out.print(name);
            out.print((" = "));
            out.print(f.get(target));
            out.print(", ");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
