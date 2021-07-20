package com.sad301.mediainfo.server;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class Utils {

  protected static boolean authValid(byte[] data, String hash, String password) throws Exception{
    Mac mac = Mac.getInstance("HmacSHA512");
    mac.init(new SecretKeySpec(password.getBytes(), "HmacSHA512"));
    byte[] server_hash = Base64.getEncoder().encode(mac.doFinal(data));
    return hash.equals(new String(server_hash));
  }

  protected static String token() {
    byte[] token = new byte[128];
    SecureRandom rng = new SecureRandom();
    rng.nextBytes(token);
    return new String(Base64.getEncoder().encode(token));
  }
}
