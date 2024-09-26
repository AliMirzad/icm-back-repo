package ir.technyx.icm_core.service.rsa;

import ir.technyx.icm_core.domain.rsa.RsaKey;
import ir.technyx.icm_core.repository.rsa.RsaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.Cipher;
import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

@Service
@Transactional
@AllArgsConstructor
public class RSAEncryptionServiceImpl implements RsaEncryptionService {


    @Autowired
    private final RsaRepository rsaRepository;

    @Override
    public KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // Print the generated key pair
        System.out.println("Generated Key Pair:");
        System.out.println("Public Key: " + keyPair.getPublic());
        System.out.println("Private Key: " + keyPair.getPrivate());

        System.out.println("Encoded Public Key: " + Arrays.toString(new String[]{Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded())}));
        System.out.println("Encoded Private Key: " + Arrays.toString(new String[]{Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded())}));

        return keyPair;
    }

    @Override
    public byte[] encryptText(String text, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(text.getBytes());
    }

    @Override
    public String decryptText(byte[] encryptedData, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedData = cipher.doFinal(encryptedData);
        return new String(decryptedData);
    }


    @Override
    public void encryptFile(MultipartFile inputFile, String outputFile, PublicKey publicKey) throws Exception {
        try (InputStream inputStream = inputFile.getInputStream(); OutputStream outputStream = new FileOutputStream(outputFile)) {

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            byte[] inputBytes = new byte[(int) inputFile.getSize()];
            inputStream.read(inputBytes);

            byte[] encryptedBytes = cipher.doFinal(inputBytes);
            outputStream.write(encryptedBytes);
        }
    }

    @Override
    public void decryptFile(MultipartFile inputFile, String desktopDir, PrivateKey privateKey) throws Exception {
        String desktopPath = desktopDir + File.separator;

        try (InputStream inputStream = inputFile.getInputStream(); ByteArrayOutputStream decryptedOutputStream = new ByteArrayOutputStream()) {

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            byte[] inputBytes = new byte[(int) inputFile.getSize()];
            inputStream.read(inputBytes);

            byte[] decryptedBytes = cipher.doFinal(inputBytes);
            decryptedOutputStream.write(decryptedBytes);

            // Write the decrypted bytes to a text file on the desktop
            try (Writer writer = new FileWriter(desktopPath + inputFile.getOriginalFilename() + ".txt")) {
                writer.write(decryptedOutputStream.toString());
            }
        }
    }

    public void saveKeysToFile(KeyPair keyPair, String keyPairFile, String desktopDir) throws Exception {
        String desktopPath = desktopDir + File.separator;

        // Create the directory if it doesn't exist
        File directory = new File(desktopPath);
        if (!directory.exists()) {
            directory.mkdirs(); // Create parent directories if needed
        }

        // Save both public and private keys to a single text file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(desktopPath + keyPairFile))) {
            // Write public key
            writer.write("-----BEGIN RSA PUBLIC KEY-----\n");
            writer.write(new String(Base64.getEncoder().encode(keyPair.getPublic().getEncoded())));
            writer.write("\n-----END RSA PUBLIC KEY-----\n\n");

            // Write private key
            writer.write("-----BEGIN RSA PRIVATE KEY-----\n");
            writer.write(new String(Base64.getEncoder().encode(keyPair.getPrivate().getEncoded())));
            writer.write("\n-----END RSA PRIVATE KEY-----\n");
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Error occurred while saving key pair to file: " + desktopPath + keyPairFile);
        }
    }


    @Override
    public KeyPair loadKeyFromFile(MultipartFile keyPairFile) throws Exception {
        byte[] keyPairBytes = keyPairFile.getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(keyPairBytes);

        try (DataInputStream dataInputStream = new DataInputStream(inputStream)) {
            int publicKeyLength = dataInputStream.readInt();
            byte[] publicKeyBytes = new byte[publicKeyLength];
            dataInputStream.readFully(publicKeyBytes);

            int privateKeyLength = dataInputStream.readInt();
            byte[] privateKeyBytes = new byte[privateKeyLength];
            dataInputStream.readFully(privateKeyBytes);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

            return new KeyPair(publicKey, privateKey);
        }
    }

    @Override
    public void saveKeysToDatabase(KeyPair keyPair, long companyId) {
        RsaKey rsaKey = new RsaKey();
        rsaKey.setPublicKey(new String(Base64.getEncoder().encode(keyPair.getPublic().getEncoded())));
        rsaKey.setPrivateKey(new String(Base64.getEncoder().encode(keyPair.getPrivate().getEncoded())));
        rsaKey.setCompany(companyId);
        rsaKey.setRskVersion(1);
        rsaKey.setActive(true);
        rsaKey.setRegistrationInfo(null);
        rsaRepository.save(rsaKey);
    }

    @Override
    public KeyPair loadKeysFromDatabase(long companyId, long version) throws NoSuchAlgorithmException, InvalidKeySpecException {

        RsaKey rsaKey = rsaRepository.findByCompanyId(companyId, version);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(rsaKey.getPublicKey().getBytes());
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(rsaKey.getPrivateKey().getBytes());
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        return new KeyPair(publicKey, privateKey);
    }
}



