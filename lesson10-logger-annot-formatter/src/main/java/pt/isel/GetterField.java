package pt.isel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class GetterField extends AbstractGetter {
    private final Printer out;
    private final Field f;
    private final String name;

    public GetterField(Printer out, Field f) {
        super(out, f);
        this.out = out;
        this.f = f;
        f.setAccessible(true);
        /*
         * Looking for AltName annotation
         */
        AltName altName = f.getAnnotation(AltName.class);
        this.name = altName != null ? altName.name() : f.getName();
    }

    @Override
    public void getAndPrint(Object target) {
        try {
            out.print(name);
            out.print((" = "));
            format(f.get(target));
            out.print(", ");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
