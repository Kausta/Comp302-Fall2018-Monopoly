package cabernet1.monopoly.lib.persistance;

import cabernet1.monopoly.TestBase;
import cabernet1.monopoly.lib.persistence.GameSaverRegistry;
import cabernet1.monopoly.lib.persistence.Saveable;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class GameSaverRegistryTests extends TestBase {

    @Test
    public void createdStateIsNonEmptyAndCorrect() {
        GameSaverRegistry instance = GameSaverRegistry.getInstance();
        HashMap<String, Class<? extends Serializable>> classes = instance.getAllSaveableClasses();
        assertNotNull(classes);
        assertNotEquals(0, classes.size());
        testRepOK(instance);
    }

    @Test
    public void saveableClassesAreMarkedSuchAndAreSingleton() {
        GameSaverRegistry instance = GameSaverRegistry.getInstance();
        HashMap<String, Class<? extends Serializable>> classes = instance.getAllSaveableClasses();
        for (Class<? extends Serializable> clazz : classes.values()) {
            assertNotNull(clazz.getDeclaredAnnotation(Saveable.class));
            assertTrue(isSingleton(clazz));
        }
    }

    private static boolean isSingleton(Class<?> clazz) {
        try {
            if (clazz.getDeclaredField("_instance") == null) {
                return false;
            }
            return clazz.getDeclaredConstructor() != null;
        } catch (NoSuchFieldException | NoSuchMethodException e) {
            return false;
        }
    }

}
