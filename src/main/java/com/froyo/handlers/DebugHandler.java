package com.froyo.handlers;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;

public class DebugHandler implements Handler {

    public void handle(Message message) throws MessagingException {

        System.out.println("Message from: " + getFrom(message));
    }

    private static String getFrom(Message javaMailMessage) throws MessagingException {

        String from = "";
        Address a[] = javaMailMessage.getFrom();
        if( a == null ) return null;

        for (int i = 0; i < a.length; i++) {
            Address address = a[i];
            from = from + address.toString();
        }

        return from;
    }
}
