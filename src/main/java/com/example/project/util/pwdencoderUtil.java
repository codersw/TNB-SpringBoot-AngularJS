package com.example.project.util;


import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by sw on 2017/9/20.
 * SHA-256 pwdencoder
 */
public class pwdencoderUtil {

    /**
     * 混淆码。防止破解。
     */
    private static  String defaultSalt = "abcd";

    /**
     * 获得混淆码
     *
     * @return
     */
    public String getDefaultSalt() {
        return defaultSalt;
    }

    /**
     * 设置混淆码
     *
     * @param defaultSalt
     */
    public void setDefaultSalt(String defaultSalt) {
        this.defaultSalt = defaultSalt;
    }


    public static String encodePassword(String rawPass) {
        return encodePassword(rawPass, defaultSalt);
    }

    public static String encodePassword(String rawPass, String salt) {
        String saltedPass = mergePasswordAndSalt(rawPass, salt, false);
        MessageDigest messageDigest = getMessageDigest();
        byte[] digest;
        try {
            digest = messageDigest.digest(saltedPass.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("UTF-8 not supported!");
        }
        return new String(Hex.encodeHex(digest));
    }

    public boolean isPasswordValid(String encPass, String rawPass) {
        return isPasswordValid(encPass, rawPass, defaultSalt);
    }

    public boolean isPasswordValid(String encPass, String rawPass, String salt) {
        if (encPass == null) {
            return false;
        }
        String pass2 = encodePassword(rawPass, salt);
        return encPass.equals(pass2);
    }

    public static MessageDigest getMessageDigest() {
        String algorithm = "SHA-256";
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm [" + algorithm + "]");
        }
    }

    public static String mergePasswordAndSalt(String password, Object salt, boolean strict) {
        if (password == null) {
            password = "";
        }
        if (strict && (salt != null)) {
            if ((salt.toString().lastIndexOf("{") != -1) || (salt.toString().lastIndexOf("}") != -1)) {
                throw new IllegalArgumentException("Cannot use { or } in salt.toString()");
            }
        }
        if ((salt == null) || "".equals(salt)) {
            return password;
        } else {
            return password + "{" + salt.toString() + "}";
        }
    }

    public static void main(String[] args) {
        pwdencoderUtil encoder = new pwdencoderUtil();
        System.out.println(encoder.encodePassword("123456"));
    }
}

