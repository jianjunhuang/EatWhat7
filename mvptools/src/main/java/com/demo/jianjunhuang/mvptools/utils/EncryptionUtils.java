package com.demo.jianjunhuang.mvptools.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author jianjunhuang.me@foxmail.com
 * @since 2017/5/25.
 *
 * 加密工具<br/>
 * 目前包含:
 * 1. MD5
 * 2. SHA1
 */

public class EncryptionUtils {

  public static String getMD5(String str) {
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("MD5");
      messageDigest.update(str.getBytes(), 0, str.length());
      return new BigInteger(1, messageDigest.digest()).toString(16);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return str;
  }

  public static String getSHA1(String str) {
    StringBuffer sb = new StringBuffer();
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
      byte[] result = messageDigest.digest(str.getBytes());
      for (int i = 0; i < result.length; i++) {
        sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
      }
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return sb.toString();
  }
}
