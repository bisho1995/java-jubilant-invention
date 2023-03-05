import lombok.NonNull;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class BaseEncryption {
    public static @NonNull byte[] encrypt(@NonNull String algorithm,
                                      @NonNull Key key,
                                 @NonNull String message) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        return cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
    }

    public static @NonNull String decrypt(@NonNull String algorithm,
                                      @NonNull Key key,
                              @NonNull String messageB64Enc) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        byte[] bytes = Base64Utils.base64Decode(messageB64Enc);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key);

        return new String(cipher.doFinal(bytes));
    }
}
