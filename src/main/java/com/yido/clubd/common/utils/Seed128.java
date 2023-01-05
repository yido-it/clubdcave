package com.yido.clubd.common.utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Seed128 {
	private static final Charset ENCODING_TYPE = StandardCharsets.UTF_8;

    private final byte[] key;

    private final byte INIT_VECTOR[] = {
        (byte) 0x026, (byte) 0x08d, (byte) 0x066, (byte) 0x0a7, (byte) 0x035, (byte) 0x0a8,
        (byte) 0x01a, (byte) 0x081, (byte) 0x06f, (byte) 0x0ba, (byte) 0x0d9, (byte) 0x0fa,
        (byte) 0x036, (byte) 0x016, (byte) 0x025, (byte) 0x001
    };

    public Seed128(final String key) {
        this.key = key.getBytes(ENCODING_TYPE);
    }

    public String encrypt(final String str) {
        byte[] strBytes = str.getBytes(ENCODING_TYPE);
        byte[] encrypted = KisaSeedCbc.SEED_CBC_Encrypt(this.key, this.INIT_VECTOR, strBytes, 0, strBytes.length);
        return new String(Base64.getEncoder().encode(encrypted), ENCODING_TYPE);
    }

    public String decrypt(final String str) {
        byte[] decoded = Base64.getDecoder().decode(str.getBytes(ENCODING_TYPE));
        byte[] decrypted = KisaSeedCbc.SEED_CBC_Decrypt(this.key, this.INIT_VECTOR, decoded, 0, decoded.length);
        return new String(decrypted, ENCODING_TYPE);
    }
}
