package com.zerostech.utils;

import org.apache.commons.net.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 迹_Jason on 2017/7/4.
 * DES加密
 */
public class DESUtils {
    private static final String ALGORITHM = "DESede";
    private static final String DEFAULT_KEY = "KJ2I4956JXBKDF0E8WH4VFNW";
    private static final String charset = "UTF-8";

    public static byte[] encryptMode(byte[] keybyte, byte[] src) {
        try {
            SecretKey deskey = new SecretKeySpec(keybyte, ALGORITHM);
            Cipher c1 = Cipher.getInstance(ALGORITHM);
            c1.init(1, deskey);
            return c1.doFinal(src);
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    public static String encrypt(String originalString, String secretKey) {
        String result = null;
        secretKey = (secretKey == null) || ("".equals(secretKey.trim())) ? DEFAULT_KEY : secretKey;
        byte[] bEn = (byte[]) null;
        try {
            bEn = encryptMode(secretKey.getBytes(), originalString.getBytes(charset));
            result = new Base64().encodeToString(bEn);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String decrypt(String originalString, String secretKey) {
        secretKey = (secretKey == null) || ("".equals(secretKey.trim())) ? DEFAULT_KEY : secretKey;
        try {
            byte[] byteArr = Base64.decodeBase64(originalString);
            byte[] bEn = (byte[]) null;
            bEn = decryptMode(secretKey.getBytes(), byteArr);
            return new String(bEn, charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static byte[] decryptMode(byte[] keybyte, byte[] src) {
        try {
            SecretKey deskey = new SecretKeySpec(keybyte, ALGORITHM);
            Cipher c1 = Cipher.getInstance(ALGORITHM);
            c1.init(2, deskey);
            return c1.doFinal(src);
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }
}
