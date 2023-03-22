package pt.isel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GetterProperty extends AbstractGetter {
    private final Printer out;
    private final Method m;
    private final String name;

    public GetterProperty(Printer out, Method m) {
        super(out, m);
        this.out = out;
        this.m = m;
        AltName altName = m.getAnnotation(AltName.class);
        this.name = altName != null
                ? altName.name()
                : m.getName().substring(3);
        m.setAccessible(true);
    }

    @Override
    public void getAndPrint(Object target) {
        try {
            out.print(name);
            out.print((" = "));
            format(m.invoke(target));
            out.print(", ");
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
