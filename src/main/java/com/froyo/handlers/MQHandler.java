package com.froyo.handlers;

import javax.mail.Message;
import javax.mail.MessagingException;

public class MQHandler implements Handler {

    public void handle(Message message) throws MessagingException {

        int messageNumber = message.getMessageNumber();
        System.out.println("Message Number: " + messageNumber);
    }
}
