package Ref7;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] Args) throws InvocationTargetException, IllegalAccessException, InstantiationError, InstantiationException {
        start (Test1.class);
        System.out.println ();
        start (Test2.class);
        System.out.println ();
        start ("Ref7.Test3");
        System.out.println ();
        start ("Ref7.Test4");
        System.out.println ();
    }

    public static void start(Class c) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Method[] methods = c.getDeclaredMethods ();
        int bsCount = 0, asCount = 0;
        List<Method> tests = new ArrayList<> ();

        for (Method o : methods) {
            String type = o.getDeclaredAnnotations ()[0].annotationType ().getSimpleName ();
            if (type.equals ("BeforeSuite")) {
                bsCount++;

                if (bsCount > 1) throw new RuntimeException (" Больше одного метода с аннотацией BeforeSuite");
            } else if (type.equals ("AfterSuite")) {
                asCount++;

                if (asCount > 1) throw new RuntimeException ("Больше одного метода с аннотацией AfterSuite");
            } else if (type.equals ("Test")) {
                tests.add (o);
            }
        }

        tests.sort ((o1, o2) -> o1.getAnnotation (Test.class).value () - o2.getAnnotation (Test.class).value ());


        for (Method o : methods) {
            String type = o.getDeclaredAnnotations ()[0].annotationType ().getSimpleName ();
            if (type.equals ("BeforeSuite")) {
                tests.add (0, o);
            }
            if (type.equals ("AfterSuite")) {
                tests.add (o);
            }
        }

        for (Method i : tests) {
            try {
                System.out.print ("(" + i.getDeclaredAnnotation (Test.class).value () + ") ");
            } catch (NullPointerException e) {

            }
            i.invoke (c.newInstance (), null);


        }
    }

    public static void start(String className) {
        try {
            Class<?> c = Class.forName (className);
            Constructor<?> constructor = c.getConstructor (null);
            start (c);
        } catch (InvocationTargetException | InstantiationError | NoSuchMethodError | IllegalArgumentException | ClassCastException e) {

        } catch (NoSuchMethodException e) {
            e.printStackTrace ();
        } catch (InstantiationException e) {
            e.printStackTrace ();
        } catch (IllegalAccessException e) {
            e.printStackTrace ();
        } catch (ClassNotFoundException e) {
            e.printStackTrace ();
        }
    }

}
