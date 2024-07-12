package com.billing.socket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.billing.model.ServiceMaster;

@Component
public class ServiceMasterWebSocketHandler {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ServiceMasterWebSocketHandler(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendServiceMastersToClients(List<ServiceMaster> serviceMasters) {
        messagingTemplate.convertAndSend("/topic/serviceMasters", serviceMasters);
    }
    
}
