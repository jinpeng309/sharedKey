package com.capslock.rpc.service.sharedkey;

import com.capslock.rpc.api.sharedkey.SharedKeyService;
import com.weibo.api.motan.config.springsupport.annotation.MotanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Created by alvin.
 */
@MotanService
public class SharedKeyServiceImpl implements SharedKeyService {
    private static final String SHARED_KEY_PREFIX = "shared_key_";

    @Autowired
    private RedisTemplate<String, byte[]> redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void addSharedKey(final String deviceUuid, final byte[] sharedKey) {
        redisTemplate.boundValueOps(createKey(deviceUuid)).set(sharedKey);
        stringRedisTemplate.convertAndSend(SHARED_KEY_TOPIC, deviceUuid);
    }

    @Override
    public byte[] getSharedKey(final String deviceUuid) {
        return redisTemplate.boundValueOps(createKey(deviceUuid)).get();
    }

    private String createKey(final String deviceUuid) {
        return SHARED_KEY_PREFIX.concat(deviceUuid);
    }
}
