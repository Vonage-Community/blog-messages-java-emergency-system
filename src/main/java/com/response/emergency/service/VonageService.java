package com.response.emergency.service;

import com.response.emergency.config.VonageConfig;
import com.vonage.client.VonageClient;
import com.vonage.client.messages.MessageResponse;
import com.vonage.client.messages.MessagesClient;
import com.vonage.client.messages.whatsapp.WhatsappTextRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VonageService {

    @Value("${VONAGE_NUMBER}")
    private static String vonageNumber;

    public void sendWhatsApp(String toNumber, String text) {

        VonageClient client = VonageConfig.getInstance();

        MessagesClient whatsAppClient = client.getMessagesClient();

        var message = WhatsappTextRequest.builder()
                .from(vonageNumber).to(toNumber)
                .text(text)
                .build();

        MessageResponse response = whatsAppClient.sendMessage(message);
        log.info("Message sent successfully. ID: "+response.getMessageUuid());
    }
}