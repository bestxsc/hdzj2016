package wl.hdzj.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.util.Base64Utils;

/**
 * Created by micro on 2016/11/3.
 */
public class TokenUtils {

    @Autowired
    private StringRedisTemplate template;

    public String createToken(String username){
        ValueOperations<String, String> ops = this.template.opsForValue();
        String token = Base64Utils.encodeToString(KeyGenerators.secureRandom(64).generateKey());
        ops.set(token, username);
        return token;
    }

    public String getUserNameFromToken(String token){
        ValueOperations<String, String> ops = this.template.opsForValue();
        if (!this.template.hasKey(token)) {
            return null;
        } else {
            return ops.get(token);
        }
    }
}
