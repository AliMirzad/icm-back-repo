package ir.technyx.icm_core.service.rsa;

import org.springframework.web.multipart.MultipartFile;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

public interface RsaEncryptionService {


    KeyPair generateKeyPair() throws Exception;

    byte[] encryptText(String text, PublicKey publicKey) throws Exception;

    String decryptText(byte[] encryptedData, PrivateKey privateKey) throws Exception;

    void encryptFile(MultipartFile inputFile, String outputFile, PublicKey publicKey) throws Exception;

    void decryptFile(MultipartFile inputFile, String desktopDir, PrivateKey privateKey) throws Exception;

    void saveKeysToFile(KeyPair keyPair, String keyPairFile, String desktopDir) throws Exception;

    KeyPair loadKeyFromFile(MultipartFile keyPairFile) throws Exception;

    void saveKeysToDatabase(KeyPair keyPair, long companyId);

    KeyPair loadKeysFromDatabase(long companyId, long version) throws NoSuchAlgorithmException, InvalidKeySpecException;


}
