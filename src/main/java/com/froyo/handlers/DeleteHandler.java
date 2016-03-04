package com.froyo.handlers;

import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;

public class DeleteHandler implements Handler {

    public void handle(Message message) throws MessagingException {

        message.setFlag(Flags.Flag.DELETED, true);
        System.out.println("marked as deleted");
    }
}
