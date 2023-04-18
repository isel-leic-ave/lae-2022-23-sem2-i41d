package pt.isel;

import org.cojen.maker.ClassMaker;
import org.cojen.maker.FieldMaker;
import org.cojen.maker.MethodMaker;
import org.cojen.maker.Variable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class App {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException, InstantiationException {
        final var dynamicType = buildDynamicClass();
        // dynamicType.finishTo(new FileOutputStream("DynamicType.class"));
        dynamicType
                .finish()
                .getMethod("run")
                .invoke(null);

        ClassMaker myDynamicTypeMaker = buildMyDynamicType();
        Class<?> myDynamicType = myDynamicTypeMaker.finish();
        Object target = myDynamicType
                .getDeclaredConstructor(int.class)
                .newInstance(9);
        // Call equivalent to: target.mul(3)
        /**
         * Replace the next call by invocation via interface implementation.
         * Make myDynamicType implement an interface with a method mul(int).
         * Then you may cast target to your interface and call mull directly,
         * i.e. target.mul(3)
         */
        final var res = myDynamicType
                .getMethod("mul", int.class)
                .invoke(target, 3);
        System.out.println(res);
    }
    public static ClassMaker buildDynamicClass() {
        ClassMaker cm = ClassMaker.begin().public_();

        // public static void run()...
        MethodMaker mm = cm.addMethod(null, "run").public_().static_();

        // System.out.println(...
        mm.var(System.class).field("out").invoke("println", "hello, world");

        return cm;
    }

    /**
     * Builds dynamically a type equivalent to:
     *
     * class MyDyanmicType {
     *     private final int nr;
     *
     *     MyDyanmicType(int nr) {
     *         this.nr = nr;
     *     }
     *     public int mul(int other) {
     *         return other * nr;
     *     }
     * }
     */
    public static ClassMaker buildMyDynamicType() {
        ClassMaker classMaker = ClassMaker.begin().public_();
        /*
         * Field nr
         */
        FieldMaker nrMaker = classMaker
                .addField(int.class, "nr")
                .private_()
                .final_();
        /**
         * Constructor
         */
        MethodMaker ctorMaker = classMaker
                .addConstructor(int.class)
                .public_();
        ctorMaker
                .invokeSuperConstructor();
        ctorMaker
                .field(nrMaker.name())
                .set(ctorMaker.param(0));
        /**
         * Method mul
         */
        MethodMaker mulMaker = classMaker
                .addMethod(int.class, "mul", int.class)
                .public_();
        Variable res = mulMaker
                .param(0)
                .mul(mulMaker.field(nrMaker.name()));
        mulMaker.return_(res);
        /**
         * finish
         */
        return classMaker;
    }
}
