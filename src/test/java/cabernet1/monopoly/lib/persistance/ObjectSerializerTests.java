package cabernet1.monopoly.lib.persistance;

import cabernet1.monopoly.TestBase;
import cabernet1.monopoly.lib.persistence.ObjectSerializer;
import org.junit.jupiter.api.Test;

import java.io.Serializable;

import static org.junit.jupiter.api.Assertions.*;

public class ObjectSerializerTests extends TestBase {

    @Test
    public void serializedObjectDeserializeCorrectly() {
        SerializationTestClass testObject = new SerializationTestClass("My super duper important value");
        String serialized = ObjectSerializer.serializeObject(testObject);
        assertNotNull(serialized);
        Serializable convertedObject = ObjectSerializer.deserializeObject(serialized);
        assertNotNull(convertedObject);
        assertTrue(convertedObject instanceof SerializationTestClass);
        SerializationTestClass newObject = (SerializationTestClass) convertedObject;
        assertEquals(testObject.getMyField(), newObject.getMyField());
    }

}
