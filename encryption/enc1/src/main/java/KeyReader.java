import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

@Slf4j
public class KeyReader {
    private static final String KEYSTORE_PASSWORD = "password";
    private static final String PRIVATE_KEY_PASSWORD = "password_key1";
    private static final String PRIVATE_KEY_ALIAS = "key1";

    private static final String PUBLIC_KEY = "key1.cer";
    private static final String PRIVATE_KEY = "key1.p12";

    public static PublicKey getPublicKey() throws CertificateException, FileNotFoundException {
        URL publicKeyUrl = KeyReader.class.getClassLoader().getResource(
                PUBLIC_KEY);
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");

        Certificate certificate =
                certificateFactory.generateCertificate(new FileInputStream(publicKeyUrl.getPath()));

        return certificate.getPublicKey();
    }

    public static PrivateKey getPrivateKey() throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException, NoSuchProviderException {
        URL privateKeyUrl = KeyReader.class.getClassLoader().getResource(
                PRIVATE_KEY);
        String privateKeyPath = privateKeyUrl.getPath();

        /**
         * Warn: If java is able to read PKCS12 file or not depends on the
         * java jdk version that you are using, for example, here is the
         * error you would get
         * 1. https://community.boomi.com/s/article/Troubleshooting-NoSuchAlgorithmException-Algorithm-HmacPBESHA256-not-available
         * 2. https://forum.gamemaker.io/index.php?threads/java-security-nosuchalgorithmexception-algorithm-hmacpbesha256-not-available.94216/
         */
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(new FileInputStream(privateKeyPath), KEYSTORE_PASSWORD.toCharArray());

        PrivateKey key = (PrivateKey) keyStore.getKey(PRIVATE_KEY_ALIAS,
                PRIVATE_KEY_PASSWORD.toCharArray());

        return key;
    }

    public static void main(String[] args) throws UnrecoverableKeyException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, NoSuchProviderException {
        PrivateKey privateKey = getPrivateKey();
        log.info("Private key=> format: {}, getAlgorithm: {}",
                privateKey.getFormat(),privateKey.getAlgorithm());
        PublicKey publicKey = getPublicKey();
        log.info("Public key=> format: {}, getAlgorithm: {}",
                publicKey.getFormat(),
                publicKey.getAlgorithm());
    }
}
