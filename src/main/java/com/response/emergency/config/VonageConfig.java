package com.response.emergency.config;

import com.vonage.client.VonageClient;
import org.springframework.beans.factory.annotation.Value;

public class VonageConfig {

    @Value("${APPLICATION_ID}")
    private static String applicationId;

    @Value("${PRIVATE_KEY}")
    private static String privateKey;

    private static VonageClient instance;

    private VonageConfig() {}

    public static VonageClient getInstance() {
        if (instance == null) {
            instance = VonageClient.builder()
                    .applicationId(applicationId)
                    .privateKeyPath(privateKey)
                    .build();
        }

        return instance;
    }
}
