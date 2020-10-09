package com.lsim.messagesender.controller;

import com.lsim.messagesender.enitity.MessageInfoEntity;
import com.lsim.messagesender.model.MessageModel;
import com.lsim.messagesender.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    @Autowired
    MessageService messageService;

    @PostMapping("/send")
    public ResponseEntity addMessageInfo(@RequestBody MessageModel model){
       return messageService.saveMessage(model);
    }

    @GetMapping("/sms/send")
    public ResponseEntity chekMessage(@RequestParam("msisdn") String msisdn,
                                        @RequestParam("senderName") String senderName,
                                        @RequestParam("messageBody") String messageBody){
        return messageService.chekMessage(msisdn);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<MessageInfoEntity>> getMessages(){
       return messageService.checkDB();
    }
}
