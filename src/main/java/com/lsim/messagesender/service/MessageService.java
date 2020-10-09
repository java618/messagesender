package com.lsim.messagesender.service;

import com.lsim.messagesender.enitity.MessageInfoEntity;
import com.lsim.messagesender.model.MessageModel;
import com.lsim.messagesender.repo.MessageRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;

    private final long fiveDays = 5*1000 * 60 * 60 * 24;

    private final Logger logger = LoggerFactory.getLogger(MessageService.class);

    public ResponseEntity saveMessage(MessageModel model){
        logger.info("Save message service: Start");
        HttpStatus httpStatus;
        MessageInfoEntity msEntity = new MessageInfoEntity();

        if (isNumber(model.getMsisdn())){
            msEntity.setMsisdn(model.getMsisdn());
            msEntity.setSenderName(model.getSenderName());
            msEntity.setMessageBody(model.getMessageBody());
            msEntity.setInsertData(new Timestamp(new Date().getTime()));

            messageRepo.save(msEntity);
            httpStatus = HttpStatus.CREATED;
            logger.info("Save message service: Message saved");
        }else {
            logger.info("Save message service: Number incorrect");
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity(httpStatus);
    }

    public ResponseEntity chekMessage(String msisdn){
        HttpStatus httpStatus;

        MessageInfoEntity miEntity = messageRepo.findMessageInfoEntityByMsisdn(msisdn);
        if (!miEntity.isSend()){
            miEntity.setSend(true);
            messageRepo.save(miEntity);
            httpStatus = HttpStatus.OK;
        }else {
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity(httpStatus);
    }

    public boolean isNumber(String number){
        Pattern pattern = Pattern.compile("^(994)[- .]?[(]?(10|50|51|55|60|70|77|99)[)]?[- .]?\\d{7}$");
        Matcher matcher = pattern.matcher(number);

        return matcher.matches();
    }

    public ResponseEntity<List<MessageInfoEntity>> checkDB(){

        Timestamp timestamp = new Timestamp(new Date(System.currentTimeMillis() - fiveDays).getTime());
        List<MessageInfoEntity> miEntities = messageRepo.findAllBySendAndInsertDataAfter(false, timestamp );

        return new ResponseEntity<>(miEntities, HttpStatus.OK);
    }

}
