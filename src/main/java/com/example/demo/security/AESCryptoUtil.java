package com.example.demo.security;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author: pjialong
 * @program: demo2
 * @description: ASE加解密工具类
 * @createDate: 2023-11-22 16:31
 **/
public class AESCryptoUtil {
    private static Logger logger = LoggerFactory.getLogger(AESCryptoUtil.class);

    private static String defaultKey;

    private static String defaultIv;

    public static void setConfig(String key, String iv) {
        defaultKey = key;
        defaultIv = iv;
    }


    /**
     * 加密方法
     *
     * @param data 要加密的数据
     * @param key  加密key
     * @param iv   加密iv
     * @return 加密的结果
     * @throws Exception
     */
    public static String encrypt(String data, String key, String iv) {
        try {
            //"算法/模式/补码方式"NoPadding PkcsPadding
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();

            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }

            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);

            return new Base64().encodeToString(encrypted);

        } catch (Exception e) {
            logger.error("加密失败", e);
            return null;
        }
    }

    /**
     * AES加密
     */
    public static byte[] encrypt(byte[] encData, String key, String ivStr){
        try {
            if (encData == null || encData.length == 0) {
                return null;
            }
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); // 扫描工具纰漏CBC模式不安全。 应采用GCM 无填充模式

            byte[] raw = key.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(ivStr.getBytes("ASCII"));// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            return cipher.doFinal(encData);
        } catch (Exception e) {
            logger.error("加密失败", e);
            return null;
        }
    }

    /**
     * 解密方法
     *
     * @param data 要解密的数据
     * @param key  解密key
     * @param iv   解密iv
     * @return 解密的结果
     * @throws Exception
     */
    public static String desEncrypt(String data, String key, String iv) {
        try {
            byte[] encrypted1 = new Base64().decode(data);

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original);
            return originalString.trim();
        } catch (Exception e) {
            logger.error("des解密失败", e);
            return null;
        }
    }

    public static void main(String[] args) {
        String v = "SWY2WUxrODRWZVpBRjg0M3YzcGFUdz09";
        String a = encrypt("18759599888", "asd45asd564a@#@$", "1234567890123456");
        System.out.println("a," + a + ",");
        String r = desEncrypt(v, "f5d2abbe29a34882", "c7721ef6d3d44781");
        System.out.println("r," + r + ",");
        String aR = desEncrypt(a, "asd45asd564a@#@$", "1234567890123456");
        System.out.println("aR," + aR + ",");
    }

    public static byte[] decrypt(byte[] src) {
        return decrypt(src, defaultKey, defaultIv);
    }

    public static byte[] encrypt(byte[] src) {
        try {
            return encrypt(src, defaultKey, defaultIv);
        } catch (Exception e) {
            // throw new RooBaseException(e);
            throw new RuntimeException();
        }

    }

    /**
     * AES解密
     */
    public static byte[] decrypt(byte[] src, String key, String ivString) {
        try {
            if (src == null || src.length == 0) {
                return null;
            }
            byte[] raw = key.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivString.getBytes("ASCII"));
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(src);
            return original;

        } catch (Exception e) {
            logger.error("解密失败", e);
            return null;
        }
    }
}
