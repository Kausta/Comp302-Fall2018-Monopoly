package cabernet1.monopoly.lib.persistence;

import java.io.*;
import java.util.Base64;

public class ObjectSerializer {
    public static String serializeObject(Serializable object) {
        ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
        try (ObjectOutputStream outStream = new ObjectOutputStream(byteOutStream)) {
            outStream.writeObject(object);
            outStream.flush();
            byte[] bytes = byteOutStream.toByteArray();
            return convertToBase64(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Serializable deserializeObject(String base64Obj) {
        byte[] bytes = convertFromBase64(base64Obj);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        try (ObjectInputStream inStream = new ObjectInputStream(inputStream)) {
            Object obj = inStream.readObject();
            if (!(obj instanceof Serializable)) {
                return null;
            }
            return (Serializable) obj;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String convertToBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    private static byte[] convertFromBase64(String base64) {
        return Base64.getDecoder().decode(base64);
    }
}
