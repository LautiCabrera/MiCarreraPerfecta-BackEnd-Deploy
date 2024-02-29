package com.IES.MiCarreraPerfecta.Utils;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EncryptorGenerator {
    
    @Value("${myCareerPerfect.secretWord}")
    private String secretWord;

    public String encrypt(Integer numero) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(secretWord.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(String.valueOf(numero).getBytes());
            // Usar Base64 para codificar los bytes en una cadena segura para URLs
            return Base64.getUrlEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int decrypt(String textoEncriptado) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(secretWord.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            // Decodificar la cadena encriptada usando Base64
            byte[] decryptedBytes = cipher.doFinal(Base64.getUrlDecoder().decode(textoEncriptado));
            return Integer.parseInt(new String(decryptedBytes));
        } catch (Exception e) {
            e.printStackTrace();
            return -1; 
        }
    }
    
}