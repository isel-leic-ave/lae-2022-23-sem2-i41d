package pt.isel;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.InvocationTargetException;

public abstract class AbstractGetter implements Getter {
    private final Printer out;
    private final Formatter formatter;

    protected AbstractGetter(Printer out, AnnotatedElement member) {
        this.out = out;
        /**
         * Looking for Format annotation
         */
        final var annot = member.getAnnotation(Format.class);
        try {
            formatter = annot != null
                    ? annot.format().getConstructor().newInstance()
                    : null;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    protected void format(Object propVal) {
        if(formatter == null)
            out.print(propVal);
        else {
            /**
             * REMEMBER DON'T DO THIS!!!!
             */
            // final String msg = (String) f.getClass().getMethod("format", Object.class).invoke(f, propVal);

            final String msg = formatter.format(propVal);
            out.print(msg);
        }
    }
}
