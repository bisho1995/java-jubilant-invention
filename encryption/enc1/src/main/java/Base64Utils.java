import java.util.Base64;

public class Base64Utils {
    public static String base64Encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }
    public static byte[] base64Decode(String encodedData) {
        return Base64.getDecoder().decode(encodedData);
    }
}
