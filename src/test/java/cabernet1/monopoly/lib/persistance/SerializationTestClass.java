package cabernet1.monopoly.lib.persistance;

import java.io.Serializable;

public class SerializationTestClass implements Serializable {
    private static final long serialVersionUID = -8936553564301179744L;

    private final String myField;

    public SerializationTestClass(String myValue) {
        this.myField = myValue;
    }

    public String getMyField() {
        return myField;
    }
}
