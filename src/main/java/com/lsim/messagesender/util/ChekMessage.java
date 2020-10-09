package com.lsim.messagesender.util;

import com.lsim.messagesender.enitity.MessageInfoEntity;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Component
public class ChekMessage {

    @Scheduled(fixedDelay = 1000)
    public void chekMessage(){

        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://localhost:8080/messages";


        ResponseEntity<MessageInfoEntity[]> miEntitiesNode = restTemplate.getForEntity(uri, MessageInfoEntity[].class);

        List<MessageInfoEntity> entityList = Arrays.asList(miEntitiesNode.getBody());

        entityList.parallelStream().forEach(messageInfoEntity ->
                sendMessage(messageInfoEntity)
                );


    }

    public void sendMessage(MessageInfoEntity miEntity){

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        RestTemplate restTemplate = new RestTemplate();
        String smsUri = "http://localhost:8080/sms/send";

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(smsUri)
                .queryParam("msisdn", miEntity.getMsisdn())
                .queryParam("senderName", miEntity.getSenderName())
                .queryParam("messageBody", miEntity.getMessageBody());

        HttpEntity<?> entity = new HttpEntity<>(headers);
        
        restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);

    }
}
