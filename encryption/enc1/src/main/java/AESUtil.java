import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class AESUtil {
    private static final String CIPHER_ALGORITHM = "AES";
    private static String secretKey;

    static {
        try {
            secretKey = getRandomKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String getRandomKey() throws NoSuchAlgorithmException {
        return Base64Utils.base64Encode(KeyGenerator.getInstance(CIPHER_ALGORITHM).generateKey().getEncoded());
    }

    public static SecretKey getKey(String keyB64) {
        byte[] keyBytes = Base64Utils.base64Decode(keyB64);

        return new SecretKeySpec(keyBytes, 0, keyBytes.length, CIPHER_ALGORITHM);
    }

    public static @NonNull byte[] encrypt(String message) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return BaseEncryption.encrypt(CIPHER_ALGORITHM, getKey(secretKey),
                message);
    }

    public static @NonNull String decrypt(String messageB64) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return BaseEncryption.decrypt(CIPHER_ALGORITHM, getKey(secretKey),
                messageB64);
    }

    public static void main(String[] args) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        log.info("key = {}", secretKey);
        final String message = "some secret message";

        String encodedEncryptedMessage = Base64Utils.base64Encode(encrypt(message));
        log.info("encoded encrypted message = {}", encodedEncryptedMessage);

        String decryptedMessage = decrypt(encodedEncryptedMessage);
        log.info("decrypted message = {}", decryptedMessage);
    }
}
