package Expo.internal.accessor;

import java.lang.reflect.Field;











@FunctionalInterface
public interface FieldReader<T> {
   T M(Field var1) throws Throwable;
}
