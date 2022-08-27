package top.yztz.oa.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AuthUtils {
    public static final String SECRET = "d0j1nAdjT.22qw#ehqu.JK";

    public static final int calendarField = Calendar.DATE;
    // 10天过期
    public static final int calendarInterval = 10;

    private static JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();


    public static String getToke(String userID) {
        assert userID != null;
        Date iatDate = new Date();
        // expire time
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(calendarField, calendarInterval);
        Date expiresDate = nowTime.getTime();

        // header Map
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        // build token
        // param backups {iss:Service, aud:APP}
        String token = JWT.create().withHeader(map) // header
                .withClaim("iss", "restapi") // payload
                .withClaim("aud", "app")
                .withClaim("user_id", userID)
                .withIssuedAt(iatDate) // sign time
                .withExpiresAt(expiresDate) // expire time
                .sign(Algorithm.HMAC256(SECRET)); // signature

        return token;
    }

    public static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt;
        try {
            jwt = verifier.verify(token);
        } catch (Exception e) {
            // e.printStackTrace();
            // token 校验失败, 抛出Token验证非法异常
            return null;
        }
        return jwt.getClaims();
    }

    public static Long getAppUID(String token) {
        Map<String, Claim> claims = verifyToken(token);
        if(claims == null) return null;
        Claim user_id_claim = claims.get("user_id");
        if (null == user_id_claim || StringUtils.isEmpty(user_id_claim.asString())) {
            return null;
        }
        return Long.valueOf(user_id_claim.asString());
    }
}
