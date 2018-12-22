package cabernet1.monopoly.lib.persistence;

import java.lang.annotation.*;

/**
 * Create a marker annotation for saveable classes, which we will use to check whether that class is actually saveable
 * <br />
 * Note that a saveable class must be a singleton with a private static variable of its same type and an empty private constructor
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Saveable {
}
