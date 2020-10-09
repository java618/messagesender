package com.lsim.messagesender.repo;

import com.lsim.messagesender.enitity.MessageInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface MessageRepo extends JpaRepository<MessageInfoEntity, Long> {
    List<MessageInfoEntity> findAllBySendAndInsertDataAfter(boolean isSend, Timestamp date);
    MessageInfoEntity findMessageInfoEntityByMsisdn(String msisdn);
}
