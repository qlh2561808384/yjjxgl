package com.datanew.util;


import com.alibaba.druid.util.Base64;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import java.io.*;
import java.security.*;

/**
 * Created by Lustin on 2016/2/25.
 */
    public class RSA_Encrypt {
    /**
     * 指定加密算法为DESede
     */
    private static String ALGORITHM = "RSA";
    /**
     * 指定key的大小
     */
    private static int KEYSIZE = 1024;
    /**
     * 指定公钥存放文件
     */
    private static String PUBLIC_KEY_FILE = "PublicKey";
    /**
     * 指定私钥存放文件
     */
    private static String PRIVATE_KEY_FILE = "PrivateKey";

    /**
     * 生成密钥对
     */
    public static Key[] generateKeyPair() throws NoSuchAlgorithmException {
        /** RSA算法要求有一个可信任的随机数源 */
        SecureRandom sr = new SecureRandom();
        /** 为RSA算法创建一个KeyPairGenerator对象 */
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM);
        /** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
        kpg.initialize(KEYSIZE, sr);
        /** 生成密匙对 */
        KeyPair kp = kpg.generateKeyPair();
        /** 得到公钥 */
        Key publicKey = kp.getPublic();
        /** 得到私钥 */
        Key privateKey = kp.getPrivate();
        Key[] keys={publicKey,privateKey};
        /** 用对象流将生成的密钥写入文件 */
        ObjectOutputStream oos1 = null;
        ObjectOutputStream oos2 = null;
        try {
            oos1 = new ObjectOutputStream(new FileOutputStream(PUBLIC_KEY_FILE));
            oos2 = new ObjectOutputStream(new FileOutputStream(PRIVATE_KEY_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            oos1.writeObject(publicKey);
            oos2.writeObject(privateKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /** 清空缓存，关闭文件输出流 */
        try {
            oos1.close();
            oos2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return keys;
    }

    /**
     * 加密方法
     * source： 源数据
     */
    public static String encrypt(String source) throws Exception {
        /** 将文件中的公钥对象读出 */
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
        Key key = (Key) ois.readObject();
        ois.close();
        /** 得到Cipher对象来实现对源数据的RSA加密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] b = source.getBytes();
        /** 执行加密操作 */
        byte[] b1 = cipher.doFinal(b);
        return Base64Utils.encodeToString(b1);

    }

    /**
     * 解密算法
     * cryptograph:密文
     */
    public static String decrypt(String cryptograph,Key privateKey) throws Exception {
        /** 将文件中的私钥对象读出 */
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
//        Key key = (Key) ois.readObject();
        /** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] b1 =Base64Utils.decodeFromString(cryptograph);// decoder.decodeBuffer(cryptograph);
        /** 执行解密操作 */
        byte[] b = cipher.doFinal(b1);
        return new String(b);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(encrypt("name=nobody&idcard=123&tel=456&email=789&regicode=330000&backurl=?"));
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
        Key key = (Key) ois.readObject();

        System.out.println(decrypt("IOPuJQsIH2AeKyp8IRjrUq6tfEYWUgEid2p//vJVpXzP9YMp0MHVzwavSBMe9GXNfde2j+GFZD/s\n" +
                "gfFWr9V2j8kq8b13dVLrivnVjLvLG5bPJTFOyOA+Hlk1JJjw+nI56OzmrdfBOqeqnlDmpgCQi1gu\n" +
                "iOENogebWL/yNVlnJBU=",key));
        //generateKeyPair();
//        String source = "Hello World!";//要加密的字符串
//        String cryptograph = encrypt(source);//生成的密文
//        System.out.println(cryptograph);
//
//        String target = decrypt(cryptograph);//解密密文
//        System.out.println(target);
//        System.out.println(decrypt("hYJDC1INS3jxU5ucC29rVuoz39ixuCre91FJ0WOMjVJKPfa3l/i8eDSisVtRC0FxtGgY+UFbalXD\n" +
//                "Pzx/m0Lb0CJpjR0Cz9xHQIGqES7VD9ZYPn6Igg7LvveXXkYQIJUNsBCbku1R07pBwS0OoLJ8okUm\n" +
//                "cn0nWzPjjP6qpAEfFEU="));
    }
}