import Animals.Dinasour;

import java.lang.reflect.*;

public class CodeAnalyzer {
    public static void analyzeClass(Object o) throws IllegalAccessError, InstantiationError, ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class someClass = o.getClass();
        Constructor constructor = someClass.getDeclaredConstructor();
        Class[] params = {String.class, int.class};
        Dinasour animal = (Dinasour) someClass.getDeclaredConstructor(params).newInstance("Вова", 19);
        /** Hooray, we get some animal object */

        System.out.println(someClass);

        Field age = someClass.getDeclaredField("age");
        age.setAccessible(true);
        age.set(someClass, 198);

    }

    public static void main(String[] args) throws NoSuchFieldException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        analyzeClass(new Dinasour("Арсений", 11));
    }
}
