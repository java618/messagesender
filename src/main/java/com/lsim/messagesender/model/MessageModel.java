package com.lsim.messagesender.model;

import com.sun.istack.NotNull;

public class MessageModel {
    @NotNull
    private String msisdn;
    @NotNull
    private String senderName;
    @NotNull
    private String messageBody;

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
}
