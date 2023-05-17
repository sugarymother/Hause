package fun.moyujian.hause.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;

/**
 * JWT工具类
 * @author Tian
 */
@Slf4j(topic = "JWT工具类")
public class JwtUtil {

    /**
     * 密钥
     */
    private final static String SECRET = "fun.moyujian.hause";
    /**
     * 签发者
     */
    private final static String ISSUER = "moyujian";

    /**
     * 为了解决多点登录问题而设
     * 当再次签发token时，使之前签发的token全部失效
     */
    private final static HashMap<Long, String> tokenMap = new HashMap<>(64);

    /**
     * 签发Token
     * @param userId 用户id
     * @return 令牌
     */
    public static String signToken(Long userId) {
        //签发时间
        Date issuedAt = new Date();
        String token;
        try {
            //HMAC256加密实例
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            token = JWT.create()
                    //JWT签发方
                    .withIssuer(ISSUER)
                    //JWT接受者
                    .withAudience("hause")
                    //负载
                    .withClaim("UserId", userId.toString())
                    //签发时间
                    .withIssuedAt(issuedAt)
                    .sign(algorithm);
        } catch (Exception e) {
            log.error("Token签发工具类>签发Token异常:{}", e.getMessage());
            throw new RuntimeException("Token签发工具类>签发Token异常:" + e.getMessage());
        }
        tokenMap.put(userId, token);
        return token;
    }

    /**
     * 校验Token
     * @param token 令牌
     * @return 是否有效
     */
    public static boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            try {
                JWTVerifier verifier = JWT.require(algorithm)
                        .withIssuer(ISSUER)
                        .build();
                verifier.verify(token);
            } catch (JWTVerificationException e) {
                return false;
            }
        } catch (UnsupportedEncodingException e) {
            log.error("Token签发工具类>Token校验异常:{}", e.getMessage());
            throw new RuntimeException("Token签发工具类>Token校验异常:" + e.getMessage());
        }
        Long userId = getChaimUserId(token);
        return token.equals(tokenMap.getOrDefault(userId, ""));
    }

    /**
     * Token 负载UserId
     * @param token 令牌
     * @return 负载的用户id
     */
    public static Long getChaimUserId(String token) {
        return Long.parseLong(JWT.decode(token).getClaim("UserId").asString());
    }

}
