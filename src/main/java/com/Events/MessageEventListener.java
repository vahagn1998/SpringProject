package com.Events;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service("messageEventListener")
public class MessageEventListener implements ApplicationListener<MessageEvent> {

    @Override
    public void onApplicationEvent(MessageEvent event) {
        String msg = event.getMsg();
        System.out.println(msg);
    }
}
