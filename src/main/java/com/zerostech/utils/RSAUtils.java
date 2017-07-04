package com.zerostech.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 迹_Jason on 2017/7/4.
 * RSA加密解密
 */
public class RSAUtils {

    public Map<String, String> generateKeys(String algorithm, int length, final String encode) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        keyPairGenerator.initialize(length);
        final KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return new HashMap<String, String>() {
            {
                this.put("PublicKey", new String(Base64.getEncoder().encode(keyPair.getPublic().getEncoded()), encode));
                this.put("PrivateKey", new String(Base64.getEncoder().encode(keyPair.getPrivate().getEncoded()), encode));
            }
        };
    }

    public PrivateKey getPrivateKey(String key, String algorithm) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] privateKey = Base64.getDecoder().decode(key);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKey);
        return KeyFactory.getInstance(algorithm).generatePrivate(spec);
    }

    public PublicKey getPublicKey(String key, String algorithm) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] privateKey = Base64.getDecoder().decode(key);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(privateKey);
        return KeyFactory.getInstance(algorithm).generatePublic(spec);
    }

    public byte[] encrypt(byte[] data, Key key, int keyLength, String algorithm) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(1, key);
        return this.packageCipher(data, cipher, keyLength / 8 - 11, data.length);
    }

    public byte[] decrypt(byte[] data, Key key, int keyLength, String algorithm) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(2, key);
        return this.packageCipher(data, cipher, keyLength / 8, data.length);
    }

    private byte[] packageCipher(byte[] data, Cipher cipher, int maxLength, int inputLength) throws IllegalBlockSizeException, BadPaddingException, IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;

        for(int i = 0; inputLength - offSet > 0; offSet = i * maxLength) {
            byte[] cache;
            if(inputLength - offSet > maxLength) {
                cache = cipher.doFinal(data, offSet, maxLength);
            } else {
                cache = cipher.doFinal(data, offSet, inputLength - offSet);
            }

            out.write(cache, 0, cache.length);
            ++i;
        }

        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    public byte[] sign(PrivateKey key, byte[] data, String algorithm) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signer = Signature.getInstance(algorithm);
        signer.initSign(key);
        signer.update(data);
        return signer.sign();
    }

    public boolean verify(PublicKey key, byte[] data, byte[] signature, String algorithm) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature verifier = Signature.getInstance(algorithm);
        verifier.initVerify(key);
        verifier.update(data);
        return verifier.verify(signature);
    }
}
