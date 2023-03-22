package org.example;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

@Slf4j
public class RSAEncryptor {
    static final String ALGORITHM = "RSA";
    private static final String PUBLIC_KEY_PATH = RSAEncryptor.class.getClassLoader()
            .getResource("rsa/common_name1.cer").getPath();
    static final String RSA_ENCRYPTION_INSTANCE = "X.509";
    private static final String PRIVATE_KEY_PATH = RSAEncryptor.class.getClassLoader()
            .getResource("rsa/keystore1.p12").getPath();
    private static final String PRIVATE_KEY_INSTANCE = "PKCS12";
    private static final String KEY_STORE_PASSWORD = "";
    private static final String COMMOM_NAME_PASSWORD = "password";

    public static void main(String[] args) {
        Key privateKey = getPrivateKey();
        Key publicKey = getPublicKey();

        log.info("privateKey => {}", privateKey);
        log.info("publicKey => {}", publicKey);

        final String data = "some data";
        byte[] encrypt = encrypt(data);

        byte[] decrypt = decrypt(encrypt);

        log.info("encrypted data => {}", new String(encrypt));
        log.info("decrypted data => {}", new String(decrypt));
    }

    @SneakyThrows
    public static byte[] encrypt(final String data) {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey());

        return cipher.doFinal(data.getBytes());
    }

    @SneakyThrows
    public static byte[] decrypt(
            final byte[] cipherText) {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, getPrivateKey());

        return cipher.doFinal(cipherText);
    }

    @SneakyThrows
    private static Key getPrivateKey() {
        if (!Files.exists(Paths.get(PRIVATE_KEY_PATH))) {
            log.error("Could not find file with path {} for private key", PRIVATE_KEY_PATH);
            throw new FileNotFoundException("The file " + PRIVATE_KEY_PATH + " could not be found!!");
        }

        KeyStore keyStore = KeyStore.getInstance(PRIVATE_KEY_INSTANCE);
        keyStore.load(Files.newInputStream(
                        Paths.get(PRIVATE_KEY_PATH)),
                KEY_STORE_PASSWORD.toCharArray());

        return keyStore.getKey("common_name1", COMMOM_NAME_PASSWORD.toCharArray());
    }

    @SneakyThrows
    private static Key getPublicKey() {
        if (!Files.exists(Paths.get(PUBLIC_KEY_PATH))) {
            log.error("Could not find file with path {} for public key", PUBLIC_KEY_PATH);
            throw new FileNotFoundException("The file " + PUBLIC_KEY_PATH + " could not be found!!");
        }

        FileInputStream fin = new FileInputStream(PUBLIC_KEY_PATH);
        CertificateFactory f = CertificateFactory.getInstance(RSA_ENCRYPTION_INSTANCE);
        X509Certificate certificate = (X509Certificate) f.generateCertificate(fin);

        return certificate.getPublicKey();
    }

}
