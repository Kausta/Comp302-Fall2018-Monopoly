package cabernet1.monopoly.lib.persistance;

import cabernet1.monopoly.TestBase;
import cabernet1.monopoly.lib.persistence.GameSaverRegistry;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

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
    public void gettingClassInstancesDoesntThrow() {
        GameSaverRegistry instance = GameSaverRegistry.getInstance();
        Set<String> instanceNames = instance.getSaveableClassNames();
        for (String instanceName : instanceNames) {
            assertDoesNotThrow(() -> {
                instance.getClassInstance(instanceName);
            });
        }
        testRepOK(instance);
    }

}
