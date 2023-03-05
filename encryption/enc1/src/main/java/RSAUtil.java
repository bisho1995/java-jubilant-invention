import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Base64;

public class RSAUtil {
    private static final String CIPHER_ALGORITHM = "RSA";
    private static final String SIGNATURE_ALGORITHM = "SHA1WithRSA";

    public static byte[] encrypt(String message) throws NoSuchPaddingException, NoSuchAlgorithmException, FileNotFoundException, CertificateException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        return BaseEncryption.encrypt(CIPHER_ALGORITHM,
                KeyReader.getPublicKey(), message);
    }
    public static String decrypt(String encryptedMessageB64) throws NoSuchPaddingException, NoSuchAlgorithmException, UnrecoverableKeyException, CertificateException, KeyStoreException, IOException, NoSuchProviderException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        return BaseEncryption.decrypt(CIPHER_ALGORITHM,
                KeyReader.getPrivateKey(), encryptedMessageB64);
    }

    public static byte[] getSignature(String content) throws NoSuchAlgorithmException, UnrecoverableKeyException, CertificateException, KeyStoreException, IOException, NoSuchProviderException, InvalidKeyException, SignatureException {
        Signature sign = Signature.getInstance(SIGNATURE_ALGORITHM);

        sign.initSign(KeyReader.getPrivateKey());
        sign.update(content.getBytes(StandardCharsets.UTF_8));

        return sign.sign();
    }

    public static String getSignatureB64(String content) throws NoSuchAlgorithmException, UnrecoverableKeyException, CertificateException, KeyStoreException, IOException, SignatureException, NoSuchProviderException, InvalidKeyException {
        return Base64Utils.base64Encode(getSignature(content));
    }

    public static boolean verifySignature(String data, byte[] signature) throws NoSuchAlgorithmException, FileNotFoundException, CertificateException, InvalidKeyException, SignatureException {
        Signature sign = Signature.getInstance(SIGNATURE_ALGORITHM);

        sign.initVerify(KeyReader.getPublicKey());
        sign.update(data.getBytes(StandardCharsets.UTF_8));

        return sign.verify(signature);
    }

    public static void main(String[] args) throws IOException, NoSuchPaddingException, IllegalBlockSizeException, CertificateException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, UnrecoverableKeyException, KeyStoreException, NoSuchProviderException, SignatureException {
        String encodedMessage = Base64Utils.base64Encode(encrypt("Some " +
                "message"));
        System.out.println(encodedMessage);
        byte[] encMessageSignature = getSignature(encodedMessage);

        boolean isSignatureValid = verifySignature(encodedMessage, encMessageSignature);
        System.out.println(decrypt(encodedMessage));
        System.out.println("Is signature valid = "+isSignatureValid);
    }
}
