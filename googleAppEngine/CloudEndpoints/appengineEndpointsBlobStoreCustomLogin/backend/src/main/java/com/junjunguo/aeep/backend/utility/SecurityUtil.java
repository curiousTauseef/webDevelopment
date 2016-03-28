package com.junjunguo.aeep.backend.utility;

import com.google.appengine.repackaged.org.apache.commons.codec.binary.Base64;

import java.security.SecureRandom;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * This file is part of appengineEndpointsBlobStoreCustomLogin
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on March 27, 2016.
 */
public class SecurityUtil {
    private static SecurityUtil instance;

    public static SecurityUtil getInstance() {
        if (instance == null) {
            instance = new SecurityUtil();
        }
        return instance;
    }

    private final int iterations = 20 * 1000;
    private final int saltLen = 32;
    private final int desiredKeyLen = 256;

    /**
     * salt and hash a given password
     * @param password the password
     * @return the salted hash password
     * @throws Exception the exception
     */
    public String getSaltedHashPassword(String password) throws Exception {
        byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLen);
        // store the salt with the password
        return Base64.encodeBase64String(salt) + "$" + hashPassword(password, salt);
    }

    /**
     * Checks whether given plaintext password corresponds to a stored salted hash of the password.
     * @param password the password
     * @param stored   the stored
     * @return the boolean
     * @throws Exception the exception
     */
    public boolean validatePassword(String password, String stored) throws Exception {
        String[] saltAndPass = stored.split("\\$");
        if (saltAndPass.length != 2) {
            throw new IllegalStateException("The stored password have the form 'salt$hash'");
        }
        String hashOfInput = hashPassword(password, Base64.decodeBase64(saltAndPass[0]));
        return hashOfInput.equals(saltAndPass[1]);
    }

    /**
     * Gets salted hash password for storing in database.
     * @param password the string password
     * @param salt     the salt
     * @return the salted hash string password
     * @throws Exception the exception
     */
    private String hashPassword(String password, byte[] salt) throws Exception {
        if (password == null || password.length() == 0) {
            throw new IllegalArgumentException("password cannot be empty!");
        }
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, desiredKeyLen);
        return Base64.encodeBase64String(skf.generateSecret(spec).getEncoded());
    }
}
