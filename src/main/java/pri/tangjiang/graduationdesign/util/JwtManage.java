package pri.tangjiang.graduationdesign.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtManage {

    private final static String SECRET = "tangjiang";

    public static String create(Long userId) {
        Calendar calendar = Calendar.getInstance();
        // 设置一天过期
        calendar.add(Calendar.SECOND, 60 * 60 * 24);

        // header Map
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        // build token
        // param backups {iss:Service, aud:APP}
        String token = JWT.create().withHeader(map) // header
                .withClaim("userId", userId) // payload
                .withIssuedAt(new Date()) // sign time
                .withExpiresAt(calendar.getTime()) // expire time
                .sign(Algorithm.HMAC256(SECRET)); // signature

        return token;
    }

    public static Long verifyToken(String token) throws Exception {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            e.printStackTrace();
            // token 校验失败, 抛出Token验证非法异常
            throw new Exception("token验证失败");
        }
        Claim userId = jwt.getClaims().get("userId");
        if (userId == null) {
            throw new Exception("token验证失败，无法获取用户信息失");
        }
        return Long.valueOf(userId.asInt());
    }
}
