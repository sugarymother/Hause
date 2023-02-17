package fun.moyujian.hause.util;


import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;


/**
 * 加密工具类
 *
 * 使用AES对称加密，密文使用base64编码为字符串输出
 * ps：实际上不建议使用对称加密来加密密码，密码加密应使用不可逆的方式
 *
 * @author WSharkCoder
 * @date 2021/1/30 11:26
 */
@Slf4j(topic = "加密工具类")
public class EncryptUtil {
    /**
     * 对称加密密匙种子
     */
    private static final String KEY_SEED = "moyujian";

    /**
     * 密匙
     **/
    private static Key key;

    /**
     * 加密
     * @param info 原文文本
     * @return 密文，base64字符串形式
     */
    public static String encrypt(String info) {
        try {
            //生成AES加密实例
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, getKey());
            byte[] bytResult = cipher.doFinal(info.getBytes(StandardCharsets.UTF_8));
            //Base64编码
            return Base64.getEncoder().encodeToString(bytResult);
        } catch (Exception e) {
            log.error("加密工具类>AES加密异常:{}", e.getMessage());
            return null;
        }
    }

    /**
     * 解密
     * @param info base64密文字符串
     * @return 原文文本
     */
    public static String decrypt(String info) {
        try {
            //生成AES加密实例
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, getKey());
            byte[] bytInfo = Base64.getDecoder().decode(info.getBytes(StandardCharsets.UTF_8));
            byte[] bytResult = cipher.doFinal(bytInfo);
            return new String(bytResult, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("加密工具类>AES解密异常:{}", e.getMessage());
            return null;
        }
    }

    /**
     * 获取密匙，不存在则生成
     * @return key
     */
    public static Key getKey() throws NoSuchAlgorithmException {
        if (key == null) {
            //AES 加密密匙长度必须128
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            //设置密匙种子
            secureRandom.setSeed(KEY_SEED.getBytes(StandardCharsets.UTF_8));
            keyGenerator.init(128, secureRandom);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytKey = secretKey.getEncoded();
            return new SecretKeySpec(bytKey, "AES");
        }
        return key;
    }

    /**
     * 获取一个uuid
     * @return 32位uuid字符串
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

