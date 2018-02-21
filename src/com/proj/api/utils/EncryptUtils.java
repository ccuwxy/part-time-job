package com.proj.api.utils;

import com.proj.api.exception.utils.AESDecryptException;
import com.proj.api.exception.utils.AESEncryptException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by jangitlau on 2017/11/27.
 */
public class EncryptUtils {
    public static class AES{
        public static String encryptData(String _sData, String _sKey) throws AESEncryptException {
            try {
                if (_sKey.length() < 16) {
                    _sKey = _sKey + DigestUtils.md5Hex(_sKey).substring(0, 16 - _sKey.length());
                } else if (_sKey.length() > 16) {
                    _sKey = _sKey.substring(0, 16);
                }
                Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
                int blockSize = cipher.getBlockSize();
                byte[] dataBytes = _sData.getBytes();
                int plaintextLength = dataBytes.length;
                if (plaintextLength % blockSize != 0) {
                    plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
                }
                byte[] plaintext = new byte[plaintextLength];
                System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
                SecretKeySpec keyspec = new SecretKeySpec(_sKey.getBytes(), "AES");
                IvParameterSpec ivspec = new IvParameterSpec(_sKey.getBytes());
                cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
                byte[] encrypted = cipher.doFinal(plaintext);
                return new String(Base64.encodeBase64(encrypted));
            } catch (Exception e) {
                e.printStackTrace();
                throw new AESEncryptException();
            }
        }

        public static String decryptData(String _sData, String _sKey) throws AESDecryptException {
            try {
                if (_sKey.length() < 16) {
                    _sKey = _sKey + DigestUtils.md5Hex(_sKey).substring(0, 16 - _sKey.length());
                } else if (_sKey.length() > 16) {
                    _sKey = _sKey.substring(0, 16);
                }
                byte[] encrypted =Base64.decodeBase64(_sData.getBytes());
                Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
                SecretKeySpec keyspec = new SecretKeySpec(_sKey.getBytes(), "AES");
                IvParameterSpec ivspec = new IvParameterSpec(_sKey.getBytes());
                cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
                byte[] original = cipher.doFinal(encrypted);
                String originalString = new String(original);
                return originalString;
            } catch (Exception e) {
                e.printStackTrace();
                throw new AESDecryptException();
            }
        }
    }


    public static class RSA{
        public static String encryptData(String _sData,String _sPublicKey){
            return _sData;
        }

        public static String decryptData(String _sData,String _sPrivateKey){
            return _sData;
        }
    }
}
