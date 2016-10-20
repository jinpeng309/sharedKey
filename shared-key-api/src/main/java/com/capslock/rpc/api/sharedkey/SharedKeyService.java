package com.capslock.rpc.api.sharedkey;

/**
 * Created by alvin.
 */
public interface SharedKeyService {
    String SHARED_KEY_TOPIC = "shared_key_topic";
    void addSharedKey(final String deviceUuid, final byte[] key);

    byte[] getSharedKey(final String deviceUuid);
}
