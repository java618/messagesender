package com.lsim.messagesender.enitity;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "MESSAGE_INFO")
public class MessageInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "msisdn", nullable = false)
    private String msisdn;
    @Column(name = "sender_name", nullable = false)
    private String senderName;
    @Column(name = "message_body", nullable = false)
    private String messageBody;

    @Column(name = "insert_data", nullable = false)
    private Timestamp insertData;
    @Column(name = "done_data")
    private Timestamp doneData;

    @Column(name = "send", columnDefinition = "boolean default false")
    private boolean send;
    @Column(name = "status", columnDefinition = "boolean default true")
    private boolean status;

    public MessageInfoEntity() {
    }

    public MessageInfoEntity(String msisdn, String senderName, String messageBody, Timestamp insertData, Timestamp doneData) {
        this.msisdn = msisdn;
        this.senderName = senderName;
        this.messageBody = messageBody;
        this.insertData = insertData;
        this.doneData = doneData;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public Timestamp getInsertData() {
        return insertData;
    }

    public void setInsertData(Timestamp insertData) {
        this.insertData = insertData;
    }

    public Timestamp getDoneData() {
        return doneData;
    }

    public void setDoneData(Timestamp doneData) {
        this.doneData = doneData;
    }

    public boolean isSend() {
        return send;
    }

    public void setSend(boolean send) {
        this.send = send;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
