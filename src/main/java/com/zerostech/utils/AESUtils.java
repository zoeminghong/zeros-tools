package com.zerostech.utils;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Created by 迹_Jason on 2017/6/29.
 * AES加密
 */
public class AESUtils {

    private static final String KEY = "a9ac9b31e34d73433b0e2ac92b6dbb55";

    /**
     * 自动生成AES128位密钥
     */
    public static void getA221() {
        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128); //要生成多少位，只需要修改这里即可128, 192或256
            SecretKey sk = kg.generateKey();
            byte[] b = sk.getEncoded();
            String s = byteToHexString(b);
            System.out.println(s);
            System.out.println("十六进制密钥长度为" + s.length());
            System.out.println("二进制密钥的长度为" + s.length() * 4);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("没有此算法。");
        }
    }

    /**
     * 二进制byte[]转十六进制string
     */
    public static String byteToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String strHex = Integer.toHexString(bytes[i]);
            if (strHex.length() > 3) {
                sb.append(strHex.substring(6));
            } else {
                if (strHex.length() < 2) {
                    sb.append("0" + strHex);
                } else {
                    sb.append(strHex);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 十六进制string转二进制byte[]
     */
    public static byte[] hexStringToByte(String s) {
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                System.out.println("十六进制转byte发生错误！！！");
                e.printStackTrace();
            }
        }
        return baKeyword;
    }

    /**
     * 使用对称密钥进行加密
     */
    public static String encode(String mingwen) throws Exception {
        byte[] keyb = hexStringToByte(KEY);
        SecretKeySpec sKeySpec = new SecretKeySpec(keyb, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, sKeySpec);
        byte[] bjiamihou = cipher.doFinal(mingwen.getBytes());
        return URLEncoder.encode(new String(Base64.getEncoder().encode(bjiamihou), "UTF-8"), "UTF-8");
    }

    /**
     * 使用对称密钥进行解密
     */
    public static String decode(String sjiami) throws Exception {
        byte[] keyb = hexStringToByte(KEY);
        byte[] miwen = Base64.getDecoder().decode(URLDecoder.decode(sjiami, "UTF-8"));
        SecretKeySpec sKeySpec = new SecretKeySpec(keyb, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, sKeySpec);
        byte[] bjiemihou = cipher.doFinal(miwen);
        return new String(bjiemihou);
    }

//    public static void main(String[] args) throws Exception {
//        System.out.println(AESUtils.decode(AESUtils.encode("ssss")));
//    }

}
