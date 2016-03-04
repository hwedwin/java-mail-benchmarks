package com.froyo;

import com.froyo.handlers.DebugHandler;
import com.froyo.handlers.Handler;
import com.google.common.base.Stopwatch;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import java.security.GeneralSecurityException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Main
 */
public class Main {

    private Handler handler;

    public Main() {

        handler = new DebugHandler();
    }

    public void process(String host, String username, String password) throws MessagingException, GeneralSecurityException {

        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);

        Properties props = System.getProperties();
        props.put("mail.imap.starttls.enable", "true");
        props.put("mail.imap.ssl.socketFactory", sf);

        Session session = Session.getDefaultInstance(props, null);
        Store store = session.getStore("imap");
        store.connect(host, username, password);

        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_WRITE);

        System.out.println("Found: " + inbox.getMessageCount() + " messages ");

        Message[] messages = inbox.getMessages();
        Stopwatch s = Stopwatch.createStarted();
        for (int i = 0; i < messages.length; i++) {

            Message m = messages[0];
            handler.handle(m);
        }

        Stopwatch done = s.stop();
        System.out.println("Time taken to read "
                           + inbox.getMessageCount()
                           + " emails: " + done.elapsed(TimeUnit.MILLISECONDS) + " ms ");

        inbox.close(true);
        store.close();
    }

    public static void main(String[] args) throws MessagingException, GeneralSecurityException {

        Main m = new Main();
        if (args.length == 3){
            m.process(args[0], args[1], args[2]);
        } else {
            System.out.println("Usage: <hostname> <username> <password>");
        }
    }
}
