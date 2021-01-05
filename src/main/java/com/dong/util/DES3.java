package com.dong.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;
import java.util.Base64;

/**
 * @author LD
 */
public class DES3 {

    private static final String Algorithm = "DESede"; // 定义 加密算法,可用DES,DESede,Blowfish

    /**
     * 加密函数
     * @param keyByte 加密密钥，长度为24字节
     * @param src 被加密的数据缓冲区（源）
     * @return
     */
    public static byte[] encryptMode(byte[] keyByte, byte[] src) {
        try {
            // 生成密钥
            SecretKey desKey = new SecretKeySpec(keyByte, Algorithm);
            // 加密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.ENCRYPT_MODE, desKey);
            return c1.doFinal(src);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return null;
    }

    /**
     * 解密函数
     * @param keyByte 长度为24字节
     * @param src src为加密后的缓冲区
     * @return
     */
    public static byte[] decryptMode(byte[] keyByte, byte[] src) {
        try {
            // 生成密钥
            SecretKey desKey = new SecretKeySpec(keyByte, Algorithm);
            // 解密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE, desKey);
            return c1.doFinal(src);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return null;
    }

    // 转换成十六进制字符串
    public static String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs.append("0").append(stmp);
            } else {
                hs.append(stmp);
            }
            if (n < b.length - 1) {
                hs.append(":");
            }
        }
        return hs.toString().toUpperCase();
    }

    public static void main(String[] args) {

        // 添加新安全算法,如果用JCE就要把它添加进去
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        final byte[] keyBytes = {0x11, 0x22, 0x4F, 0x58, (byte) 0x88, 0x10, 0x40, 0x38, 0x28, 0x25, 0x79, 0x51, (byte) 0xCB, (byte) 0xDD, 0x55, 0x66, 0x77, 0x29, 0x74, (byte) 0x98, 0x30, 0x40, 0x36, (byte) 0xE2}; // 24字节的密钥
        String szSrc = "11A2SA4T5ADSF528DS2GA1D589JL6b49";
        System.out.println("加密前的字符串:" + szSrc);
        byte[] encoded = encryptMode(keyBytes, szSrc.getBytes());
        assert encoded != null;
        String s = Base64.getEncoder().encodeToString(encoded);
        System.out.println("加密后的字符串:" + s);
        byte[] srcBytes = decryptMode(keyBytes, encoded);
        assert srcBytes != null;
        System.out.println("解密后的字符串:" + (new String(srcBytes)));


    }

    /*public static void main(String[] args) throws Exception  {
        //KeyGenerator提供对称密钥生成器的功能，支持各种算法
        KeyGenerator keygen;
        //SecretKey负责保存对称密钥
        SecretKey deskey;
        //Cipher负责完成加密或解密工作
        Cipher c;
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        //实例化支持3DES算法的密钥生成器，算法名称用DESede
        keygen = KeyGenerator.getInstance("DESede");
        //生成密钥
        deskey = keygen.generateKey();
        //生成Cipher对象，指定其支持3DES算法
        c = Cipher.getInstance("DESede");
        String msg = "郭克华_安全编程技术";
        System.out.println("明文是：" + msg);
        //根据密钥，对Cipher对象进行初始化,ENCRYPT_MODE表示加密模式
        c.init(Cipher.ENCRYPT_MODE, deskey);
        byte[] src = msg.getBytes();
        //加密，结果保存进enc
        byte[] enc = c.doFinal(src);
        System.out.println("密文是：" + new String(enc));
        //根据密钥，对Cipher对象进行初始化,DECRYPT_MODE表示解密模式
        c.init(Cipher.DECRYPT_MODE, deskey);
        //解密，结果保存进dec
        byte[] dec = c.doFinal(enc);
        System.out.println("解密后的结果是："+ new String(dec));
    }*/
}
