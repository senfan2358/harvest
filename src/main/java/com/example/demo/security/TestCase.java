package com.example.demo.security;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author: pjialong
 * @program: demo2
 * @description: Base64加解密
 * @createDate: 2023-11-22 16:04
 **/
public class TestCase {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "senfan";
        String encrypt = encrypt(str);
        System.out.println(encrypt);
        System.out.println(encode(str.getBytes()));
        System.out.println(encodeBase64(str.getBytes()));
        // String decrypt = decrypt(encrypt);
        // System.out.println(decrypt);
        // System.out.println(new String(decode(encrypt), "utf-8"));
        System.out.println(decodeBase64(encrypt2(str)));
    }

    public static String encrypt2(String strSrc) {
        MessageDigest md = null;
        String strDes = null;

        byte[] bt = strSrc.getBytes();
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(bt);
            // strDes = bytes2Hex(md.digest()); // to HexString
            //进行base64转码
            strDes = encodeBase64(md.digest());
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
        return strDes;
    }

    public static String encrypt(String str) {
        String result = "";
        if (str != null) {
            try {
                return Base64.getEncoder().encodeToString(str.getBytes("utf-8"));
            } catch (UnsupportedEncodingException e) {
                // logger.error("加密失败",e);
                System.out.println("加密失败");
            }
        }
        return result;
    }

    // 加密
    public static String encode(byte[] bytes) {
        String result = "";
        if (bytes != null) {
            return Base64.getEncoder().encodeToString(bytes);
        }
        return result;
    }

    // 加密
    public static String encodeBase64(byte[] bytes) {
        String result = "";
        if (bytes != null) {
            return Base64.getEncoder().encodeToString(bytes);
        }
        return result;
    }

    // 解密
    public static String decodeBase64(String str) {
        String result = "";
        if (str != null) {
            try {
                result = new String(Base64.getUrlDecoder().decode(str), "utf-8");
            } catch (UnsupportedEncodingException e) {
                // logger.error("解密失败",e);
                System.out.println("解密失败");
            }
        }
        return result;
    }

    // 解密
    // public static String decrypt(String str) {
    //     String result = "";
    //     if (str != null) {
    //         try {
    //             result = new String(Base64.getUrlDecoder().decode(str), "utf-8");
    //         } catch (UnsupportedEncodingException e) {
    //             // logger.error("解密失败",e);
    //             System.out.println("解密失败");
    //         }
    //     }
    //     return result;
    // }
    // 解密
    public static byte[] decode(String str) {
        if (str != null) {
            return Base64.getDecoder().decode(str);
        }
        return null;
    }


}
