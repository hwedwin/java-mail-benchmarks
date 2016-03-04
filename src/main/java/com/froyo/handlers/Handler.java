package com.froyo.handlers;

import javax.mail.Message;
import javax.mail.MessagingException;

public interface Handler {

    void handle(Message message) throws MessagingException;
}
