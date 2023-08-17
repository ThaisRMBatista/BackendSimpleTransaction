package com.transferenciasimplificada.services;

import com.transferenciasimplificada.domain.user.User;
import com.transferenciasimplificada.dtos.NotificationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);

        ResponseEntity<String> notificationResponse = restTemplate.postForEntity("http://o4d9z.mocklab.io/notify", notificationRequest, String.class);
        if (!notificationResponse.getStatusCode().equals(HttpStatus.OK)) {
            logger.info("erro ao enviar a notificação");
            throw new Exception("Serviço de Notificação está fora do ar");
        }
    }
}
